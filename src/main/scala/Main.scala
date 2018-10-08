object Main extends CICOM {
  def main(args: Array[String]): Unit = {
//    parse(Def, "let\n  f := map n to if n = 0 then 1 else n * f(n - 1);") match {
//      case Success(matched, _) => println(matched)
//      case Failure(msg, _) => println(s"Failure: $msg")
//      case Error(msg, _) => println(s"Error: $msg")
//    }

    val stringToParse = "let\n  f := map n to if n = 0 then 1 else n * f(n - 1); \nin\n  let\n    f := map n,m,k to if (n <= 0 & n >= 0)\n                  | (n < 0 & n > 0 & n != 0) then number?\n                                           else m / f(k + 1);\n  in\n     let x:=3;\n         y:=4;\n         z:=cons?(function?(x * ~y), cons(-arity(x)));\n     in\n        let x:=3;\n            y:=4;\n            z:=g();\n        in\n            (g(x,y,z))(null?(true),list?(false),first(null))"
    val stringToParseNoWhitespace = "letf:=mapntoifn=0then1elsen*f(n-1);inletf:=mapn,m,ktoif(n<=0&n>=0)|(n<0&n>0&n!=0)thennumber?elsem/f(k+1);inletx:=3;y:=4;z:=cons?(function?(x*~y),cons(-arity(x)));inletx:=3;y:=4;z:=g();in(g(x,y,z))(null?(true),list?(false),first(null))"

    val parser = new CICOM
    val result = parser.parseAll(parser.Exp, stringToParse)
    val resultNoWhitespace = parser.parseAll(parser.Exp, stringToParseNoWhitespace)

    println(result.toString)
    //println(resultNoWhitespace.toString)
  }

}
