
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object page extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[models.Post,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(post: models.Post, content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.36*/("""
<!DOCTYPE html>
<html>
<head>
<title>"""),_display_(Seq[Any](/*5.9*/post/*5.13*/.property("title"))),format.raw/*5.31*/(""" - MABE</title>
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*6.46*/routes/*6.52*/.Assets.at("stylesheets/main.css"))),format.raw/*6.86*/("""">
</head>
<body>

<nav>
  <a href=""""),_display_(Seq[Any](/*11.13*/routes/*11.19*/.Application.index)),format.raw/*11.37*/("""">Home</a>
</nav>

<header>
  <h1>"""),_display_(Seq[Any](/*15.8*/post/*15.12*/.property("title"))),format.raw/*15.30*/("""</h1>
</header>

<section class="byline">
  <p>
    """),_display_(Seq[Any](/*20.6*/List(post.property("author"), post.property("date"))/*20.58*/.flatten.mkString(" • "))),format.raw/*20.82*/("""
    """),_display_(Seq[Any](/*21.6*/post/*21.10*/.properties.getString("tags").split(" ").map/*21.54*/ { tag =>_display_(Seq[Any](format.raw/*21.63*/("""
      <a class="tag" href=""""),_display_(Seq[Any](/*22.29*/routes/*22.35*/.Application.tagged(tag))),format.raw/*22.59*/("""">#"""),_display_(Seq[Any](/*22.63*/tag)),format.raw/*22.66*/("""</a>
    """)))})),format.raw/*23.6*/("""
  </p>
</section>

<section class="summary">
  """),_display_(Seq[Any](/*28.4*/if(post.properties.hasPath("summary"))/*28.42*/ {_display_(Seq[Any](format.raw/*28.44*/("""
    """),_display_(Seq[Any](/*29.6*/views/*29.11*/.Helpers.markdown(post.properties.getString("summary")))),format.raw/*29.66*/("""
  """)))})),format.raw/*30.4*/("""
</section>

<section class="content">"""),_display_(Seq[Any](/*33.27*/content)),format.raw/*33.34*/("""</section>

"""),_display_(Seq[Any](/*35.2*/if(post.properties.hasPath("summary"))/*35.40*/ {_display_(Seq[Any](format.raw/*35.42*/("""
  <section class="signature">"""),_display_(Seq[Any](/*36.31*/views/*36.36*/.Helpers.markdown(post.properties.getString("signature")))),format.raw/*36.93*/("""</section>
""")))})),format.raw/*37.2*/("""

<footer>
  <p>MABE • Markdown Blog Engine • <a href="http://github.com/hilton/mabe">http://github.com/hilton/mabe</a></p>
</footer>
</body>
</html>
"""))}
    }
    
    def render(post:models.Post,content:Html): play.api.templates.HtmlFormat.Appendable = apply(post,content)
    
    def f:((models.Post,Html) => play.api.templates.HtmlFormat.Appendable) = (post,content) => apply(post,content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 17 11:13:18 CET 2014
                    SOURCE: /Users/mayleen/Downloads/play-2.2.1 2/mabe/app/views/page.scala.html
                    HASH: c9a014ff1e10f39aa5bb15c35c73e6d2bd3233e5
                    MATRIX: 565->1|693->35|766->74|778->78|817->96|913->157|927->163|982->197|1055->234|1070->240|1110->258|1180->293|1193->297|1233->315|1321->368|1382->420|1428->444|1469->450|1482->454|1535->498|1582->507|1647->536|1662->542|1708->566|1748->570|1773->573|1814->583|1898->632|1945->670|1985->672|2026->678|2040->683|2117->738|2152->742|2227->781|2256->788|2304->801|2351->839|2391->841|2458->872|2472->877|2551->934|2594->946
                    LINES: 19->1|22->1|26->5|26->5|26->5|27->6|27->6|27->6|32->11|32->11|32->11|36->15|36->15|36->15|41->20|41->20|41->20|42->21|42->21|42->21|42->21|43->22|43->22|43->22|43->22|43->22|44->23|49->28|49->28|49->28|50->29|50->29|50->29|51->30|54->33|54->33|56->35|56->35|56->35|57->36|57->36|57->36|58->37
                    -- GENERATED --
                */
            