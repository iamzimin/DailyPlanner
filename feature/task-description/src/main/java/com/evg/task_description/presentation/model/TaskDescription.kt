package com.evg.task_description.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TaskDescription(
    val id: Long,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
) : Parcelable