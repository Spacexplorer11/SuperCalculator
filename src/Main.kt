import kotlin.math.pow
import javax.swing.JOptionPane

fun add(a: Double, b: Double) = a + b
fun subtract(a: Double, b: Double) = a - b
fun multiply(a: Double, b: Double) = a * b
fun divide(a: Double, b: Double): Double {
    if (b == 0.0) {
        JOptionPane.showMessageDialog(null, "Cannot divide by zero")
    }
    return a / b
}
fun power(a: Double, b: Double) = a.pow(b)


fun main() {
    JOptionPane.showMessageDialog(null, "Welcome to the calculator!")
    val calculation = JOptionPane.showInputDialog("Please write your calculation (max 2 numbers):")
    if (calculation == null || calculation.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No input provided. Exiting.")
        return
    }
    when {
        calculation.contains("+") -> {
            val numbers = calculation.split("+").map { it.trim().toDouble() }
            JOptionPane.showMessageDialog(null, "Result: ${add(numbers[0], numbers[1])}")
        }
        calculation.contains("-") -> {
            val numbers = calculation.split("-").map { it.trim().toDouble() }
            JOptionPane.showMessageDialog(null, "Result: ${subtract(numbers[0], numbers[1])}")
        }
        calculation.contains("*") -> {
            val numbers = calculation.split("*").map { it.trim().toDouble() }
            JOptionPane.showMessageDialog(null, "Result: ${multiply(numbers[0], numbers[1])}")
        }
        calculation.contains("/") -> {
            val numbers = calculation.split("/").map { it.trim().toDouble() }
            JOptionPane.showMessageDialog(null, "Result: ${divide(numbers[0], numbers[1])}")
        }
        calculation.contains("^") -> {
            val numbers = calculation.split("^").map { it.trim().toDouble() }
            JOptionPane.showMessageDialog(null, "Result: ${power(numbers[0], numbers[1])}")
        }
        else -> {
            JOptionPane.showMessageDialog(null, "Invalid operation")
        }
    }
}

/* fun openEditor(OS: String, textEditorPath: String, file: File) {
    if (OS.contains("mac")) {
        val process = ProcessBuilder("open", "-W", "-a", "TextEdit", file.path).start()
        process.waitFor()
    } else {
        Runtime.getRuntime().exec(arrayOf(textEditorPath, file.absolutePath))
    }
}


fun main() {
    val OS = System.getProperty("os.name").lowercase()
   var textEditorPath: String
    val file = File("calculation.txt")
    when {
        OS.contains("win") -> textEditorPath = "C:\\Program Files\\Notepad++\\notepad++.exe"
        OS.contains("mac") -> textEditorPath = "open"
        OS.contains("nix") || OS.contains("nux") -> textEditorPath = "/usr/bin/nano"
        else -> {
            println("Unsupported OS")
            println("Please type the path to your text editor:")
            textEditorPath = readLine() ?: ""
            return
        }
    }
    file.writeText("Please write your calculation( max 2 numbers ) below: ( Don't forget to save, CTRL + S or CMD + S! Also, quit/close the app when you are done ) \n")

    openEditor(OS, textEditorPath, file)

    val input = file.readText()
    val calculation = input.removePrefix("Please write your calculation( max 2 numbers ) below:( Remember to save, CTRL + S or CMD + S! Also, quit/close the app when you are done ) \n")
    println("Your calculation: $calculation")

    when {
        calculation.contains("+") -> {
            val numbers = calculation.split("+").map { it.trim().toDouble() }
            file.writeText("Result: ${add(numbers[0], numbers[1])}")
        }
        calculation.contains("-") -> {
            val numbers = calculation.split("-").map { it.trim().toDouble() }
            file.writeText("Result: ${subtract(numbers[0], numbers[1])}")
        }
        calculation.contains("*") -> {
            val numbers = calculation.split("*").map { it.trim().toDouble() }
            file.writeText("Result: ${multiply(numbers[0], numbers[1])}")
        }
        calculation.contains("/") -> {
            val numbers = calculation.split("/").map { it.trim().toDouble() }
            file.writeText("Result: ${divide(numbers[0], numbers[1])}")
        }
        calculation.contains("^") -> {
            val numbers = calculation.split("^").map { it.trim().toDouble() }
            file.writeText("Result: ${power(numbers[0], numbers[1])}")
        }
        else -> {
            file.writeText("Invalid operation")
        }
    }

    openEditor(OS, textEditorPath, file)

} */
