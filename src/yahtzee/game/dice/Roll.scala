package yahtzee.game.dice

case class Roll(die: Vector[Dice]) {
  def getDiceNum(diceNumber: Int): Dice = {
    if (diceNumber > 6) null
    else die(diceNumber)
  }
}
