import kotlin.random.Random

enum class Ammo(private val damage: Int, private val chanceCriticalDamage: Int, private val rationCriticalDamage: Int) {
    ORDINARY(10,25,10),
    ARMOR_PIERCING(20,25,20),
    INCENDIARY(40,25,20),
    EXPANSIVE(40,25,50),
    EXPLOSIVE(50,25,60);

    fun getCurrentDamage(): Int {
        val currentDamage: Int = if (chanceCriticalDamage.chance()) {
            damage + damage * rationCriticalDamage / 100
        } else {
            damage
        }
        return currentDamage
    }
}

fun Int.chance(): Boolean {
    return Random.nextInt(0,100) in 0..this
}