package org.company.app.data.remote.model

import kotlinx.serialization.Serializable
import org.company.app.data.remote.model.Document

@Serializable
data class Documents(
    val documents: List<Document>
)