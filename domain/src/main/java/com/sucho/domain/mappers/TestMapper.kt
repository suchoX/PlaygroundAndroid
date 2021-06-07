package com.sucho.domain.mappers

import com.sucho.domain.model.TestModel
import com.sucho.domain.responemodels.TestResponse

fun TestResponse.toTest(): TestModel {
  return TestModel(this.num.toString())
}