import kotlin.random.Random

class Sergeant : AbstractWarrior() {
    override var maxHealth: Int = 120
    override var chanceAvoid: Int = 50
    override var accuracy: Int = 40
    override var weapon: AbstractWeapon = when (Random.nextInt(1, 4)) {
        1 -> Weapons.createShotgun()
        2 -> Weapons.createAutomate()
        else -> {
            Weapons.createSniperRifle()
        }
    }
    override var currentHealth: Int = maxHealth
    override var isKilled: Boolean = false
}