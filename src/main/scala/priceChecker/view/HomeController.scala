package priceChecker.view

import scala.collection.mutable.ArrayBuffer
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TextField, ChoiceBox}
import scalafx.collections.ObservableBuffer
import scalafx.Includes._

import priceChecker.MainApp

@sfxml
class HomeController(private val itemNamesList: ChoiceBox[String], private val price: TextField) {
    
    

    def checkPrice(action: ActionEvent): Unit = {
        // get selected item name
        val iname = itemNamesList.selectionModel().selectedItem.value

        // if item name is not null (no item selected), continue
        if (iname != null) {
            // query server for item price
            val data = MainApp.queryPrice(iname)

            // update price on client GUI
            updatePrice(data)
            
            // creates new socket
            MainApp.createNewSocket()
        }
    }

    def updateItemNames(itemNames: ArrayBuffer[String]) {
        itemNamesList.items = new ObservableBuffer[String]() ++= itemNames
    }
    
    def updatePrice(iprice: String) {
        price.text = iprice
    }

}
