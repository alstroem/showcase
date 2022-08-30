package dk.alstroem.network_lib

import java.io.IOException

suspend fun <T> safeQuery(
    body: suspend () -> T?
): Either<T> {
    return try {
        body()?.let {
            Either.Success(it)
        } ?: Either.Error(IOException("The server did not return any data"))
    } catch (exception: Exception) {
        Either.Error(exception)
    }
}
