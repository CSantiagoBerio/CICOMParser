import scala.util.parsing.combinator._
import scala.collection.mutable.HashSet

class CICOM extends RegexParsers {
  def Character: Parser[Any] = "[a-z]+".r | "[A-Z]+" .r| "?" | "_"

  def Digit: Parser[Any] = "[0-9]".r

  def Delimiter: Parser[Any] = "(" | ")" | "[" | "]" | "," | ";"

  def Operator: Parser[Any] = "+" | "-" | "~" | "*" | "/" | "!=" | ":=" | "<=" | ">=" | "=" | "<" | ">" | "&" | "|"

  def Exp: Parser[Any] = "if" ~ Exp ~ "then" ~ Exp ~ "else" ~ Exp |"let" ~ rep(Def) ~ "in" ~ Exp | "map" ~ IdList ~ "to" ~ Exp | Term ~ rep(Binop ~ Exp)

  def Term: Parser[Any] = Factor ~ rep("(" ~> ExpList <~ ")") | Unop ~ Term | Empty | int | Bool

  def Factor: Parser[Any] = "(" ~> Exp <~ ")" | Prim | Id

  def ExpList: Parser[Any] = rep(PropExpList)

  def PropExpList: Parser[Any] = Exp ~ "," ~ PropExpList | Exp

  def IdList: Parser[Any] = rep1(PropIdList)

  def PropIdList: Parser[Any] = Id ~ "," ~ PropIdList | Id

  def Def: Parser[Any] = Id ~ ":=" ~ Exp ~ ";"

  def Empty: Parser[Any] = "null"

  def Bool: Parser[Any] = "true" | "false"

  def Unop: Parser[Any] = Sign | "~"

  def Sign: Parser[Any] = "+" | "-"

  def Binop: Parser[Any] = Sign | "!=" | "<=" | ">=" | "*" | "/" | "=" | "<" | ">" | "&" | "|"

  def Prim: Parser[Any] = "number?" | "function?" | "list?" | "null?" | "cons?" | "cons" | "first" | "rest" | "arity"

//  def Id: Parser[Any] = Character ~ rep(Character | Digit)

  def int: Parser[Any] = rep1(Digit)

  def reservedWords = HashSet("if", "then", "map", "to", "else", "let", "in", "null")
  def idRegex= """[a-zA-Z][\.a-zA-Z0-9_-]*""".r
  def Id = Parser(input =>
    idRegex(input).filterWithError(
      !reservedWords.contains(_),
      reservedWord => s"$reservedWord is a reserved word, please use another word",
      input
    )
  )
}
