package yahtzee

import java.net.InetSocketAddress
import java.nio.ByteBuffer

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer

class WebServer(port: Int) extends WebSocketServer(new InetSocketAddress((port))) {

  /**
   * A simple WebSocketServer implementation. Keeps track of a "chatroom".
   */
    override def onOpen(conn: WebSocket, handshake: ClientHandshake): Unit = {
      conn.send("Welcome to the server!") //This method sends a message to the new client
      broadcast("new connection: " + handshake.getResourceDescriptor) //This method sends a message to all clients connected
      System.out.println(conn.getRemoteSocketAddress.getAddress.getHostAddress + " entered the room!")
    }

    override def onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean): Unit = {
    }

    override def onMessage(conn: WebSocket, message: String): Unit = {

    }

    override def onMessage(conn: WebSocket, message: ByteBuffer): Unit = {

    }

    override def onError(conn: WebSocket, ex: Exception): Unit = {
      ex.printStackTrace()
      if (conn != null) {
        // some errors like port binding failed may not be assignable to a specific websocket
      }
    }

    override def onStart(): Unit = {
      System.out.println("Server started!")
      setConnectionLostTimeout(0)
      setConnectionLostTimeout(100)
    }
}
