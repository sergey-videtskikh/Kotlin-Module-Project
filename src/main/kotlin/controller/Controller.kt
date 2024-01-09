package controller

import model.Archive
import model.Model
import model.Note
import view.MenuItem
import view.MenuList
import view.View
import java.util.Scanner

class Controller(private val view: View, private val model: Model) {

    private var activeArchive: Archive? = null
    private var activeNote: Note? = null

    private val scanner = Scanner(System.`in`)

    fun run() {
        archiveState()
    }

    private fun archiveState() {
        val archiveMenu = createArchiveMenu()
        view.printMenu(archiveMenu)
        val menuItem = takeMenuItem(archiveMenu)

        menuItem.action()
    }

    private fun createArchiveState() {
        view.printEnterArchiveName()
        val name = takeStringFromConsole()
        createArchive(name)

        archiveState()
    }

    private fun notesState() {
        val notesMenu = createNotesMenu()
        view.printMenu(notesMenu)
        val menuItem = takeMenuItem(notesMenu)

        menuItem.action()
    }

    private fun createNoteState() {
        view.printEnterNoteName()
        val name = takeStringFromConsole()
        view.printEnterNoteText()
        val text = takeStringFromConsole()
        createNote(name, text)

        notesState()
    }

    private fun noteState() {
        view.printNote(activeNote!!)

        notesState()
    }

    private fun createArchiveMenu(): MenuList {
        val menuList = MenuList()
        menuList.items.add(MenuItem("Создать архив") { createArchiveState() })
        for (archive in model.archives) {
            menuList.items.add(MenuItem(archive.name) {
                activeArchive = archive
                notesState()
            })
        }
        menuList.items.add(MenuItem("Выход") { exit() })

        return menuList
    }

    private fun createNotesMenu(): MenuList {
        val menuList = MenuList()
        menuList.items.add(MenuItem("Создать заметку") { createNoteState() })
        for (note in activeArchive!!.notes) {
            menuList.items.add(MenuItem(note.name) {
                activeNote = note
                noteState()
            })
        }
        menuList.items.add(MenuItem("Выход") { archiveState() })

        return menuList
    }

    private fun exit() {
        view.theEnd()
    }


    private fun takeMenuItem(menuList: MenuList): MenuItem {
        var i: Int
        while (true) {
            try {
                val string = scanner.nextLine()
                i = Integer.parseInt(string)
            } catch (e: NumberFormatException) {
                view.printNotNumberMessage()
                continue
            }
            if (i !in 0..menuList.items.lastIndex) {
                view.printNoSuchMenuItem()
                continue
            }
            break
        }

        return menuList.items[i]
    }

    private fun takeStringFromConsole(): String {
        var string: String
        while (true) {
            string = scanner.nextLine()
            if (string.isBlank()) {
                view.printEmptyStringEntered()
            } else {
                break
            }
        }

        return string
    }

    private fun createArchive(name: String) {
        model.addArchive(Archive(name))
    }

    private fun createNote(name: String, text: String) {
        activeArchive!!.addNote(Note(name, text))
    }

}