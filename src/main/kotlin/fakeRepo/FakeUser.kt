package fakeRepo

import entites.User

class FakeUser {
    companion object {
        private var user1 = User("Kenan805", "test1@gmail.com", "kenan123", "Kenan", "Idayatov")
        private var user2 = User("Revan299", "test2@gmail.com", "revan123", "Revan", "Idayatov")
        private var user3 = User("Nebi050", "test3@gmail.com", "nebi123", "Nebi", "Nebili")
        private var user4 = User("Hormet15", "test4@gmail.com", "hormet123", "Hormet", "Hemidov")
        private var user5 = User("Ramin111", "test5@gmail.com", "ramin123", "Ramin", "Abdullayev")

        var users = mutableListOf<User>(user1, user2, user3, user4, user5)
    }


}