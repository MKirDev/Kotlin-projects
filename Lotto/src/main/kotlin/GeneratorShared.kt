import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.random.Random

object GeneratorShared {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    private var randomNumber: Int = 0
    private val mutableSet = mutableSetOf<Int>()

    init {
        repeat(90) {
            do {
                randomNumber = Random.nextInt(1, 91)
            } while (randomNumber in mutableSet)
            mutableSet.add(randomNumber)
        }

        scope.launch {
            while (currentCoroutineContext().isActive)
                mutableSet.forEach {
                    _sharedFlow.emit(it)
                    delay(1000)
                }
        }
    }
}