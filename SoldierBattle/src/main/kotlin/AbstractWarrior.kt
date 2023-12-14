abstract class AbstractWarrior(
) : Warrior {
    abstract var maxHealth: Int
    abstract override var chanceAvoid: Int
    abstract val accuracy: Int
    abstract val weapon: AbstractWeapon
    abstract var currentHealth: Int
    override var isKilled: Boolean = false
    override fun attack(warrior: Warrior) {
        try {
            weapon.fire()
            if (accuracy.chance() && !warrior.chanceAvoid.chance()) {
                val damage: Int? = weapon.ammo?.getCurrentDamage()
                println("$warrior атакован")
                damage?.let {
                    warrior.getDamage(it)
                }
            } else println("$warrior удалось избежать попадания\n")
        } catch (ex: NoAmmoException) {
            println("Выполняется перезарядка оружия\n")
            weapon.reload()
            return
        }
    }

    override fun getDamage(damage: Int) {
        println("Нанесен урон: $damage. ")
        if (currentHealth - damage <= 0) {
            currentHealth = 0
            isKilled = true
        } else {
            currentHealth -= damage
        }
        println("Текущее здоровье - $currentHealth")
        println()
    }


}