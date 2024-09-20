package org.company.app.data.remote

import EduApp.composeApp.BuildConfig
import org.company.app.data.remote.mapper.ApiModelMapper
import org.company.app.data.remote.model.Documents
import org.company.app.domain.model.Course
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.company.app.repository.RemoteDataRepository

class RemoteDataImpl(
    private val httpClient: HttpClient,
    private val apiModelMapper: ApiModelMapper,
    private val apiKey: String
) : RemoteDataRepository {

    private companion object {
        private const val BASE_URL = "https://firestore.googleapis.com"
    }

    private fun buildUrl(category: String): String =
        "$BASE_URL/$apiKey/$category/courses"

    override suspend fun getCoursesFromApiByCategory(category: String): List<Course> {
        return try {
            val response = httpClient.get(buildUrl(category)).body<Documents>()
            apiModelMapper.map(response.documents)
        } catch (e: Exception) {
            emptyList()
        }
    }
}