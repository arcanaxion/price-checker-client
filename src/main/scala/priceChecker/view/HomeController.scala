package priceChecker.view

import scala.collection.mutable.ArrayBuffer
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TextField, ChoiceBox}
import scalafx.collections.ObservableBuffer
import scalafx.Includes._

import priceChecker.MainApp

@sfxml
class HomeController(private val itemNamesList: ChoiceBox[String], 
private val name: TextField, private val price: TextField) {

    def checkPrice(action: ActionEvent): Unit = {
        // get selected item name
        val iname = itemNamesList.selectionModel().selectedItem.value

        // if item name is not null (no item selected), continue
        if (iname != null) {
            // query server for item price
            val data = MainApp.queryPrice(iname)

            // update item name and price on client GUI
            updateName(iname)
            updatePrice(data)
            
            // creates new socket
            MainApp.createNewSocket()
        }
    }

    def updateItemNames(itemNames: ArrayBuffer[String]) {
        itemNamesList.items = new ObservableBuffer[String]() ++= itemNames
    }
    
    def updateName(iname: String) {
        name.text = iname
    }

    def updatePrice(iprice: String) {
        price.text = iprice
    }

}
