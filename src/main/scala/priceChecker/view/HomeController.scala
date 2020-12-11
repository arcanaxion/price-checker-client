package priceChecker.view

import scala.collection.mutable.ArrayBuffer
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, ListView, TextField}
import scalafx.collections.ObservableBuffer
import scalafx.Includes._

@sfxml
class HomeController(private val itemNamesList: ListView[String], 
private val name: TextField, private val price: TextField) {
    
    def checkPrice(action: ActionEvent): Unit = {
        
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
