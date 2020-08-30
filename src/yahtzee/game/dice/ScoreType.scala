package yahtzee.game.dice

case class ScoreType(id: ScoreType.Id, scoreType: String, description: String, applicable: (Roll, Short) => Boolean)

object ScoreType {
  type Id = Int

  val Aces = ScoreType(1, "Aces", "Count and add only Aces", UpperExpression)
  val Twos = ScoreType(2, "Twos", "Count and add only Twos", UpperExpression)
  val Threes = ScoreType(3, "Threes", "Count and add only Threes", UpperExpression)
  val Fours = ScoreType(4, "Fours", "Count and add only Fours", UpperExpression)
  val Fives = ScoreType(5, "Fives", "Count and add only Fives", UpperExpression)
  val Sixes = ScoreType(6, "Sixes", "Count and add only Sixes", UpperExpression)
  val ThreeOfAKind = ScoreType(7, "3 of a kind", "Add total of all dice", ThreeOfAKindExpression)
  val FourOfAKind = ScoreType(8, "4 of a kind", "Add total of all dice", FourOfAKindExpression)
  val FullHouse = ScoreType(9, "Full House", "Score 25", FullHouseExpression)
  val SmallStraight = ScoreType(10, "Small Straight", "Score 30", SmallStraightExpression)
  val LargeStraight = ScoreType(11, "Large Straight", "Score 40", LargeStraightExpression)
  val Yahtzee = ScoreType(12, "Yahtzee", "Score 50", YahtzeeExpression)

  val All = Set(
    Aces, Twos, Threes, Fours, Fives, Sixes, ThreeOfAKind,
    FourOfAKind, FullHouse, SmallStraight, LargeStraight, Yahtzee
  )

  private def UpperExpression(roll: Roll, id: Short): Boolean = if(roll.die.contains(Dice(id))) true else false
  private def ThreeOfAKindExpression(roll: Roll, id: Short): Boolean = if(roll.die.groupBy(_.roll).exists(_._2.size >= 3)) true else false
  private def FourOfAKindExpression(roll: Roll, id: Short): Boolean = if(roll.die.groupBy(_.roll).exists(_._2.size >= 4)) true else false
  private def FullHouseExpression(roll: Roll, id: Short): Boolean = {
    val dieNumberToAmountOfDice = roll.die.groupBy(_.roll)
    if(dieNumberToAmountOfDice.size == 2) {
      val sizeOfFirstElement = dieNumberToAmountOfDice.toIndexedSeq(0)._2.size
      if(sizeOfFirstElement == 2 || sizeOfFirstElement == 3) true
      else false
    } else false
  }
  private def SmallStraightExpression(roll: Roll, id: Short): Boolean = straightExpression(roll, 4)._1
  private def LargeStraightExpression(roll: Roll, id: Short): Boolean = straightExpression(roll, 5)._2 == 5
  private def YahtzeeExpression(roll: Roll, id: Short): Boolean = if(roll.die.groupBy(_.roll).size == 1) true else false

  private def straightExpression(roll: Roll, straightLength: Int): (Boolean, Int) = {
    val sortedDiceUnique = roll.die.sortBy(_.roll)
    val straights = sortedDiceUnique.foldLeft(-1, 0, false)((acc, diceNum) =>
      if(acc._2 == straightLength) (diceNum.roll, 1, true) else {
        if (acc._1 + 1 == diceNum.roll) (diceNum.roll, acc._2 + 1, acc._3)
        else (diceNum.roll, 1, acc._3)
      }
    )
    (straights._3, straights._2)
  }

  def determineApplicableScoreTypes(roll: Roll): Set[ScoreType] =
    All.filter(scoreType => scoreType.applicable(roll, scoreType.id.toShort))
}
