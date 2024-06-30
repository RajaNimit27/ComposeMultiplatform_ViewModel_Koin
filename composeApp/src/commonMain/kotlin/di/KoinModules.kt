package di

import data.remote.ApiService
import data.remote.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import network.Repository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import viewmodel.HomeViewModel

val providehttpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
}

val provideapiServiceModule = module{
    single { ApiService(get()) }
}

val provideDataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSource(get()) }
}

val provideRepositoryModule = module {
    single<Repository> { Repository(get()) }
}

val provideviewModelModule = module {
    viewModelOf(::HomeViewModel)
}

fun appModule() = listOf(providehttpClientModule,
    provideapiServiceModule, provideDataSourceModule, provideRepositoryModule, provideviewModelModule)