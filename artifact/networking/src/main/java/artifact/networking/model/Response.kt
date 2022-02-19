package artifact.networking.model

import android.util.Log

// type of return classes
sealed class Response<ContentType>

sealed class ValidationResponse<ContentType>(data: ContentType?, message: String?, statusCode: Int):Response<ContentType>()

sealed class Success<ContentType> : Response<ContentType>()

sealed class Failure<ContentType>(val message: String?, val statusCode: Int) :
    Response<ContentType>()

// mapper classes
class Ok<ContentType>(val data: ContentType) : Success<ContentType>()

class EmptyOk<ContentType> : Success<ContentType>()

class NoContent<ContentType> : Success<ContentType>()

class ClientError<ContentType>(message: String?, statusCode: Int) :
    Failure<ContentType>(message,statusCode)

class NotAuthorized<ContentType>(message: String?, statusCode: Int) :
    Failure<ContentType>(message,statusCode)

//class ValidationError<ContentType>( data: ContentType?,  message: String?, statusCode: Int) :
//    ValidationResponse<ContentType>(data,message,statusCode)

class ServerError<ContentType>(message: String?, statusCode: Int) :
    Failure<ContentType>(message, statusCode)

class Undefined<ContentType>(val data: ContentType?, val message: String?, val statusCode: Int) :
    Response<ContentType>()


fun <T> retrofit2.Response<T>.map(): Response<T> {
    Log.d("Response", "" + body())
    Log.d("Response Code", "" + code())
    // Log.d("Response Error", "" + extractErrorMessage())
    return when {
        body() != null && code() / 100 == 2 -> Ok(body()!!)
        code() / 100 == 2 -> EmptyOk()
        body() == null && code() == 204 -> NoContent()
        code()  == 401 && body()==null -> NotAuthorized( extractErrorMessage(), code()) // if token is expired
        code() / 100 == 4 && body()==null -> ClientError( extractErrorMessage(), code())
        code() / 100 == 5 -> ServerError("", code())
        else -> Undefined(body(), extractErrorMessage(), code())
    }
}

fun <T> retrofit2.Response<T>.extractErrorMessage(): String? {
    val msg = errorBody()?.string()
    return if (msg.isNullOrEmpty()) {
        message()
    } else {
        msg
    }
}