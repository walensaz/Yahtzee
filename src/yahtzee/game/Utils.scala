package yahtzee.game

object Utils {
  def random(minInclusive: Int, maxInclusive: Int): Int = {
    ((Math.random() * maxInclusive) + minInclusive).toInt
  }
}
