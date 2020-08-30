package yahtzee.game.dice

import yahtzee.game.Utils

case class Dice(var roll: Short) {
  def reRoll(): Unit = roll = Utils.random(1, 6).toShort
}

object Dice {
  def roll: Dice = Dice(Utils.random(1, 6).toShort)
}
