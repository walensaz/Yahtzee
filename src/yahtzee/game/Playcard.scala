package yahtzee.game

import yahtzee.game.dice.ScoreType

case class Playcard() {

  val allScores = ScoreType.All

  private var playerScores: Vector[ScoreType] = Vector.empty

  def addUsedScore(scoreType: ScoreType): Unit = playerScores = playerScores :+ scoreType


}

case class Score(scoreType: ScoreType, score: Int)
