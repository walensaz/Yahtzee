package yahtzee.game

import java.sql.Connection

case class Player(playcard: Playcard, connection: Connection)
