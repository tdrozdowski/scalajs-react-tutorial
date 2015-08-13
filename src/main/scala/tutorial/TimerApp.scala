package tutorial

import japgolly.scalajs.react.{React, ReactComponentB, BackendScope}
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.JSApp


/**
 * Created with IntelliJ IDEA.
 * User: terry
 * Date: 8/13/15
 * Time: 12:10 PM
 *
 */

case class State(secondsElapsed : Long)

class Backend(scope: BackendScope[_, State]) {
  var interval : js.UndefOr[js.timers.SetIntervalHandle] = js.undefined

  def tick() = scope.modState(s => State(s.secondsElapsed + 1))

  def start() = interval = js.timers.setInterval(1000)(tick())
}

object TimerApp extends JSApp {

  val Timer = ReactComponentB[Unit]("Timer")
    .initialState(State(0))
    .backend(new Backend(_))
    .render($ => <.div("Seconds elapsed: ", $.state.secondsElapsed))
    .componentDidMount(_.backend.start())
    .componentWillUnmount(_.backend.interval foreach js.timers.clearInterval)
    .buildU

  def main() = {
    println("Attempting to run...")
    React.render(Timer(), dom.document.getElementById("root"))

  }

}
