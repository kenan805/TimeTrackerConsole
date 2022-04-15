package entites

import fakeRepo.FakeCategory
import fakeRepo.FakeUser

private var CATEGORY_ID: Int = 1

class Category {
    var id: Int = CATEGORY_ID
    var userId: Int
    var name: String

    constructor(name: String, userId: Int) {
        this.name = name
        this.userId = userId
        CATEGORY_ID++
    }

    fun showCategory() {
        println("\nCategory id: ${this.id}")
        println("Category name: ${this.name}")
        println("User name: ${FakeUser.users.find { u -> u.id == this.userId }?.userName}")
    }
}