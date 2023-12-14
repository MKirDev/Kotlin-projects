import kotlin.random.Random

class Lieutenant : AbstractWarrior() {
    override var maxHealth: Int = 150
    override var chanceAvoid: Int = 50
    override var accuracy: Int = 40
    override var weapon: AbstractWeapon = when (Random.nextInt(1, 5)) {
        1 -> Weapons.createPistol()
        2 -> Weapons.createShotgun()
        3 -> Weapons.createAutomate()
        else -> {
            Weapons.createSniperRifle()
        }
    }
    override var currentHealth: Int = maxHealth
    override var isKilled: Boolean = false
}