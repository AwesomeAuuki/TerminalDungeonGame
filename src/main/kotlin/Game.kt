import java.lang.Thread.sleep

data class User(var name: String = "Adventurer", var health: Int) {
    fun boot() = Game.start(this)
}


object Game {
    var rand_ch: (Int, Int) -> Int = { min, max -> (min..max).random() }
    var enemies = listOf("spider", "pixie", "goblin", "boss")
    var hp = if ("boss" in enemies) (150..200).random() else 100
    private val enemy = enemies.random()

    fun start(user: User) {
        val enemy = User(enemy, hp)
        user.health = if (rand_ch(1, 10) == 5) 125 else 100
        sleep(1L)
        println("Welcome ${user.name.capitalize()} to this very epic game!")
        sleep(2L)
        println("A wild ${enemy.name.capitalize()} has appears!")
        sleep(2L)
        println("Get ready to fight! ${user.name.capitalize()}!!!")
        sleep(3L)

        fun attack(): String? {
            println("\n\n${enemy.name.capitalize()}'s Health: ${enemy.health}, ${user.name.capitalize()}'s Health: ${user.health}\n(\"Choose from the numbers below\")")
            println("\n1) Basic Attack\n")
            return readLine()
        }

        do {
            sleep(2L)
            val choice = attack()
            sleep(1L)
            val rand = rand_ch(1, 6)
            when (choice) {
                "::nuke" -> {
                    enemy.health = 0

                    println("Executing... Done!\nRestarting game!")
                    main()
                }
                "1" -> {
                    if (enemy.health <= 0) {
                        println("\nYou've managed to defeat ${enemy.name.capitalize()}")
                        break
                    }
                    println("_".repeat(30))
                    println("Doing a basic attack! ${enemy.name.capitalize()}'s Health is now ${enemy.health}!")
                    println("_".repeat(30))
                    if ((1..6).random() == 3) println("${user.name.capitalize()} missed!") else enemy.health -= (1..35).random()

                    if (rand == 3) println("${enemy.name.capitalize()} missed!") else {
                        user.health -= rand_ch(10, 20)
                        if (user.health <= 0) {
                            println("\nYou've managed to defeat ${user.name.capitalize()}")
                            break
                        }
                        println("${enemy.name.capitalize()} managed to hit you!\nyour health is now ${user.health}!")
                    }
                    println("_".repeat(30))
                }
            }
        } while (enemy.health > 0 && user.health > 0)

        when {
            user.health <= 0 -> println("You've lost!")
            enemy.health <= 0 -> println("you've won!")
            else -> println("You've both tied!")
        }
    }

}