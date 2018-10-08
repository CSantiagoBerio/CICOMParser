import scala.io.Source

object Main extends CICOM {
  def main(args: Array[String]): Unit = {

    val source = Source.fromFile("src\\main\\scala\\testprogram").getLines().mkString

    parse(Exp, source) match {
      case Success(matched, _) => println(matched)
      case Failure(msg, _) => println(s"Failure: $msg")
      case Error(msg, _) => println(s"Error: $msg")
    }

    val parser = new CICOM
    val result = parser.parseAll(parser.Exp, source)
    println()
    println(result.toString)

  }

}
