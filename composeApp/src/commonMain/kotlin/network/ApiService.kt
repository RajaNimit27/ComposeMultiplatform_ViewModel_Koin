package data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import models.ApiResponse
import models.Products
import utils.Constants

class ApiService(private val httpClient: HttpClient) {

    val products = "products?limit=100&skip=5"
    suspend fun getProducts(): ApiResponse =
        httpClient.get("${Constants.BASE_URL}$products").body<ApiResponse>()

}