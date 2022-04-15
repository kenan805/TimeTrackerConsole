package validationRules

import colored
import fakeRepo.FakeUser

class UserValidator {
    companion object {
        private var messages: MutableList<String> = mutableListOf()
        private var isValid: Boolean = true

        fun userRegisterValidate(
            firstName: String,
            lastName: String,
            mail: String,
            userName: String,
            password: String,
            confirmPassword: String,
        ): Boolean {
            if (firstName.isEmpty() || lastName.isEmpty() || mail.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                messages += "Melumatlari tam doldurun!"
                isValid = false
                return isValid
            }
            if (firstName.length < 2) {
                messages += "Ad minimum 2 hərfli olmalıdır!"
                isValid = false
            }
            if (lastName.length < 5) {
                messages += "Soyad minimum 5 hərfli olmalıdır!"
                isValid = false
            }
            if (!mail.matches(Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"))) {
                messages += "Mail formati duzgun deyil!"
                isValid = false
            }
            if (userName.last() != ' ' && userName.contains(" ")) {
                messages += "Username-de 1sozden ibaret olmalidir!"
                isValid = false
            }
            if (password.length < 8) {
                messages += "Parol minimum 8 rəqəm olmalıdır!"
                isValid = false
            }
            if (!password.matches(Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"))) {
                messages += "Parolda ən az bir böyük hərf, bir kiçik hərf və rəqəm olmalıdır!"
                isValid = false
            } else if (password != confirmPassword) {
                messages += "Parollar uygun deyil"
                isValid = false
            }
            else if (FakeUser.users.any{ u->u.userName==userName }) {
                messages += "Bu adda username artiq var!"
                isValid = false
            }

            return isValid
        }

        fun userLoginValidate(userName: String, password: String): Boolean {
            if (userName.isEmpty() || password.isEmpty()) {
                messages += "Doldurulmayan yerler var!"
                isValid = false
            }
            return isValid
        }

        fun showMessages() {
            if (messages.isNotEmpty()) {
                messages.forEach {
                    colored { println(it.red.bold) }
                }
            }
        }

        fun messagesClear() {
            messages.clear()
        }
    }
}