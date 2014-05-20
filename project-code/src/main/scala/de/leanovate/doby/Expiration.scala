package de.leanovate.doby

import java.text.SimpleDateFormat
import java.util.Date
import scala.reflect.macros.blackbox.Context
import scala.util.Try


object Expiration {

  import scala.language.experimental.macros

  def expire(on: String): Unit = macro expire_on_impl

  def expire(on: String, warningDate: String): Unit = macro expire_on_and_warn_impl

  def TODO(author: String, description: String, by: String): Unit = macro todo_impl

  def expire_on_impl(c: Context)(on: c.Expr[String]): c.Expr[Unit] = {
    val onValue = valueOf(c)(on)
    val expirationDate = parseToDate(onValue)

    expireError(c)(expirationDate, s"has expired")

    c.literalUnit
  }

  def expire_on_and_warn_impl(c: Context)(on: c.Expr[String], warningDate: c.Expr[String]): c.Expr[Unit] = {
    val onValue = valueOf(c)(on)
    val expirationDate = parseToDate(onValue)

    expireError(c)(expirationDate, s"has expired")

    val warningDateAsString = valueOf(c)(warningDate)
    val warning = parseToDate(warningDateAsString)

    expireWarning(c)(warning, s"will expired")

    c.literalUnit
  }
  
  def todo_impl(c: Context)(author: c.Expr[String], description: c.Expr[String], by: c.Expr[String]): c.Expr[Unit] = {
    val authorValue = valueOf(c)(author)
    val descriptionValue = valueOf(c)(description)
    val byValue = valueOf(c)(by)

    val byDate = parseToDate(byValue)

    expireError(c)(byDate, s"TODO from '$authorValue': '$descriptionValue' has expired")

    val threeWeeksBefore = 3 * 7 * 24 * 60 * 60 * 60 * 1000
    val warningDate = new Date(byDate.getTime - threeWeeksBefore)

    expireWarning(c)(warningDate, s"TODO from '$authorValue': '$descriptionValue' will expired")

    c.literalUnit
  }

  private def parseToDate(s: String): Date = {
    Try {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      dateFormat.parse(s)
    }.getOrElse {
      val dateFormat = new SimpleDateFormat("yyyy/MM/dd")
      dateFormat.parse(s)
    }
  }

  private def valueOf(c: Context)(exp: c.Expr[String]): String = {
    import c.universe._

    exp.tree match {
      case Literal(Constant(s: String)) => s
      case _ => c.abort(c.enclosingPosition, s"'${show(exp.tree)}' must be a value!")
    }
  }

  private def expireError(c: Context)(expiration: Date, expirationMsg: String): Unit = {
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
