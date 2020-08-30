package yahtzee

object Server {

  val server = new WebServer(8888)
  server.start()
}
