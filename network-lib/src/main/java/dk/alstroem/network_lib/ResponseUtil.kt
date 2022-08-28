package dk.alstroem.network_lib

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend inline fun <T> safeQuery(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline body: suspend () -> T?
): Either<T> {
    return withContext(dispatcher) {
        try {
            body()?.let {
                Either.Success(it)
            } ?: Either.Error(IOException("The server did not return any data"))
        } catch (exception: Exception) {
            Either.Error(exception)
        }
    }
}
