import scala.swing._
import scala.swing.event._
class Frame extends MainClass {
  iconImage = toolkit.getImage("C:\\Users\\Demon\\Desktop\\Курсовая\\Calculator\\scala-icon.png")
  val b0 = new nums
  b0.text = "0"
  val b1 = new nums
  b1.text = "1"
  val b2 = new nums
  b2.text = "2"
  val b3 = new nums
  b3.text = "3"
  val b4 = new nums
  b4.text = "4"
  val b5 = new nums
  b5.text = "5"
  val b6 = new nums
  b6.text = "6"
  val b7 = new nums
  b7.text = "7"
  val b8 = new nums
  b8.text = "8"
  val b9 = new nums
  b9.text = "9"
  val bpoint = new nums
  bpoint.text = "."
  val plus = new signs
  plus.text = "+"
  val minus = new signs
  minus.text = "-"
  val multiplex = new signs
  multiplex.text = "*"
  val division = new signs
  division.text = "/"
  val bequal = new signs
  bequal.text = "="
  val bdel = new del
  bdel.text = "C"
  val bce = new del
  bce.text = "CE"
  val bsave = new rememberance
  bsave.text = "M"
  val binp = new rememberance
  binp.text = "MR"
  val bclr = new rememberance
  bclr.text = "MC"
  val btrn = new turner
  btrn.text="-A"
  val buttons = new GridPanel(4,4)
  {
    background = java.awt.Color.black
    contents += b7
    contents += b8
    contents += b9
    contents += division
    contents += b4
    contents += b5
    contents += b6
    contents += multiplex
    contents += b1
    contents += b2
    contents += b3
    contents += minus
    contents += bpoint
    contents += b0
    contents += bequal
    contents += plus
  }
  val funcblock = new GridPanel(1,4){
    background = java.awt.Color.black
    contents += bclr
    contents += binp
    contents += bsave
    contents += btrn
  }
  val funcblock2 = new GridPanel(1,4){
    background = java.awt.Color.DARK_GRAY
    contents += new Label()
    contents += new Label()
    contents += bdel
    contents += bce
  }
  val display = new BorderPanel{
    background = java.awt.Color.white
    add(block3,BorderPanel.Position.West)
    add(block1,BorderPanel.Position.East)
  }
  val display2 = new BorderPanel{
    background = java.awt.Color.white
    add(block2,BorderPanel.Position.East)
  }
  val digits = new BoxPanel(Orientation.Vertical){
    contents += display2
    contents += display
    contents += funcblock2
    contents += funcblock
  }
  contents = new GridPanel(2, 1) {
    preferredSize = new Dimension(210, 200)
    contents += digits
    contents += buttons
    listenTo(keys)
    reactions += {
      case KeyReleased(_, c, _, _) => Operation(c)
      case KeyTyped(_, c, _, _) => PressTheKey(c.toString())
    }
    focusable = true
  }
}
object MyCalculator{
  def main(args: Array[String]): Unit ={

    val MyFrame = new Frame
    MyFrame.visible = true
  }
}