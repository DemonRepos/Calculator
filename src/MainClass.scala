import scala.swing._
import scala.swing.event._
class MainClass extends MainFrame {
  class screen extends Label{
    horizontalTextPosition = Alignment.Right
    verticalTextPosition = Alignment.Bottom
    text = "0"
    foreground=java.awt.Color.gray
  }
  var cur = 0
  var memory = ""
  val block1 = new screen
  val block2 = new screen
  val block3 = new screen
  block1.foreground=java.awt.Color.black
  block3.text = " "
  class buttons extends Button{
    font = new Font("Arial",0,10)
    background = java.awt.Color.DARK_GRAY
    foreground=java.awt.Color.white
  }
  def clicking(a: String, b: String, c: String, d: String): Unit = {
    if (c=="/" && a=="0") return proc("Ошибка","Ошибка"," ",0)
    if (d=="=") return proc(calc(a, b, c), "0", " ", 1)
    return proc(a, calc(a, b, c), d, 0)
  }
  def proc(a: String, b: String, c: String, d: Int) {
    cur = d
    block1.text = a
    block2.text = b
    block3.text = c
  }
  def calc(a: String, b: String, c: String): String = {
    c match {
      case "+" => return correct((a.toFloat + b.toFloat).toString)
      case " " => return correct((a.toFloat + b.toFloat).toString)
      case "-" => return correct((b.toFloat - a.toFloat).toString)
      case "*" => return correct((a.toFloat * b.toFloat).toString)
      case "/" => return correct((b.toFloat / a.toFloat).toString)
      case _ => return b
    }
  }
  def correct(a:String):String ={
    if (a.toFloat - a.toFloat.round == 0) return a.substring(0,a.indexOf("."))
    return a
  }
  def save(a:String,b:String): String ={
    if (a != "Ошибка" && a.toFloat != 0) return a
    return b
  }
  def input(a:String,b:String): String ={
    if (a!="") return a
    return b
  }
  def clear(): String ={
    return ""
  }

  def addnum(c:String): Unit ={
    if (c=="." && block1.text.indexOf(".")!= -1) return 0
    if (block1.text=="Ошибка") return proc(c,"0"," ",1)
    if (cur == 1 || (block1.text.toFloat==0 && c=="."))
      return proc(block1.text + c, block2.text, block3.text, 1)
    return proc(c, block2.text, block3.text, 1)
  }
  def PressTheKey(q:String): Unit ={
    if ((q >= "0" && q <="9") || (q==".")) return addnum(q)
    if (q=="+" || q=="-" || q=="*" || q=="/" || q=="=") return clicking(block1.text, block2.text, block3.text,q)
    return 0
  }
  def Operation(c:Key.Value): Unit = {
    c.toString() match
    {
      case "Backspace" => return deleting(block1.text, block2.text, block3.text,"C")
      case "Delete" => return deleting(block1.text, block2.text, block3.text,"CE")
      case "Enter" =>  return clicking(block1.text,block2.text,block3.text,"=")
      case _ => return 0
    }
  }
  def deleting(a: String, b: String, c: String, d:String): Unit = {
    if ((d == "CE") || (block1.text.length()<2) || (block1.text=="0")) return proc("0", "0", " ", 0)
    else {
      if (block1.text=="Ошибка") return proc(block1.text,block2.text," ",1)
      if (cur == 1)
        return proc(block1.text.dropRight(1), block2.text, block3.text, 1)
      if (block1.text.toFloat == 0) return proc(block1.text, block2.text, block3.text, 0)
      return proc(block1.text.dropRight(1), block2.text, block3.text, 0)
    }
  }
  def conter(a:Label): String ={
    return correct((a.text.toFloat * -1).toString)
  }
  def turnthenum(a:Label,b:Label,c:String,d:Int): Unit ={
    return proc(conter(a),b.text,c,d)
  }
  def memorywork(a:String,b:String,c:String,d:Int,text:String): Unit = {
    text match {
      case "M" => memory = save(a,memory)
      case "MR" =>  return proc(input(memory,a),check(b),c,d)
      case _ => memory = clear()
    }
  }
  def check(a:String):String = {
  if (a=="Ошибка") return "0"
  return a
  }
  class nums extends buttons {
    preferredSize = new Dimension(40, 40)
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(_, _, _, _, _) => addnum(this.text)
    }

  }
  class signs extends buttons {
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(_, _, _, _, _) => clicking(block1.text, block2.text, block3.text, this.text)
    }
  }
  class turner extends buttons {
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(_, _, _, _, _) => turnthenum(block1, block2, block3.text, cur)
    }
  }
  class del extends buttons {
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(_, _, _, _, _) => deleting(block1.text, block2.text, block3.text, this.text)
    }

  }
  class rememberance extends buttons{
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(_, _, _, _, _) => memorywork(block1.text,block2.text,block3.text,cur,this.text)
    }
  }
}
