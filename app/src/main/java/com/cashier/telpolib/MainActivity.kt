package com.cashier.telpolib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cashier.telpo_printer.TelpoPrintHelper
import com.cashier.telpo_printer.wait

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val printer = TelpoPrintHelper()
        printer.init(applicationContext)

        wait(3000){
            printer.apply {
                printText("Hello\n")
                printText("Hello\n")
                printText("Hello\n")
                startPrinting()
                releasePrinter()
                openDrawer()
            }
        }


    }
}