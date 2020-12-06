import java.net.Socket
import java.io.{DataInputStream, DataOutputStream}

object MainApp extends App {
    val clientSocket = new Socket("localhost", 5555)

    val instream = clientSocket.getInputStream()
    val outstream = clientSocket.getOutputStream()

    val dis = new DataInputStream(instream)
    val dos = new DataOutputStream(outstream)

    dos.writeBytes(s"${scala.io.StdIn.readLine("Enter your message: ")}\n")
    val data = dis.readLine()

    println(s"Received data from server: ${data}")
}