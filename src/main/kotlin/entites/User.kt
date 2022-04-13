package entites

class User {
    var userName: String
    var mail: String
    var password: String
    var firstName: String
    var lastName: String

    constructor(userName: String, mail: String, password: String, firstName: String, lastName: String) {
        this.mail = mail.replace(" ","")
        this.password = password.replace(" ","")
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName.replace(" ","")
    }

}
