package de.leanovate.doby

import scala.reflect.macros.blackbox.Context
import java.text.SimpleDateFormat

import java.util.Calendar

object Expiration {

  import scala.language.experimental.macros

  def expire(when: String): Unit = macro expire_impl

  def expire_impl(c: Context)(when: c.Expr[String]): c.Expr[Unit] = {
    import c.universe._

    val whenValue = when.tree match {
      case Literal(Constant(s: String)) => s
      case _ => c.abort(c.enclosingPosition, "'when' must be a value!")
    }

    val now = Calendar.getInstance()
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val whenAsDate = dateFormat.parse(whenValue)

    // check if has expired
    if (whenAsDate.before(now.getTime)) {
      c.error(c.enclosingPosition, s"has expired on the '$whenValue'")
    }

    // check if it will expire in 4 months
    now.add(Calendar.MONTH, 4)
    if (whenAsDate.before(now.getTime)) {
      c.warning(c.enclosingPosition, s"will expire on the '$whenValue'")
    }

    c.literalUnit
  }

}
