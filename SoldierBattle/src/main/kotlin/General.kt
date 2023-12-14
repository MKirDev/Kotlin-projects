import kotlin.random.Random

class General : AbstractWarrior() {
    override var maxHealth: Int = 200
    override var chanceAvoid: Int = 70
    override var accuracy: Int = 60
    override var weapon: AbstractWeapon = when (Random.nextInt(1, 7)) {
        1 -> Weapons.createPistol()
        2 -> Weapons.createShotgun()
        3 -> Weapons.createAutomate()
        4 -> Weapons.createMachineGun()
        5 -> Weapons.createGrenadeLauncher()
        else -> {
            Weapons.createSniperRifle()
        }
    }
    override var currentHealth: Int = maxHealth
    override var isKilled: Boolean = false
}