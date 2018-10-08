import fastparse.all
import fastparse.all._


class CICOM2 {
  val number: P[Int] = P(CharIn('0' to '9').rep.!.map( _.toInt))
}
