package entites

import fakeRepo.FakeCategory
import fakeRepo.FakeUser
import helpers.DatetimeHelper
import java.time.LocalDate
import java.time.LocalTime

private var PROJECT_ID: Int = 1

class Project {
    var id: Int = PROJECT_ID
    var name: String
    var categoryId: Int
    var userId: Int
    var dailyTimeSpent: MutableMap<LocalDate, Int>
    var isStarted: Boolean = false
    var startedTime: LocalTime? = null
    var endedTime: LocalTime? = null

    constructor(
        name: String,
        categoryId: Int,
        userId: Int,
        dailyTimeSpent: MutableMap<LocalDate, Int>,
    ) {
        this.name = name
        this.categoryId = categoryId
        this.userId = userId
        this.dailyTimeSpent = dailyTimeSpent
        PROJECT_ID++
    }

    fun showProject() {
        println("\nProject id: ${this.id}")
        println("Project name: ${this.name}")
        println("Project category name: ${FakeCategory.categories.find { c -> c.id == this.categoryId }?.name}")
        println("Project user name: ${FakeUser.users.find { u -> u.id == this.userId }?.userName}")
        for ((key, value) in this.dailyTimeSpent) {
            println("Date - time: $key - ${DatetimeHelper.secondsToTime(value)}")
        }
    }

}