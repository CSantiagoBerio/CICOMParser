import scala.io.Source

object Main extends CICOM {
  def main(args: Array[String]): Unit = {

    val source = Source.fromFile("src\\main\\scala\\testprogram").getLines().mkString

    parse(Exp, source) match {
      case Success(matched, _) => println("\nParsing Successful, Program Syntactically Correct...\n" + matched)
      case Failure(msg, _) => println(s"Failure: $msg")
      case Error(msg, _) => println(s"Error: $msg")
    }
    println()

    val parser = new CICOM
    val result = parser.parseAll(parser.Exp, source)
    if (result.successful) {
      println("Parsing Successful, Program Syntactically Correct...")
    } else {
      println("Parsing failed, Program Syntactically Incorrect")
    }
    println(result.toString)

  }

}
