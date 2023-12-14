import kotlin.random.Random

class Soldier : AbstractWarrior() {
    override var maxHealth: Int = 100
    override var chanceAvoid: Int = 30
    override var accuracy: Int = 30
    override var weapon: AbstractWeapon = when (Random.nextInt(1, 3)) {
        1 -> Weapons.createShotgun()
        else -> Weapons.createAutomate()
    }
    override var currentHealth: Int = maxHealth
    override var isKilled: Boolean = false

}