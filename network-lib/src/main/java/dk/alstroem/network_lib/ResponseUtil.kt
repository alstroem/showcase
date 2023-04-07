package dk.alstroem.network_lib

import java.io.IOException

suspend fun <T> safeQuery(
    body: suspend () -> T?
): Result<T> {
    return try {
        body()?.let {
            Result.success(it)
        } ?: Result.failure(IOException("The server did not return any data"))
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}
