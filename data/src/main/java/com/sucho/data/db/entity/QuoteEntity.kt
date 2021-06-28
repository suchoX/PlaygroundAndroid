package com.sucho.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name = "type") val type: Int,
  @ColumnInfo(name = "quote") val quote: String,
  @ColumnInfo(name = "img_res") val imgRes: Int
)
