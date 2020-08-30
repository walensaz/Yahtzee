package yahtzee.game

import java.util.concurrent.atomic.AtomicInteger

object GameManager {

  private val currentId: AtomicInteger = new AtomicInteger(0)
  var currentGames: Map[Int, Game] = Map.empty

  def startNewGame(players: List[Player]): Unit = {
    val newId = getNextId
    currentGames = currentGames + (newId -> Game(players, newId))
  }

  def endGame(id: Int): Unit = currentGames = currentGames - id

  private def getNextId: Int = currentId.getAndIncrement()
}
