package tutorial

import japgolly.scalajs.react.{React, ReactComponentB, ReactEventI, BackendScope}
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp

/**
 * Created with IntelliJ IDEA.
 * User: terry
 * Date: 8/13/15
 * Time: 1:14 PM
 *
 */

case class TodoState(items : List[String], text : String)

class TodoBackend($: BackendScope[Unit, TodoState]) {
  def onChange(e : ReactEventI) = $.modState(_.copy(text = e.target.value))

  def handleSubmit(e : ReactEventI) = {
    e.preventDefault()
    $.modState(s => TodoState(s.items :+ s.text, ""))
  }
}

object TodoMain extends JSApp {

  val TodoList = ReactComponentB[List[String]]("TodoList")
    .render(props => {
      def createItem(itemText : String) = <.li(itemText)
      <.ul(props map createItem)
    })
    .build

  val TodoApp = ReactComponentB[Unit]("TodoApp")
    .initialState(TodoState(Nil, ""))
    .backend(new TodoBackend(_))
    .render((_, S, B) =>
      <.div(
        <.h3("TODO"),
        TodoList(S.items),
        <.form(^.onSubmit ==> B.handleSubmit,
            <.input(^.onChange ==> B.onChange, ^.value := S.text),
            <.button("Add #", S.items.length + 1)
        )
      )
    ).buildU

  def main() = {
    println("Attempting to run...")
    React.render(TodoApp(), dom.document.getElementById("todoList"))
  }
}
