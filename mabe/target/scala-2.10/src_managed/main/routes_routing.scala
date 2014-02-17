// @SOURCE:/Users/mayleen/Downloads/play-2.2.1 2/mabe/conf/routes
// @HASH:6fbb6d20317b18363371e219488985efd5277519
// @DATE:Mon Feb 17 11:13:11 CET 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:1
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:2
private[this] lazy val controllers_Application_tagged1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tag/"),DynamicPart("tag", """[^/]+""",true))))
        

// @LINE:3
private[this] lazy val controllers_Application_post2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),DynamicPart("slug", """[^/]+""",true))))
        

// @LINE:5
private[this] lazy val controllers_Assets_at3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tag/$tag<[^/]+>""","""controllers.Application.tagged(tag:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """$slug<[^/]+>""","""controllers.Application.post(slug:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:1
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil,"GET", """""", Routes.prefix + """"""))
   }
}
        

// @LINE:2
case controllers_Application_tagged1(params) => {
   call(params.fromPath[String]("tag", None)) { (tag) =>
        invokeHandler(controllers.Application.tagged(tag), HandlerDef(this, "controllers.Application", "tagged", Seq(classOf[String]),"GET", """""", Routes.prefix + """tag/$tag<[^/]+>"""))
   }
}
        

// @LINE:3
case controllers_Application_post2(params) => {
   call(params.fromPath[String]("slug", None)) { (slug) =>
        invokeHandler(controllers.Application.post(slug), HandlerDef(this, "controllers.Application", "post", Seq(classOf[String]),"GET", """""", Routes.prefix + """$slug<[^/]+>"""))
   }
}
        

// @LINE:5
case controllers_Assets_at3(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     