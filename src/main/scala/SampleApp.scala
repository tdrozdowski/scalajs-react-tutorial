import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{React, ReactComponentB}
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

/**
 * Created with IntelliJ IDEA.
 * User: terry
 * Date: 8/12/15
 * Time: 7:46 AM
 *
 */
@JSExport("SampleApp")
object SampleApp extends JSApp {
  val HelloMessage = ReactComponentB[String]("HelloMessage")
    .render(name => <.div("Hello ", name))
    .build

  @JSExport
  def main() = {
    println("Attempting to run...")
    React.render(HelloMessage("Terry"), dom.document.getElementById("welcome"))
  }
}
