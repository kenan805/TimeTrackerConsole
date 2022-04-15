package fakeRepo

import entites.Category

class FakeCategory {
    companion object {
        var cat1 = Category("Test1", 1)
        var cat2 = Category("Test2", 1)
        var cat3 = Category("Test3", 2)
        var cat4 = Category("Test4",3)
        var cat5 = Category("Test4",5)

        var categories: MutableList<Category> = mutableListOf(cat1, cat2, cat3, cat4, cat5)

    }
}