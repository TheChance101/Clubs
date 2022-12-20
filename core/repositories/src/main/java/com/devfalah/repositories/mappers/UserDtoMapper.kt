package com.devfalah.repositories.mappers

import com.devfalah.entities.User
import com.devfalah.repositories.Constants.SPECIAL_SEPARATE
import com.devfalah.repositories.models.UserDTO


fun UserDTO.toEntity(): User {
    return User(
        birthdate = this.birthdate ?: "",
        coverUrl = this.coverUrl ?: "",
        email = this.email ?: "",
        name = this.firstName ?: "",
        title = this.lastName?.substringBefore(SPECIAL_SEPARATE) ?: "",
        bio = getBio(this.lastName),
        username = this.username ?: "",
        gender = this.gender ?: "",
        id = this.guid ?: 0,
        language = this.language ?: "",
        profileUrl = this.icon?.large ?: "",
        isFriend = false,
        isMyProfile = false,
        isRequestExists = false
    )
}

fun List<UserDTO>.toEntity() = map { it.toEntity() }

private fun getBio(lastName: String?): String {
    return if (lastName != null && lastName.contains(SPECIAL_SEPARATE)) {
        lastName.substringAfter(SPECIAL_SEPARATE)
    } else {
        ""
    }
}