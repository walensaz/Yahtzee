package yahtzee

import java.io.PrintWriter
import java.net.Socket

import org.java_websocket.WebSocket

trait YahtzeeSocket {
  def sendPacket(packet: String)
}

case class JavaConnection(socket: Socket) extends YahtzeeSocket {
  val writer = new PrintWriter(socket.getOutputStream)

  override def sendPacket(packet: String): Unit = {
    writer.println(packet)
    writer.flush()
  }
}

case class WebConnection(webSocket: WebSocket) extends YahtzeeSocket {
  override def sendPacket(packet: String): Unit = webSocket.send(packet)
}