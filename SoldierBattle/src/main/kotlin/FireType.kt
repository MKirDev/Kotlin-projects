sealed class FireType {
    object Single : FireType() {
        override fun toString(): String {
            return "Single"
        }
    }
    data class Burst(val queueSize: Int) : FireType()
    object Automatic : FireType() {
        override fun toString(): String {
            return "Automatic"
        }
    }
}