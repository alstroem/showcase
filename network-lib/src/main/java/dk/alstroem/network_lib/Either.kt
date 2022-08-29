package dk.alstroem.network_lib

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Either<out R> {
    data class Success<out T>(val data: T) : Either<T>()
    data class Error(val exception: Exception) : Either<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

/**
 * `true` if [Either] is of type [Success] & holds non-null [Success.data].
 */
val Either<*>.succeeded
    get() = this is Either.Success && data != null

fun <T> Either<T>.successOr(fallback: T): T {
    return (this as? Either.Success<T>)?.data ?: fallback
}

val <T> Either<T>.data: T?
    get() = (this as? Either.Success)?.data

/**
 * Updates value of [MutableStateFlow] if [Either] is of type [Success]
 */
inline fun <reified T> Either<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is Either.Success) {
        stateFlow.value = data
    }
}
