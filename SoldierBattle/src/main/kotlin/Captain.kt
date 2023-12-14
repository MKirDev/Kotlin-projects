import kotlin.random.Random

class Captain : AbstractWarrior() {
    override var maxHealth: Int = 170
    override var chanceAvoid: Int = 60
    override var accuracy: Int = 50
    override var weapon: AbstractWeapon = when (Random.nextInt(1, 6)) {
        1 -> Weapons.createPistol()
        2 -> Weapons.createShotgun()
        3 -> Weapons.createAutomate()
        4 -> Weapons.createMachineGun()
        else -> {
            Weapons.createGrenadeLauncher()
        }
    }
    override var currentHealth: Int = maxHealth
    override var isKilled: Boolean = false
}