
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,Seq[models.Post],play.api.mvc.RequestHeader,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String, posts: Seq[models.Post])(implicit request: play.api.mvc.RequestHeader):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.88*/("""
<!DOCTYPE html>
<html>
<head>
<title>"""),_display_(Seq[Any](/*5.9*/title)),format.raw/*5.14*/(""" - MABE</title>
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*6.46*/routes/*6.52*/.Assets.at("stylesheets/main.css"))),format.raw/*6.86*/("""">
</head>
<body class="index">

"""),_display_(Seq[Any](/*10.2*/if(request.path != "/")/*10.25*/ {_display_(Seq[Any](format.raw/*10.27*/("""
  <nav>
    <a href=""""),_display_(Seq[Any](/*12.15*/routes/*12.21*/.Application.index)),format.raw/*12.39*/("""">Home</a>
  </nav>
""")))})),format.raw/*14.2*/("""

<header>
<h1>"""),_display_(Seq[Any](/*17.6*/title)),format.raw/*17.11*/("""</h1>
</header>

<section class="content">
"""),_display_(Seq[Any](/*21.2*/for(post <- posts) yield /*21.20*/ {_display_(Seq[Any](format.raw/*21.22*/("""
  <h2>
    <a href=""""),_display_(Seq[Any](/*23.15*/routes/*23.21*/.Application.post(post.slug))),format.raw/*23.49*/("""">"""),_display_(Seq[Any](/*23.52*/post/*23.56*/.property("title"))),format.raw/*23.74*/("""</a>
    <span class="date">"""),_display_(Seq[Any](/*24.25*/post/*24.29*/.property("date"))),format.raw/*24.46*/("""</span>
  </h2>
  """),_display_(Seq[Any](/*26.4*/if(post.properties.hasPath("summary"))/*26.42*/ {_display_(Seq[Any](format.raw/*26.44*/("""
    """),_display_(Seq[Any](/*27.6*/views/*27.11*/.Helpers.markdown(post.properties.getString("summary")))),format.raw/*27.66*/("""
  """)))})),format.raw/*28.4*/("""
""")))})),format.raw/*29.2*/("""
</section>

<footer>
  <p>MABE • Markdown Blog Engine • <a href="http://github.com/hilton/mabe">http://github.com/hilton/mabe</a></p>
</footer>

</body>
</html>
"""))}
    }
    
    def render(title:String,posts:Seq[models.Post],request:play.api.mvc.RequestHeader): play.api.templates.HtmlFormat.Appendable = apply(title,posts)(request)
    
    def f:((String,Seq[models.Post]) => (play.api.mvc.RequestHeader) => play.api.templates.HtmlFormat.Appendable) = (title,posts) => (request) => apply(title,posts)(request)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 17 11:13:18 CET 2014
                    SOURCE: /Users/mayleen/Downloads/play-2.2.1 2/mabe/app/views/index.scala.html
                    HASH: fa8d16d09f73649d7c9fd886fe80f3a49835c768
                    MATRIX: 600->1|780->87|853->126|879->131|975->192|989->198|1044->232|1113->266|1145->289|1185->291|1244->314|1259->320|1299->338|1351->359|1402->375|1429->380|1508->424|1542->442|1582->444|1640->466|1655->472|1705->500|1744->503|1757->507|1797->525|1862->554|1875->558|1914->575|1968->594|2015->632|2055->634|2096->640|2110->645|2187->700|2222->704|2255->706
                    LINES: 19->1|22->1|26->5|26->5|27->6|27->6|27->6|31->10|31->10|31->10|33->12|33->12|33->12|35->14|38->17|38->17|42->21|42->21|42->21|44->23|44->23|44->23|44->23|44->23|44->23|45->24|45->24|45->24|47->26|47->26|47->26|48->27|48->27|48->27|49->28|50->29
                    -- GENERATED --
                */
            