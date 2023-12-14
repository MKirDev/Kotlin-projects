import kotlin.random.Random

open class Team {
    val list = Stack<AbstractWarrior>()
    fun makeTeam(number: Int) {
        var j = number
        while (j > 0) {
            when (Random.nextInt(0, 100)) {
                in 95..100 -> list.push(General())
                in 84..94 -> list.push(Captain())
                in 68..83 -> list.push(Lieutenant())
                in 47..67 -> list.push(Sergeant())
                in 0..46 -> list.push(Soldier())
            }
            j -= 1
        }
    }

}