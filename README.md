# TelpoDeviceHelper

Wrapper library for Telpo M8 device functionalities including printer, LCD screen, and cash drawer.

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

```build.gradle(app)
implementation 'com.github.aballa19977:TelpoLib:1.4'
```

```build.gradle(project)
repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
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
    val widths = intArrayOf(1, 1, 1)
    val align = intArrayOf(0, 1, 2)  // 0:left, 1:center, 2:right
    printTable(texts, widths, align, 27)
    
    startPrinting()
}
```

### LCD Display Control
```kotlin
val displayBitmap: Bitmap = // your display bitmap must be
device.sendLcdDigital(displayBitmap)
```

### Cash Drawer Control
```kotlin
device.openDrawer() // Opens cash drawer with automatic closure
```

## API Reference

### Main Methods

| Method                                                                                            | Description                       |
|---------------------------------------------------------------------------------------------------|-----------------------------------|
| `init(context: Context)`                                                                          | Initializes all device components |
| `printText(text: String, textSize: Int = 27, isBold: Boolean = false, isItalic: Boolean = false)` | Prints formatted text             |
| `printTable(texts: Array<String>, width: IntArray, align: IntArray, fontSize: Int)`               | Prints formatted table            |
| `printBitmap(bitmap: Bitmap)`                                                                     | Prints bitmap image               |
| `feedPaper(lines: Int)`                                                                           | Controls paper feed               |
| `setAlign(align: Int)`                                                                            | Sets text alignment               |
| `sendLcdDigital(bitmap: Bitmap)`                                                                  | Controls LCD display              |
| `startPrinting()`                                                                                 | Executes print job                |
| `releasePrinter()`                                                                                | Releases device resources         |
| `openDrawer()`                                                                                    | Controls cash drawer              |


## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
