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

    var dis: Option[DataInputStream] = None
    var dos: Option[DataOutputStream] = None
    var ois: Option[ObjectInputStream] = None

    createNewSocket()

    def createNewSocket(): Unit = {
        // replace localhost with IP of server
        val clientSocket = new Socket("localhost", 5555)

        val in = clientSocket.getInputStream()
        val out = clientSocket.getOutputStream()

        dis = Some(new DataInputStream(in))
        dos = Some(new DataOutputStream(out))
        ois = Some(new ObjectInputStream(in))

        val itemNames = ois.get.readObject().asInstanceOf[ArrayBuffer[String]]

        // println("List of item names: ")
        for (name <- itemNames) {
            // println(name)
        }

        control.updateItemNames(itemNames)
    }

    def queryPrice(input: String): String = {
        dos.get.writeBytes(s"${input}\n")
        var data = dis.get.readLine()

        try {
            val price = data.toInt
            // println(s"Price of $input is RM${price/100.0}")
            // double dollar sign to print a single dollar sign
            // third dollar sign is for the variable
            data = f"$$${price/100.0}%.2f"
        } catch {
            // server responds with a string if an invalid item is queried
            // this is not actually needed as it won't happen because the GUI only
            // permits valid items
            case e: Exception =>
        }

        return data
    }    

    // scalafx
    stage = new PrimaryStage() {
        title = "Item Price Checker"
        scene = new Scene(){
            root = border
        }
        icons += new Image(getClass.getResourceAsStream("/priceIcon.png"))
    }
}