package tayabas.anthony.retrofitsample.data.api

import retrofit2.Response
import retrofit2.http.GET
import tayabas.anthony.retrofitsample.model.ResponseListUsers

interface ApiInterface {
    @GET("/api/users?page=2")
    suspend fun getAllUsers(): Response<ResponseListUsers>
}