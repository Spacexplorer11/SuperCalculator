import java.util.Locale.getDefault
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
    calculation.lowercase(getDefault())
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
        calculation.contains("x") -> {
            val numbers = calculation.split("x").map { it.trim().toDouble() }
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
    JOptionPane.showMessageDialog(null, "Thank you for using the calculator!")
}

