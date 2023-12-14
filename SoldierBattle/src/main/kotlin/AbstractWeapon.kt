import kotlin.random.Random

abstract class AbstractWeapon(
    private val maxAmmo: Int,
    private val fireType: FireType,
    private val ammoMagazine: Stack<Ammo>,
    var isEmpty: Boolean
) {

    internal var ammo: Ammo? = null

    open fun getAmmo(): Ammo? {
        when (Random.nextInt(1, 6)) {
            1 -> ammo = Ammo.ARMOR_PIERCING
            2 -> ammo = Ammo.ORDINARY
            3 -> ammo = Ammo.EXPANSIVE
            4 -> ammo = Ammo.EXPLOSIVE
            5 -> ammo = Ammo.INCENDIARY
        }
        return ammo
    }

    fun reload() {
        getAmmo()
        var i = 0
        while (i < maxAmmo) {
            ammo?.let { ammoMagazine.push(it) }
            i++
        }
        isEmpty = false
    }

    fun fire() {
        if (isEmpty) throw NoAmmoException()
        when (fireType) {
            FireType.Automatic -> {
                val j = 0
                var l = maxAmmo
                while (l > j) {
                    ammoMagazine.pop()
                    l -= 1
                }
                isEmpty = true
            }

            is FireType.Burst -> {
                var l = 0
                while (l < fireType.queueSize) {
                    ammoMagazine.pop()
                    l++
                }
                if (ammoMagazine.isEmpty()) isEmpty = true
            }

            FireType.Single -> {
                ammoMagazine.pop()
                if (ammoMagazine.isEmpty()) isEmpty = true
            }
        }
    }
}

