package entites

import fakeRepo.FakeCategory
import fakeRepo.FakeProject
import fakeRepo.FakeUser

private var USER_ID: Int = 1

class User {
    var id: Int = USER_ID
    var userName: String
    var mail: String
    var password: String
    var firstName: String
    var lastName: String
    //var dailyTimeSpent: Int
    //var projects: MutableList<Project>

    constructor(
        userName: String,
        mail: String,
        password: String,
        firstName: String,
        lastName: String,
        //projects: MutableList<Project>,
    ) {
        this.mail = mail.replace(" ", "")
        this.password = password.replace(" ", "")
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName.replace(" ", "")
        USER_ID++
    }

    fun showProjectsByUser() {
        var projects = FakeProject.projects.filter { p -> p.userId == this.id }
        projects.forEach { p -> p.showProject() }
    }

    fun showCategoriesByUser() {
        var categories = FakeCategory.categories.filter { c -> c.userId == this.id }.sortedBy { c -> c.id }
        categories.forEach { c -> c.showCategory() }
    }

    fun showUser() {
        println("\nUser id: ${this.id}")
        println("Firstname: ${this.firstName}")
        println("Lastname: ${this.lastName}")
        println("Username: ${this.userName}")
        println("Mail: ${this.mail}")
        println("Password: ${this.password}")
    }

}
