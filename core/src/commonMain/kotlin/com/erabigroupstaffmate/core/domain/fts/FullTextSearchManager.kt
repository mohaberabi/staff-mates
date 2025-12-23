package com.erabigroupstaffmate.core.domain.fts

import kotlinx.coroutines.flow.Flow

interface FullTextSearchManager {


    fun <T> fullTextSearch(
        query: String,
        ftsResults: (query: String) -> Flow<Set<String>>,
        pivotResults: (primaryIds: Set<String>) -> Flow<List<T>>
    ): Flow<List<T>>
}