
object Weapons {


    fun createPistol() = object : AbstractWeapon(15, fireType = FireType.Single, ammoMagazine = Stack(),true) {
        override fun toString(): String {
            return "Pistol"
        }
    }

    fun createShotgun() = object : AbstractWeapon(10, fireType = FireType.Single, ammoMagazine = Stack(),true) {
        override fun toString(): String {
            return "Shotgun"
        }
    }

    fun createAutomate() = object : AbstractWeapon(30, fireType = FireType.Burst(3), ammoMagazine = Stack(),true) {
        override fun toString(): String {
            return "Automate"
        }
    }

    fun createMachineGun() = object : AbstractWeapon(120, fireType = FireType.Automatic, ammoMagazine = Stack(),true) {
        override fun toString(): String {
            return "Machine Gun"
        }
    }

    fun createSniperRifle() = object : AbstractWeapon(12, fireType = FireType.Single, ammoMagazine = Stack(),true) {
        override fun toString(): String {
            return "Sniper Rifle"
        }
    }

    fun createGrenadeLauncher() = object : AbstractWeapon(1, fireType = FireType.Single, ammoMagazine = Stack(),true) {
        override fun getAmmo(): Ammo? {
            ammo = Ammo.EXPLOSIVE
            return ammo
        }
        override fun toString(): String {
            return "Grenade Launcher"
        }
    }

}