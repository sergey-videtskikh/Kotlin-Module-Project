package view

import model.Note

class View {
    fun printMenu(menuList: MenuList) {
        for ((index, item) in menuList.items.withIndex()) {
            println("$index: $item")
        }
    }
    fun printNote(note: Note) {
        println(note)
    }
    fun printEnterArchiveName() {
        println("Введите название архива:")
    }
    fun printEnterNoteName() {
        println("Введите название заметки:")
    }
    fun printEnterNoteText() {
        println("Введите текст заметки:")
    }
    fun theEnd() {
        println("Конец выполнения программмы. До свидания! ")
    }
    fun printNotNumberMessage() {
        println("Вы ввели не число. Повторите ввод.")
    }

    fun printNoSuchMenuItem() {
        println("Такого пункта меню нет. Повторите ввод.")
    }

    fun printEmptyStringEntered() {
        println("Вы ввели пустую строку. Повторите ввод.")
    }
}