package com.cashier.telpo_printer

import android.content.Context
import android.graphics.Bitmap
import com.common.apiutil.lcd.SimpleSubLcd
import com.common.apiutil.moneybox.MoneyBox
import com.common.apiutil.printer.UsbThermalPrinter
import com.common.apiutil.util.SDKUtil

class TelpoPrintHelper {

    private lateinit var usbThermalPrinter: UsbThermalPrinter
    private lateinit var simpleSubLcd: SimpleSubLcd

    fun init(context: Context) {
        SDKUtil.getInstance(context).initSDK()
        simpleSubLcd = SimpleSubLcd(context)
        simpleSubLcd.init()
        usbThermalPrinter = UsbThermalPrinter(context)
        usbThermalPrinter.start(0)
    }

    fun printText(
        text: String,
        textSize:Int = 27,
        isBold: Boolean = false,
        isItalic: Boolean = false
    ) {
        usbThermalPrinter.setGray(5)
        usbThermalPrinter.setBold(isBold)
        usbThermalPrinter.setItalic(isItalic)
        usbThermalPrinter.setTextSize(textSize)
        usbThermalPrinter.addString(text)
    }

    fun printTable(texts: Array<String>, width: IntArray, align: IntArray, fontSize: Int) {
        usbThermalPrinter.addColumnsString(texts, width, align, fontSize)
    }

    fun printBitmap(bitmap: Bitmap) {
        try {
            usbThermalPrinter.printLogo(bitmap.copy(Bitmap.Config.ARGB_8888, true), true)
        } catch (_: Exception) {
        }
    }


    fun feedPaper(lines: Int) {
        usbThermalPrinter.addString("\n".repeat(lines))
    }

    fun setAlign(align: Int) {
        usbThermalPrinter.setAlgin(align)
    }

    fun sendLcdDigital(bitmap: Bitmap) {
        simpleSubLcd.show(bitmap)
    }

    fun startPrinting() {
        try {
            usbThermalPrinter.printString()
        } catch (_: Exception) {

        }
    }

    fun releasePrinter() {
        usbThermalPrinter.stop()
    }

    fun openDrawer() {
        MoneyBox.boxControl(1, 1)
        wait(500) {
            MoneyBox.boxControl(1, 0)
        }
    }
}