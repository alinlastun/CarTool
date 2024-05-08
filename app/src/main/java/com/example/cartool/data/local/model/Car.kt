package com.example.cartool.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val carNumber: String,
    val rca: String? = null,
    val itp: String? = null,
    val rovinieta: String? = null,
    val casco: String? = null,
    val extinctor: String? = null,
    val trusaMedicala: String? = null
)
