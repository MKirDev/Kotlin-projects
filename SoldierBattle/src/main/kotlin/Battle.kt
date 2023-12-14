import kotlin.random.Random

class Battle {
    private var battleOver: Boolean = false
    private var firstTeam = Team()
    private var secondTeam = Team()
    private var count: Int = 0
    private var count2: Int = 0

    fun start() {
        println(
            """Приветствую!
            |Определите количество игроков
        """.trimMargin()
        )
        do {
            print("В первой команде: ")
            count = readln().toIntOrNull() ?: return
            firstTeam.makeTeam(count)

            print("Во второй команде: ")
            count2 = readln().toIntOrNull() ?: return
            secondTeam.makeTeam(count2)

            if (count <= 0 || count2 <= 0) println("Число солдат в команде должно быть больше нуля")
        } while (count <= 0 && count2 <= 0)

        teamShuffled()
        battleInformation()
        while (!battleOver) {
            if (Random.nextBoolean()) {
                println("$firstTeam атакует команду $secondTeam:\n")
                battleLogic(firstTeam, secondTeam)
            } else {
                println("$secondTeam атакует команду $firstTeam:\n")
                battleLogic(secondTeam, firstTeam)
            }
        }
        if (firstTeam.list.isEmpty()) BattleState.WinSecondTeam.secondTeamInformation(secondTeam)
        else if (secondTeam.list.isEmpty()) BattleState.WinFirstTeam.firstTeamInformation(firstTeam)
        else BattleState.Draw.drawInformation()
        battleInformation()
    }

    private fun battleLogic(first: Team, second: Team) {
        first.list.stack.forEachIndexed { index, warrior ->
            if (index < second.list.stack.size) {
                println("$warrior атакует ${second.list.stack[index]}")
                if (warrior.weapon.isEmpty) println("У $warrior отсутствуют патроны в магазине")
                warrior.attack(second.list.stack[index])
                if (second.list.stack[index].isKilled) {
                    second.list.stack[index] = second.list.stack[second.list.stack.lastIndex]
                    second.list.stack[second.list.stack.lastIndex] = second.list.stack[index]
                    second.list.pop()
                }
            } else if (index >= second.list.stack.size && !second.list.isEmpty()) {
                val randomIndex = Random.nextInt(0, second.list.stack.size)
                println("$warrior атакует ${second.list.stack[randomIndex]}")
                if (warrior.weapon.isEmpty) println("У $warrior отсутствуют патроны в оружии")
                warrior.attack(second.list.stack[randomIndex])
                if (second.list.stack.any { it.isKilled }) {
                    second.list.stack.sortBy { it.isKilled }
                    second.list.pop()
                }
            }
        }
        println("""
            |============================================
            |"Ход команды $first закончен"
            |============================================
            |
        """.trimMargin()
        )
        if (second.list.isEmpty() || first.list.isEmpty()) battleOver = true
    }

    private fun battleInformation() {
        BattleState.Progress.information(firstTeam)
        BattleState.Progress.information(secondTeam)
    }

    private fun teamShuffled() {
        println("Две команды перемешиваются")
        firstTeam.list.stack.shuffle()
        secondTeam.list.stack.shuffle()
    }
}