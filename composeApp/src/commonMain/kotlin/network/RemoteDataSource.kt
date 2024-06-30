package data.remote



class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getProducts() = apiService.getProducts()
}