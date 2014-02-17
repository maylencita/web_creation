// @SOURCE:/Users/mayleen/Downloads/play-2.2.1 2/mabe/conf/routes
// @HASH:6fbb6d20317b18363371e219488985efd5277519
// @DATE:Mon Feb 17 11:13:11 CET 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:5
// @LINE:3
// @LINE:2
// @LINE:1
package controllers {

// @LINE:5
class ReverseAssets {
    

// @LINE:5
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:3
// @LINE:2
// @LINE:1
class ReverseApplication {
    

// @LINE:3
def post(slug:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + implicitly[PathBindable[String]].unbind("slug", dynamicString(slug)))
}
                                                

// @LINE:1
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:2
def tagged(tag:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "tag/" + implicitly[PathBindable[String]].unbind("tag", dynamicString(tag)))
}
                                                
    
}
                          
}
                  


// @LINE:5
// @LINE:3
// @LINE:2
// @LINE:1
package controllers.javascript {

// @LINE:5
class ReverseAssets {
    

// @LINE:5
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:3
// @LINE:2
// @LINE:1
class ReverseApplication {
    

// @LINE:3
def post : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.post",
   """
      function(slug) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("slug", encodeURIComponent(slug))})
      }
   """
)
                        

// @LINE:1
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:2
def tagged : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.tagged",
   """
      function(tag) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tag/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tag", encodeURIComponent(tag))})
      }
   """
)
                        
    
}
              
}
        


// @LINE:5
// @LINE:3
// @LINE:2
// @LINE:1
package controllers.ref {


// @LINE:5
class ReverseAssets {
    

// @LINE:5
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:3
// @LINE:2
// @LINE:1
class ReverseApplication {
    

// @LINE:3
def post(slug:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.post(slug), HandlerDef(this, "controllers.Application", "post", Seq(classOf[String]), "GET", """""", _prefix + """$slug<[^/]+>""")
)
                      

// @LINE:1
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:2
def tagged(tag:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.tagged(tag), HandlerDef(this, "controllers.Application", "tagged", Seq(classOf[String]), "GET", """""", _prefix + """tag/$tag<[^/]+>""")
)
                      
    
}
                          
}
        
    