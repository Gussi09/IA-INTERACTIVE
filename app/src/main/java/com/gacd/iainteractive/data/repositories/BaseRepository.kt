package com.gacd.iainteractive.data.repositories

import com.gacd.iainteractive.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall : suspend () -> T
    ) : Resource<T>{
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false,throwable.code(),throwable.response()?.errorBody())
                    }
                    else ->{
                        Resource.Failure(true, null,null)
                    }
                }
            }
        }
    }
    // Used to Movies Request
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) : T{

        val response = call.invoke()
        if (response.isSuccessful){
            return response.body()!!
        }else{
            //Handle Api Exception
            throw apiException(response.code().toString())
        }
    }

}

class apiException(message: String): IOException(message)
