package com.sucho.data.usecase

import com.sucho.data.repository.TestRepo
import com.sucho.domain.mappers.toTest
import com.sucho.domain.model.TestModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTestDataUseCase @Inject constructor(
  private val testRepo: TestRepo
) : BaseUseCase<TestModel, Unit> {

  override suspend fun perform(): TestModel {
    val result = testRepo.getTestData()
    return result.toTest()
  }
}