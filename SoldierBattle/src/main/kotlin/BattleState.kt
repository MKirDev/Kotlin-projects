sealed class BattleState : Team() {
    object Progress : BattleState() {

        private fun healthCount(team: Team): Int {
            var teamHealth: Int
            var health = 0
            team.list.stack.forEach {
                teamHealth = it.currentHealth
                health += teamHealth
            }
            return health
        }

        fun information(team: Team) {
            println(
                """Состав команды $team: 
                |Солдаты: ${team.list.stack}
                |Общее здоровье команды: ${healthCount(team)}
                |Количество выживших солдат: ${team.list.stack.size}
                |
                """.trimMargin()
            )
            if (!team.list.isEmpty()) {
                (team.list.stack.forEach {
                    println("Здоровье $it: ${it.currentHealth}")
                })
                println()
            }
        }
    }
    object WinFirstTeam: BattleState() {
        fun firstTeamInformation(team: Team) {
            println("Победила первая команда - $team\n")
        }
    }

    object WinSecondTeam: BattleState() {
        fun secondTeamInformation(team: Team) {
            println("Победила вторая команда - $team\n")
        }
    }

    object Draw {
        fun drawInformation() = println("Ничья.Победителя нет\n")
    }
}