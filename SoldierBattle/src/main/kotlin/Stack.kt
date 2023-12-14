class Stack<T> () {

    var stack = mutableListOf<T>()

    fun push(item: T) {
        stack.add(item)
    }

    fun pop(): T? {
        return stack.removeLastOrNull()
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}