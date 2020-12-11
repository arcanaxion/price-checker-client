package priceChecker

import java.net.Socket
import java.io.{DataInputStream, DataOutputStream, ObjectInputStream}
import scala.collection.mutable.ArrayBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import scalafx.scene.image.Image

object MainApp extends JFXApp {

    // scalafx
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(getClass.getResourceAsStream("view/Home.fxml"))
    val border: scalafx.scene.layout.BorderPane = loader.getRoot[javafx.scene.layout.BorderPane]()
    val control = loader.getController[priceChecker.view.HomeController#Controller]()
    stage = new PrimaryStage() {
        title = "Price Checker"
        scene = new Scene(){
            root = border
        }
        icons += new Image(getClass.getResourceAsStream("/priceIcon.png"))
    }

    while(true) {
        val clientSocket = new Socket("localhost", 5555)

        val in = clientSocket.getInputStream()
        val out = clientSocket.getOutputStream()

        val dis = new DataInputStream(in)
        val dos = new DataOutputStream(out)
        val ois = new ObjectInputStream(in)

        val itemNames = ois.readObject().asInstanceOf[ArrayBuffer[String]]

        println("List of item names: ")
        for (name <- itemNames) {
            println(name)
        }

        val input = scala.io.StdIn.readLine("Enter item name to check price: ")
        dos.writeBytes(s"${input}\n")

        val data = dis.readLine()

        try {
            val price = data.toInt
            println(s"Price of $input is RM${price/100.0}")
        } catch {
            case e: Exception => println(data)
        }
    }
}