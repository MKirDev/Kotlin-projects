class NoAmmoException : Throwable() {
    override val message: String = "Not enough ammo to shoot"
}