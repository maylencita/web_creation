package controllers

import play.api._
import play.api.Play.current
import play.api.mvc._
import play.api.libs.Files
import models.Post
import com.typesafe.config.{Config, ConfigFactory}
import java.io.File
import views.Helpers
import java.io.File._
import models.Post
import play.api.templates.HtmlFormat.Appendable
import java.util.Date
import org.joda.time._


object Application extends Controller {

  type Layout = (Seq[String], Seq[Post], String) => Appendable
  type PostLayout = (Seq[String], Post, play.api.templates.Html) => Appendable

  val postsDirectory = "posts"

  val layouts = Map[String, Layout](
    "blog" -> views.html.blog.render _,
    "justified" -> views.html.justified.render _,
    "offcanvas" -> views.html.offcanvas.render _
  )

  val postLayouts = Map[String, PostLayout](
    "blog_post" -> views.html.blog_post.render _,
    "justified_post" -> views.html.justified_post.render _,
    "offcanvas_post" -> views.html.offcanvas_post.render _
  )

  /**
   * Output an index page - a list of posts.
   */
  def index = Action { implicit request =>
    withLayout{ layout =>
      Ok(layout(categories, posts.filter(_.isListed), ""))
    }
  }

  /**
   * Output a list of posts with a given tag.
   */
  def tagged(tag: String) = Action { implicit request =>
    val taggedPosts = posts.filter(p=> p.isListed && p.property("tags").getOrElse("").split(" ").contains(tag))
    withLayout{ layout =>
      Ok(layout(categories, taggedPosts, tag))
    }
  }

  /**
   * Output a list of posts with a given category
   */
  def category(cat: String) = Action { implicit request =>
    val taggedPosts = posts.filter(p => p.isListed && p.property("category").exists(_ == cat))
    withLayout{ layout =>
      Ok(layout(categories, taggedPosts, cat))
    }
  }

  /**
   * Render a post as HTML from Markdown source.
   */
  def post(slug: String) = Action {
    val path = "%s%s%s.md" format (postsDirectory, separator, slug)
    val file = Play.getFile(path)
    if (file.exists()) {
      val content = Files.readFile(file)
      val body = content.split("---\n").last
      val post = Post(slug, properties(file).withFallback(play.api.Play.current.configuration.underlying))
      post.property("layout").flatMap{
        lname =>
          postLayouts.get(lname + "_post").map{layout =>
            Ok(layout(categories, post, Helpers.markdown(body)))
          }
      }.getOrElse{
        Ok(views.html.default_post(categories, post, Helpers.markdown(body)))
      }
    }
    else {
      NotFound("No source for post %s" format slug)
    }
  }

  def archive(year:Int, month:Int) = Action{implicit request =>
    withLayout{layout =>
      val df = play.api.Play.current.configuration.getString("date-format").getOrElse("dd/MM/yyyy")
      var thePosts = posts.filter(p => p.isListed &&
        YearMonth.fromDateFields(p.date(df)).equals(new YearMonth(year,month)))
      Ok(layout(categories, thePosts, ""))
    }
  }

  /**
   * Get a list of categories
   */
  def categories = {
    posts.filter(_.isListed).flatMap(_.property("category")).distinct
  }

  /**
   * Get a list of dates
   */
  def getDates:Seq[Date] = {
    val df = play.api.Play.current.configuration.getString("date-format").getOrElse("dd/MM/yyyy")
    posts.filter(_.isListed).map(_.date(df))
  }

  /**
   * A list of this site’s posts in reverse chronological order.
   */
  private def posts: Seq[Post] = {
    val postFiles = Play.getFile(postsDirectory).listFiles()
    val posts = postFiles.toList.foldLeft(Seq[Post]()) { 
      case (lst, file) =>
	if (file.getName.endsWith(".md") || file.getName.endsWith(".markdown")) {
          val slug = file.getName.split('.').dropRight(1)(0)
          lst :+ Post(slug,  properties(file).withFallback(play.api.Play.current.configuration.underlying))
	}else {
          lst
	}
    }
    val df = play.api.Play.current.configuration.getString("date-format").getOrElse("dd/MM/yyyy")
    posts.sortBy(_.date(df)).reverse
  }  

  /**
   * Read post ‘properties’ from a post’s ‘front-matter’ in Typesafe Config format.
   */
  private def properties(post: File): Config = {
    val content = Files.readFile(post)
    val sections = content.split("---\n")
    if (sections.length > 1) {
      ConfigFactory.parseString(sections(1))
    }
    else {
      ConfigFactory.empty
    }
  }

  private def withLayout[A](block: (Layout) => SimpleResult)(implicit request:Request[A]): SimpleResult = {
    play.api.Play.current.configuration.getString("layout").flatMap{
      lname =>
        layouts.get(lname).map{layout =>
          block(layout)
        }
    }.getOrElse{
      block(views.html.default.apply _)
    }
  }

  private def withPostLayout(post:Post)(block: (PostLayout) => SimpleResult): SimpleResult = {
    post.property("layout").flatMap{
      lname =>
        postLayouts.get(lname + "_post").map{layout =>
          block(layout)
        }
    }.getOrElse{
      block(views.html.default_post.render _)
    }
  }

}
