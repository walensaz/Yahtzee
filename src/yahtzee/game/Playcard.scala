package yahtzee.game

import yahtzee.game.dice.ScoreType

case class Playcard() {

  val allScores = ScoreType.All

  private var playerScores: Vector[Score] = Vector.empty

  def addUsedScore(score: Score): Unit = playerScores = playerScores :+ score
}

case class Score(scoreType: ScoreType, score: Int)
