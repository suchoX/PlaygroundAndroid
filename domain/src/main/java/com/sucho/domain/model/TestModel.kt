package com.sucho.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestModel(
  val str: String
) : Parcelable