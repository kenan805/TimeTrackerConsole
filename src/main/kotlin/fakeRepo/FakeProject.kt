package fakeRepo

import entites.Project
import java.time.LocalDate

class FakeProject {
    companion object {
        var prj1DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.now(), 250))
        var prj2DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.parse("2022-04-12"), 3450))
        var prj3DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.parse("2022-04-11"), 1234))
        var prj4DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.now(), 2156))
        var prj5DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.now(), 890))
        var prj6DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.parse("2022-04-12"), 535))
        var prj7DayTime: MutableMap<LocalDate, Int> = mutableMapOf(Pair(LocalDate.now(), 1240))


        private var prj1 = Project("PayPark", 1, 1, prj1DayTime)
        private var prj2 = Project("Courier", 3, 2, prj2DayTime)
        private var prj3 = Project("Office", 1, 1, prj3DayTime)
        private var prj4 = Project("Home", 2, 1, prj4DayTime)
        private var prj5 = Project("Trainer", 2, 1, prj5DayTime)
        private var prj6 = Project("Taxi", 4, 3, prj6DayTime)
        private var prj7 = Project("Mobile", 5, 5, prj7DayTime)

        var projects: MutableList<Project> = mutableListOf(prj1, prj2, prj3, prj4, prj5, prj6, prj7)
    }
}