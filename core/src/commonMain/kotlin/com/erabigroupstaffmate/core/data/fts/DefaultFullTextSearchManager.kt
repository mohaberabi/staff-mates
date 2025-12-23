package com.erabigroupstaffmate.core.data.fts

import com.erabigroupstaffmate.core.domain.fts.FullTextSearchManager
import com.erabigroupstaffmate.utility.extensions.wildCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

class DefaultFullTextSearchManager : FullTextSearchManager {
    override fun <T> fullTextSearch(
        query: String,
        ftsResults: (String) -> Flow<Set<String>>,
        pivotResults: (Set<String>) -> Flow<List<T>>
    ): Flow<List<T>> {
        val ftsFlow = ftsResults(query.wildCard).distinctUntilChanged()
        return ftsFlow.flatMapLatest { pivotResults(it) }.distinctUntilChanged()
    }
}