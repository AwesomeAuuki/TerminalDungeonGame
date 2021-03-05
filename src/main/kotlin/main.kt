/***
 * I just wanted to play around with Kotlin's Terminal!
 * Hope you people, enjoy..
 * It's really simple dungeon game!
 */

fun main() {
    println("_".repeat(25))
    println("Hello there!\nWelcome to a test terminal game!\nplease enter your name or you can leave it blank:")
    println("_".repeat(25))
    var name = readLine()

    if (name.isNullOrBlank()) name = "Adventurer"

    User(name, 100).boot()
}