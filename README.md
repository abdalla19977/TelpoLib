# TelpoDeviceHelper

A comprehensive wrapper library for Telpo M8 device functionalities including printer, LCD screen, and cash drawer.

## Features

- Printer management
  - Text printing with custom formatting
  - Table printing
  - Image printing
  - Paper feed control
  - Text alignment
- LCD customer display control
- Cash drawer operations
- Device initialization handling

## Installation

Add the following dependency to your project:

```gradle
implementation 'com.example:telpo-device-helper:1.0.0'
```

## Usage

### Basic Initialization
```kotlin
val device = TelpoDeviceHelper()
device.init(this) // Initialize all device components
```

### Print Operations
```kotlin
device.apply {
    // Basic text printing
    printText("Regular text\n")
    
    // Formatted text
    printText("Bold text\n", isBold = true)
    printText("Large text\n", textSize = 35)
    printText("Bold italic\n", isBold = true, isItalic = true)
    
    // Table printing
    val texts = arrayOf("Item", "Qty", "Price")
    val widths = intArrayOf(200, 100, 150)
    val align = intArrayOf(0, 1, 2)  // 0:left, 1:center, 2:right
    printTable(texts, widths, align, 27)
    
    startPrinting()
}
```

### LCD Display Control
```kotlin
val displayBitmap: Bitmap = // your display bitmap
device.sendLcdDigital(displayBitmap)
```

### Cash Drawer Control
```kotlin
device.openDrawer() // Opens cash drawer with automatic closure
```

### Complete Receipt Example
```kotlin
val device = TelpoDeviceHelper()
device.init(this)

btnPrint.setOnClickListener {
    device.apply {
        setAlign(1) // Center
        printText("RECEIPT\n", textSize = 35, isBold = true)
        
        setAlign(0) // Left
        printText("Date: ${getCurrentDate()}\n")
        
        // Header
        val headers = arrayOf("Item", "Qty", "Price")
        val widths = intArrayOf(200, 100, 150)
        val align = intArrayOf(0, 1, 2)
        printTable(headers, widths, align, 27)
        
        feedPaper(2)
        startPrinting()
        openDrawer()
        releasePrinter()
    }
}
```

## API Reference

### Main Methods

| Method | Description |
|--------|-------------|
| `init(context: Context)` | Initializes all device components |
| `printText(text: String, textSize: Int = 27, isBold: Boolean = false, isItalic: Boolean = false)` | Prints formatted text |
| `printTable(texts: Array<String>, width: IntArray, align: IntArray, fontSize: Int)` | Prints formatted table |
| `printBitmap(bitmap: Bitmap)` | Prints bitmap image |
| `feedPaper(lines: Int)` | Controls paper feed |
| `setAlign(align: Int)` | Sets text alignment |
| `sendLcdDigital(bitmap: Bitmap)` | Controls LCD display |
| `startPrinting()` | Executes print job |
| `releasePrinter()` | Releases device resources |
| `openDrawer()` | Controls cash drawer |

## Device Requirements

- Telpo M8 device
- Android API Level 21+
- Telpo SDK integration

## Technical Notes

### Printer Settings
- Default text size: 27
- Gray level: 5
- Alignment options: 
  - 0: Left
  - 1: Center
  - 2: Right

### Cash Drawer
- Auto-close after 500ms
- Controlled via MoneyBox API

### LCD Display
- Uses SimpleSubLcd API
- Accepts bitmap images for display

## Error Handling

All operations include basic error handling. For production use, implement additional error handling:

```kotlin
try {
    device.apply {
        printText("Test\n")
        startPrinting()
    }
} catch (e: Exception) {
    Log.e("TelpoDevice", "Printing error", e)
    // Handle error
} finally {
    device.releasePrinter()
}
```

## License

MIT License

Copyright (c) 2024 [Your Name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
