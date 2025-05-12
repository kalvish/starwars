package com.kalan.starwarsnotebook.core

import com.kalan.starwarsnotebook.core.domain.util.DomainError
import com.kalan.starwarsnotebook.core.domain.util.NetworkError
import com.kalan.starwarsnotebook.core.domain.util.map
import org.junit.Assert.*
import org.junit.Test

class ResultTest {

    @Test
    fun test_ResultMapShouldTransformData_success() {
        val result: com.kalan.starwarsnotebook.core.domain.util.Result<Int, DomainError> = com.kalan.starwarsnotebook.core.domain.util.Result.Success(5)
        val mapped = result.map {
            it * 2
        }

        assertTrue(mapped is com.kalan.starwarsnotebook.core.domain.util.Result.Success)
        assertEquals(10, (mapped as com.kalan.starwarsnotebook.core.domain.util.Result.Success).data)
    }
}