package view

data class MenuItem(val name: String, val action: () -> Unit) {
    override fun toString(): String = name
}