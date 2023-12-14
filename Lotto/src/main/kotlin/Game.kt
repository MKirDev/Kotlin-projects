import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class Game {
    private val personList = mutableListOf<Person>()

    fun start() {
        var players: Int
        do {
            print("Введите количество игроков: ")
            players = readln().toIntOrNull() ?: return
            if (players <= 1) println("В игре должно быть несколько игроков.")
        } while (players <= 1)
        while (players > 0) {
            val player = Person()
            personList.add(player)
            players--
        }
        var cards: Int
        do {
            print("Введите количество карточек для игроков: ")
            cards = readln().toIntOrNull() ?: return
            if (cards <= 0) println("У игроков должна быть хотя бы одна карточка.")
        } while (cards <= 0)

        personList.forEach { person ->
            println("Для игрока $person созданы следующие карточки: ")
            person.takeCard(cards)
        }
        println("Игра начинается")

        runBlocking {
            val scope = CoroutineScope(Job() + Dispatchers.Default)
            val listOfWinner = mutableListOf<Person>()
            scope.launch {
                personList.forEachIndexed { _, person ->
                    launch {
                        GeneratorShared.sharedFlow.collect {
                            println("$person получил бочонок с номером $it")
                            person.countCard.forEachIndexed { indexOfCard, arrays ->
                                arrays.forEachIndexed { _, ints ->
                                    ints.forEachIndexed { indexOfInts, i ->
                                        if (i != null) {
                                            if (it == i) ints[indexOfInts] = null
                                        }
                                    }
                                    if (ints.all { it2 -> it2 == null }) {
                                        listOfWinner.add(person)
                                        println("У игрока $person на ${indexOfCard + 1}-ой карточке строка полностью закрыта")
                                        scope.cancel()
                                    }
                                }
                            }
                        }
                    }
                }
            }.join()

            if (listOfWinner.isNotEmpty()) {
                if (listOfWinner.size >= 2)
                    println("Победил игрок: ${listOfWinner.first()}, поскольку первым закрыл все пять чисел")
                else println("Победил игрок: ${listOfWinner.first()}")
            }
        }
    }

    fun cardInfo() {
        personList.forEach {
            println("У игрока $it следующие карточки: ")
            it.countCard.forEachIndexed { index, arrays ->
                print("${index + 1} карточка: \n")
                arrays.forEach { it2 ->
                    for (i in it2) {
                        print("$i ")
                    }
                    println()
                }
            }
        }
    }
}


