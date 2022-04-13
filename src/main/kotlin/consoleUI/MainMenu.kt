package consoleUI

import colored
import kotlin.system.exitProcess

class MainMenu {
    companion object {
        fun main() {
            println("Welcome NSPSOLUTIONS")
            println("1 - Sign in")
            println("2 - Sign up")
            println("0 - Exit")
            print("Please choose: ")
            var choice: Int = -1

            try {
                choice = Integer.parseInt(readLine())
            } catch (ex: Exception) {
            }

            when (choice) {
                0 -> {
                    colored { println("Goodbye...".green.italic) }
                    exitProcess(0)
                }
                1 -> LoginMenu.login()
                2 -> RegisterMenu.register()
                else -> {
                    colored { println("Invalid choise! Try again...".red.bold) }
                    main()
                }
            }
        }
    }
}