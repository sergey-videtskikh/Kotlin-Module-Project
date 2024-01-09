package model

data class Note(val name: String, val text: String) {
    override fun toString(): String {
        return "Note(name='$name', text='$text')"
    }
}