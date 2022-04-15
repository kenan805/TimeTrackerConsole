package consoleUI

import colored
import entites.Category
import entites.Project
import fakeRepo.FakeCategory
import fakeRepo.FakeProject
import fakeRepo.FakeUser
import helpers.DatetimeHelper
import java.time.LocalDate
import java.time.LocalTime
import java.util.Timer
import kotlin.system.exitProcess

var choice: Int = -1

class UserMenu {
    companion object {
        fun home(userId: Int) {
            println("~~~ USER MENU ~~~")
            println("1 - Today time spent")
            println("2 - Start project")
            println("3 - Stop project")
            println("4 - View projects")
            println("5 - View categories")
            println("6 - Project add")
            println("7 - Category add")
            println("8 - Project delete")
            println("9 - Category delete")
            println("10 - View profile")
            println("11 - Logout")
            println("0 - Exit")
            print("Please choose: ")

            try {
                choice = Integer.parseInt(readLine())
            } catch (ex: Exception) {
            }

            when (choice) {
                0 -> {
                    colored { println("Goodbye...".green.italic) }
                    exitProcess(0)
                }
                1 -> println("Today: ${todayTime(userId)}")
                2 -> startProject(userId)
                3 -> endProject(userId)
                4 -> showProjects(userId)
                5 -> showCategories(userId)
                6 -> projectAdd(userId)
                7 -> categoryAdd(userId)
                8 -> projectDelete(userId)
                9 -> categoryDelete(userId)
                10 -> showUserInfo(userId)
                11 -> MainMenu.main()
                else -> {
                    colored { println("Invalid choise! Try again...".red.bold) }
                    home(userId)
                }
            }
            menuBackSide(userId)

        }

        private fun endProject(userId: Int) {
            println("~~~ END PROJECT ~~~")
            try {
                if (FakeProject.projects.all { p -> !p.isStarted }) {
                    colored { println("Hal hazirda sizin hec bir proyektiniz baslanmayib!".yellow.italic) }
                    menuBackSide(userId)
                }
                print("Baslanmis projects(id - name): ")
                for (project in FakeProject.projects.filter { p -> p.userId == userId && p.isStarted }
                    .sortedBy { p -> p.id }) {
                    print("{ ${project.id} - ${project.name} } ")
                }
                print("\nEnter project id: ")
                var projectId = Integer.parseInt(readLine())
                if (!FakeProject.projects.filter { p -> p.userId == userId && p.isStarted }
                        .any { p -> p.id == projectId }) {
                    colored { println("Bu userde bele id-de baslanmis project yoxdur!".red.bold) }
                    startProject(userId)
                }
                var project = FakeProject.projects.find { p -> p.id == projectId }
                project?.endedTime = LocalTime.now()
                var differenceTime = project?.endedTime?.toSecondOfDay()!! - project?.startedTime?.toSecondOfDay()!!

                var value = project.dailyTimeSpent[LocalDate.now()]!! + differenceTime
                project.dailyTimeSpent[LocalDate.now()] = value

                project.isStarted = false

                colored { println("Projekt timer ended!".green.italic) }
                menuBackSide(userId)
            } catch (ex: Exception) {
                colored { println("Melimatları duzgun doldurun!".red.bold) }
                endProject(userId)
            }

        }

        private fun startProject(userId: Int) {
            println("~~~ START PROJECT ~~~")
            try {
                if (FakeProject.projects.all { p -> p.isStarted }) {
                    colored { println("Hal hazirda sizin butun proyektleriniz baslayib!".yellow.italic) }
                    menuBackSide(userId)
                }
                print("Baslanmamis projects(id - name): ")
                for (project in FakeProject.projects.filter { p -> p.userId == userId && !p.isStarted }
                    .sortedBy { p -> p.id }) {
                    print("{ ${project.id} - ${project.name} } ")
                }
                print("\nEnter project id: ")
                var projectId = Integer.parseInt(readLine())
                if (!FakeProject.projects.filter { p -> p.userId == userId && !p.isStarted }
                        .any { p -> p.id == projectId }) {
                    colored { println("Bu userde bele id-de baslanmamis project yoxdur!".red.bold) }
                    startProject(userId)
                }
                var project = FakeProject.projects.find { p -> p.id == projectId }
                if (project?.dailyTimeSpent!!.isEmpty() || !project?.dailyTimeSpent.keys.contains(LocalDate.now())) {
                    project.dailyTimeSpent.plus(Pair(LocalDate.now(), 0))
                }
                project.isStarted = true
                project.startedTime = LocalTime.now()

                colored { println("Projekt timer started!".green.italic) }
                menuBackSide(userId)
            } catch (ex: Exception) {
                colored { println("Melimatları duzgun doldurun!".red.bold) }
                startProject(userId)
            }
        }

        private fun showUserInfo(userId: Int) {
            var user = FakeUser.users.find { u -> u.id == userId }
            user?.showUser()
        }

        private fun categoryDelete(userId: Int) {
            println("~~~ CATEGORY DELETE ~~~")
            try {
                print("Categories(id - name): ")
                for (category in FakeCategory.categories.filter { c -> c.userId == userId }.sortedBy { c -> c.id }) {
                    print("{ ${category.id} - ${category.name} } ")
                }
                print("\nEnter category id: ")
                var categoryId = Integer.parseInt(readLine())
                if (!FakeCategory.categories.filter { c -> c.userId == userId }.any { c -> c.id == categoryId }) {
                    colored { println("Bu userde bele id-de category yoxdur!".red.bold) }
                    categoryDelete(userId)
                }

                FakeCategory.categories.removeIf { c -> c.id == categoryId }
                FakeProject.projects.removeAll { p -> p.categoryId == categoryId }
                colored { println("Category ugurla silindi!".green.italic) }
                menuBackSide(userId)
            } catch (ex: Exception) {
                colored { println("Melimatları duzgun doldurun!".red.bold) }
                categoryDelete(userId)
            }
        }

        private fun projectDelete(userId: Int) {
            println("~~~ PROJECT DELETE ~~~")
            try {
                print("Projects(id - name): ")
                for (project in FakeProject.projects.filter { p -> p.userId == userId }.sortedBy { p -> p.id }) {
                    print("{ ${project.id} - ${project.name} } ")
                }
                print("\nEnter project id: ")
                var projectId = Integer.parseInt(readLine())
                if (!FakeProject.projects.filter { p -> p.userId == userId }.any { p -> p.id == projectId }) {
                    colored { println("Bu userde bele id-de project yoxdur!".red.bold) }
                    projectDelete(userId)
                }

                FakeProject.projects.removeIf { p -> p.id == projectId }
                colored { println("Proyekt ugurla silindi!".green.italic) }
                menuBackSide(userId)
            } catch (ex: Exception) {
                colored { println("Melimatları duzgun doldurun!".red.bold) }
                projectDelete(userId)
            }
        }

        private fun categoryAdd(userId: Int) {
            println("~~~ CATEGORY ADD ~~~")
            print("Enter category name: ")
            var categoryName = readLine()
            var newCategory = Category(categoryName.toString(), userId)
            FakeCategory.categories.add(newCategory)
            colored { println("Category ugurla elave olundu!".green.italic) }
            menuBackSide(userId)
        }

        private fun menuBackSide(userId: Int) {
            colored { println("Geriye qayitmaq ucun '0' secin".yellow.bold) }
            print("Please choose: ")
            try {
                choice = Integer.parseInt(readLine())
            } catch (ex: Exception) {
            }
            when (choice) {
                0 -> {
                    home(userId)
                }
                else -> {
                    colored { println("Invalid choise! Try again...".red.bold) }
                    menuBackSide(userId)
                }
            }
        }

        private fun todayTime(userId: Int): LocalTime {
            var todayKey = LocalDate.now()
            var todayAllSeconds: Int = 0
            var todayProjects =
                FakeProject.projects.filter { p -> p.userId == userId && p.dailyTimeSpent.keys.contains(todayKey) }
            todayProjects.forEach { todayAllSeconds += it.dailyTimeSpent[todayKey]!! }
            return DatetimeHelper.secondsToTime(todayAllSeconds)
        }

        private fun showProjects(userId: Int) {
            if (FakeProject.projects.any { p -> p.userId == userId }) {
                FakeUser.users.find { u -> u.id == userId }?.showProjectsByUser()
            } else {
                colored { println("Hal hazirda sizin hec bir proyektiniz yoxdur!".yellow.italic) }
                menuBackSide(userId)
            }
        }

        private fun showCategories(userId: Int) {
            if (FakeProject.projects.any { p -> p.userId == userId }) {
                FakeUser.users.find { u -> u.id == userId }!!.showCategoriesByUser()
            } else {
                colored { println("Hal hazirda sizin hec bir categoriyaniz yoxdur!".yellow.italic) }
                menuBackSide(userId)
            }
        }

        private fun projectAdd(userId: Int) {
            println("~~~ PROJECT ADD ~~~")
            try {
                print("Enter project name: ")
                var projectName = readLine()
                print("Categories(id - name): ")
                for (category in FakeCategory.categories.filter { c -> c.userId == userId }.sortedBy { c -> c.id }) {
                    print("{ ${category.id} - ${category.name} } ")
                }
                print("\nEnter category id: ")
                var projectCatId = Integer.parseInt(readLine())
                if (!FakeCategory.categories.filter { c -> c.userId == userId }.any { c -> c.id == projectCatId }) {
                    colored { println("Bu userde bele id-de categoriya yoxdur!".red.bold) }
                    projectAdd(userId)
                }

                var newProject = Project(projectName.toString(), projectCatId, userId, mutableMapOf())
                FakeProject.projects.add(newProject)
                colored { println("Proyekt ugurla elave olundu!".green.italic) }
                menuBackSide(userId)
            } catch (ex: Exception) {
                colored { println("Melimatları duzgun doldurun!".red.bold) }
                projectAdd(userId)
            }
        }
    }
}


