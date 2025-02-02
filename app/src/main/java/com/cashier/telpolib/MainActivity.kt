package com.cashier.telpolib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cashier.telpo_printer.TelpoDeviceHelper
import com.cashier.telpo_printer.wait

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val telpoDevice = TelpoDeviceHelper()
        telpoDevice.init(this)

        wait(3000) {
            telpoDevice.apply {
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