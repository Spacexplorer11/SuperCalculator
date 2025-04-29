import javax.swing.JOptionPane
import kotlin.math.pow
import kotlin.system.exitProcess

fun add(a: Double, b: Double) = a + b
fun subtract(a: Double, b: Double) = a - b
fun multiply(a: Double, b: Double) = a * b
fun divide(a: Double, b: Double): Double {
    return a / b
}

fun power(a: Double, b: Double) = a.pow(b)

val numberWords = mapOf(
    "zero" to "0",
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
    "ten" to "10",
    "eleven" to "11",
    "twelve" to "12",
    "thirteen" to "13",
    "fourteen" to "14",
    "fifteen" to "15",
    "sixteen" to "16",
    "seventeen" to "17",
    "eighteen" to "18",
    "nineteen" to "19",
    "twenty" to "20"
)

val operatorWords = mapOf(
    "plus" to "+",
    "add" to "+",
    "minus" to "-",
    "subtract" to "-",
    "x" to "*",
    "times" to "*",
    "multiplied by" to "*",
    "multiply" to "*",
    "divided by" to "/",
    "divide" to "/",
    "over" to "/",
    "raised to" to "^",
    "raised to the power of" to "^",
    "to the power of" to "^",
    "power" to "^"
)

val operators = listOf("^", "/", "*", "+", "s")

fun translateInput(input: String): String {
    var input = input.lowercase()
    for (i in numberWords.keys) {
        if (input.contains(i)) {
           input = input.replace(i, numberWords[i]!!)
        }
    }
    for (i in operatorWords.keys) {
        if (input.contains(i)) {
            input = input.replace(i, operatorWords[i]!!)
        }
    }
    if (input.contains(Regex("""/ *0(\.0*)?"""))) {
        JOptionPane.showMessageDialog(null, "Cannot divide by zero")
        return "EXIT"
    }
    return input
}

fun calculate(calculation: String): Double {
    var result = 0.0
    when {
        calculation.contains("+") -> {
            val numbers = calculation.split("+").map { it.trim().toDouble() }
            result = add(numbers[0], numbers[1])
        }

        calculation.contains("-") -> {
            val numbers = calculation.split("-").map { it.trim().toDouble() }
            result = subtract(numbers[0], numbers[1])
        }

        calculation.contains("*") -> {
            val numbers = calculation.split("*").map { it.trim().toDouble() }
            result = multiply(numbers[0], numbers[1])
        }

        calculation.contains("/") -> {
            val numbers = calculation.split("/").map { it.trim().toDouble() }
            result = divide(numbers[0], numbers[1])
        }

        calculation.contains("^") -> {
            val numbers = calculation.split("^").map { it.trim().toDouble() }
            result = power(numbers[0], numbers[1])
        }

        else -> {
            JOptionPane.showMessageDialog(null, "Invalid operation")
        }
    }

    return result
}

fun askRetry() : String {
    val retry = JOptionPane.showConfirmDialog(
        null,
        "Do you want to perform another calculation?",
        "Retry",
        JOptionPane.YES_NO_OPTION
    )
    return if (retry == JOptionPane.YES_OPTION) {
        "Retry"
    }
    else {
        "EXIT"
    }
}

fun calculator(): String {
    JOptionPane.showMessageDialog(null, "Welcome to the calculator!")
    var calculation = JOptionPane.showInputDialog("Please write your calculation (max 2 numbers):").lowercase()
    if (calculation.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No input provided. Exiting.")
        return "EXIT"
    }
    calculation = translateInput(calculation)
    if (calculation == "EXIT") {
        return "EXIT"
    }
    else if (calculation == "Retry") {
        return "Retry"
    }
    val operatorCount = operators.sumOf { op ->
        calculation.count { it.toString() == op }
    }
    calculation = calculation.replace(" ", "")
    if (operatorCount == 1) {
        calculation = calculate(calculation).toString()
    }
    else if (operatorCount >= 2) {
        var multipleOperators = true
        var calculationShortened: String
        var calculationIndex: Int
        var result: Double
        while (multipleOperators) {
            for ( i in operators) {
                if (calculation.contains(i)) {
                    calculationIndex = calculation.indexOf(i)
                    val leftEnd = calculationIndex
                    var leftStart = leftEnd - 1
                    println("Operator: $i, Index: $calculationIndex, Calculation: $calculation")
                    println(calculation[leftStart])
                    while (leftStart >= 0 && calculation[leftStart].isDigit()) {
                        leftStart--
                    }
                    leftStart++ // adjust to real start
                    println("Left index: $leftStart")
                    val leftOperand = calculation.substring(leftStart, leftEnd)
                    val rightStart = calculationIndex + 1
                    var rightEnd = rightStart
                    while (rightEnd < calculation.length && calculation[rightEnd].isDigit()) {
                        rightEnd++
                    }
                    val rightOperand = calculation.substring(rightStart, rightEnd)
                    println("Rightend $rightEnd")
                    println("Left Operand: $leftOperand, Right Operand: $rightOperand")
                    calculationShortened = leftOperand + i + rightOperand
                    println(calculationShortened)
                    result = calculate(calculationShortened)
                    calculation = calculation.replace(calculationShortened, result.toString())
                    print("---------------------------------------------------------------------------\nCalculation: $calculation")
                    val operatorCount = operators.sumOf { op ->
                        calculation.count { it.toString() == op }
                    }
                    if (operatorCount == 1) {
                        calculation = calculate(calculation).toString()
                        break
                    }
                }
            }
            val operatorCount = operators.sumOf { op ->
                calculation.count { it.toString() == op }
            }
            multipleOperators = operatorCount > 0
        }
    }
    JOptionPane.showMessageDialog(null, "Result $calculation")

    return askRetry()
}


fun main() {
    var result = calculator()
    while (result != "EXIT") {
        if (result == "Retry") {
            result = calculator()
        }
        }
    // Exit the program
    JOptionPane.showMessageDialog(null, "Thank you for using the calculator!")
    exitProcess(0)
}

