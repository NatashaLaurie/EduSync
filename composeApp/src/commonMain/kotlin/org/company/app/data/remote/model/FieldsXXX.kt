package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class FieldsXXX(
    val address: Address? = null,
    val contact_phone: ContactPhone? = null,
    val room_number: RoomNumber? = null
)