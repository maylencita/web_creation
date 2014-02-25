package views

import play.api.templates.Html
import org.pegdown.PegDownProcessor
import play.api.Play
import play.api.libs.Files
import play.api.Play.current

import org.joda.time._

import java.util.Date

/**
 * View template helpers a.k.a. tags.
 */
object Helpers {

  val includesDirectory = "includes"

  def markdown(markdown: String): Html = {
    Html(new PegDownProcessor().markdownToHtml(markdown))
  }

  def include(incl: String): Html = {
    val path = "%s/%s" format (includesDirectory, incl)
    val file = Play.getFile(path)
    if (file.exists()) {
      Html(Files.readFile(file))
    }else{
      Html("<!-- Include " + incl + " not found! -->")
    }

  }

  def groupByYearMonth(dates:Seq[Date]) = {
println("original dates: " + dates)
    dates.groupBy{date =>
      YearMonth.fromDateFields(date)
    }
  }

  def yearMonthFormatter(year:Int, month:Int):String = {
    val ym = new YearMonth(year, month)
    ym.toString("MMM yyyy")
  }
}