package de.leanovate.doby

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}
import scala.reflect.macros.blackbox.Context
import scala.util.Try



import scala.language.experimental.macros

object expire {

  /**
   * define an expiration unit
   * @param on expiration date
   */
  def apply(on: String): Unit = macro ExpirationMacro.expire_on_impl

  /**
   * define an expiration unit
   * @param on expiration date
   * @param warningDate output a compilation warning after this date
   */
  def apply(on: String, warningDate: String): Unit = macro ExpirationMacro.expire_on_and_warn_impl
}


object TODO {

  /**
   * define a TODO note.
   * A TODO note does not compile anymore when the date has expired.
   * A TODO note emits a compiler warning 3 weeks before the expiration date.
   * @param author author of the note
   * @param description description of the node
   * @param by expiration date
   */
  def apply(author: String, description: String, by: String): Unit = macro ExpirationMacro.todo_impl
}

object FIXME {

  /**
   * define a FIXME note
   * @param author author of the note
   * @param description description of the node
   * @param by expiration date
   */
  def apply(author: String, description: String, by: String): Unit = macro ExpirationMacro.todo_impl

}

object ExpirationMacro {

  def expire_on_impl(c: Context)(on: c.Expr[String]): c.Tree = {

    import c.universe._

    val onValue = valueOf(c)(on)
    val expirationDate = parseToDate(onValue)

    expireError(c)(expirationDate, s"has expired")

    q""
  }

  def expire_on_and_warn_impl(c: Context)(on: c.Expr[String], warningDate: c.Expr[String]): c.Tree = {

    import c.universe._

    val onValue = valueOf(c)(on)
    val expirationDate = parseToDate(onValue)

    expireError(c)(expirationDate, s"has expired")

    val warningDateAsString = valueOf(c)(warningDate)
    val warning = parseToDate(warningDateAsString)

    expireWarning(c)(warning, s"will expired")

    q""
  }
  
  def todo_impl(c: Context)(author: c.Expr[String], description: c.Expr[String], by: c.Expr[String]): c.Tree = {

    import c.universe._

    val authorValue = valueOf(c)(author)
    val descriptionValue = valueOf(c)(description)
    val byValue = valueOf(c)(by)

    val byDate = parseToDate(byValue)

    expireError(c)(byDate, s"TODO from '$authorValue': '$descriptionValue' has expired")

    val cal = Calendar.getInstance()
    cal.setTime(byDate)
    cal.add(Calendar.WEEK_OF_YEAR, -3)
    val warningDate = cal.getTime

    expireWarning(c)(warningDate, s"TODO from '$authorValue': '$descriptionValue' will expired")

    q""
  }

  private[this] def parseToDate(s: String): Date = {
    Try {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      dateFormat.parse(s)
    }.getOrElse {
      val dateFormat = new SimpleDateFormat("yyyy/MM/dd")
      dateFormat.parse(s)
    }
  }

  private[this] def valueOf(c: Context)(exp: c.Expr[String]): String = {
    import c.universe._

    exp.tree match {
      case Literal(Constant(s: String)) => s
      case _ => c.abort(c.enclosingPosition, s"'${show(exp.tree)}' must be a value!")
    }
  }

  private[this] def expireError(c: Context)(expiration: Date, expirationMsg: String): Unit = {
    // check if has expired
    val now = new Date()
    if (now.after(expiration)) {
      c.error(c.enclosingPosition, expirationMsg)
    }
  }

  private def expireWarning(c: Context)(warningDate: Date, expirationMsg: String): Unit = {
    // check if it will expired
    val now = new Date()
    if (now.after(warningDate)) {
      c.warning(c.enclosingPosition, expirationMsg)
    }
  }

}
