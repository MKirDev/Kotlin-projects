
interface Warrior {
    var isKilled: Boolean
    var chanceAvoid: Int

    fun attack(warrior: Warrior)
    fun getDamage(damage: Int)

}