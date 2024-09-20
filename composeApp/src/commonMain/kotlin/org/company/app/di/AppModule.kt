package org.company.app.di

import EduApp.composeApp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.company.app.data.local.CacheDataRepositoryImpl
import org.company.app.data.remote.RemoteDataImpl
import org.company.app.data.remote.mapper.ApiModelMapper
import org.company.app.db.Database
import org.company.app.domain.CourseRefresher
import org.company.app.domain.CoursesRepository
import org.company.app.presentation.ui.features.categories.CategoriesViewModel
import org.company.app.repository.CacheDataRepository
import org.company.app.repository.CoursesRepositoryImpl
import org.company.app.repository.RemoteDataRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            repositoryModule,
            refreshModule,
            ktorModule,
            sqlDelightModule,
            mapperModule,
            dispatcherModule,
            platformModule()
        )
    }

val refreshModule = module {
    single { CourseRefresher(get()) }
}

val viewModelModule = module {
    factory { CategoriesViewModel() }
}

val repositoryModule = module {
    single<RemoteDataRepository> { RemoteDataImpl(get(), get(), BuildConfig.API_KEY) }
    single<CacheDataRepository> { CacheDataRepositoryImpl(get()) }
    single<CoursesRepository> { CoursesRepositoryImpl(get(), get()) }
}

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }, contentType = ContentType.Any
                )
            }
        }
    }
}

val sqlDelightModule = module {
    single { Database(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val mapperModule = module {
    factory { ApiModelMapper() }
}

fun initKoin() = initKoin {}