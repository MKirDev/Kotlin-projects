import kotlin.random.Random

class Person {
    val countCard = mutableListOf<Array<Array<Int?>>>()
    fun takeCard(n: Int) {
        repeat(n) {
            val card = Array(3) { Array<Int?>(9) { null } }
            val mutableSet = mutableSetOf<Int>()
            card.forEachIndexed { i, array ->
                array.forEachIndexed { j, _ ->
                    var randomNumber: Int
                    do {
                        randomNumber = Random.nextInt(1, 91)
                    } while (randomNumber in mutableSet)
                    if (j % 2 != 0) array[j] = null
                    else {
                        card[i][j] = randomNumber
                        mutableSet.add(randomNumber)
                    }
                }
                array.shuffle()
            }
            countCard.add(card)
        }
        countCard.forEachIndexed { index, arrays ->
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



