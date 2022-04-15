package consoleUI

import colored
import entites.User
import fakeRepo.FakeUser
import validationRules.UserValidator

class RegisterMenu {
    companion object {
        fun register() {
            println("~~~ SIGN UP ~~~")
            print("Enter firstname: ")
            try {
                var inputFirstName = readLine()
                print("Enter lastname: ")
                var inputLastName = readLine()
                print("Enter mail: ")
                var inputMail = readLine()
                print("Enter username: ")
                var inputUsername = readLine()
                print("Enter password: ")
                var inputPassword = readLine()
                print("Enter confirm password: ")
                var inputConfirmPassword = readLine()

                if (!UserValidator.userRegisterValidate(
                        inputFirstName!!,
                        inputLastName!!,
                        inputMail!!,
                        inputUsername!!,
                        inputPassword!!,
                        inputConfirmPassword!!
                    )
                ) {
                    UserValidator.showMessages()
                    UserValidator.messagesClear()
                    register()
                } else {
                    var newUser = User(
                        inputUsername!!,
                        inputMail!!,
                        inputPassword!!,
                        inputFirstName!!,
                        inputLastName!!,
                    )
                    FakeUser.users.add(newUser)
                    colored { println("Ugurla qeydiyyatdan kecdiniz!\nGiris ede bilersiz".green.italic) }
                    LoginMenu.login()
                }

            } catch (ex: Exception) {
                colored { println("Melimatları duzgun doldurun!".red.bold) }
                register()
            }

        }
    }
}