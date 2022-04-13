package consoleUI

import fakeRepo.FakeUser
import colored
import validationRules.UserValidator

class LoginMenu {
    companion object {
        fun login() {
            println("~~~ SIGN IN ~~~")
            print("Enter username: ")
            var inputUsername = readLine()
            print("Enter password: ")
            var inputPassword = readLine()

            if (!UserValidator.userLoginValidate(inputUsername!!, inputPassword!!)) {
                UserValidator.showMessages()
                UserValidator.messagesClear()
                login()
            }

            var user = FakeUser.users.find { u -> u.userName == inputUsername && u.password == inputPassword }

            if (user != null) {
                println("Welcome ${user.userName}")
            } else {
                colored { println("User not found! Try again...".red.bold) }
                login()
            }
        }

    }
}
