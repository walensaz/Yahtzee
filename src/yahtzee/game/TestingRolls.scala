package yahtzee.game

import yahtzee.game.dice.{Dice, Roll, ScoreType}

object TestingRolls extends App {
  for (a: Int <- 1 to 10) {
    val diceRoll = Roll(Vector(Dice.roll, Dice.roll, Dice.roll, Dice.roll, Dice.roll, Dice.roll))
    println(s"Rolled: ${diceRoll.die.mkString(", ")}")
    val scoreTypes = ScoreType.determineApplicableScoreTypes(diceRoll)
    scoreTypes.foreach(scoreType => {
      println(scoreType.scoreType)
    })
  }
}
