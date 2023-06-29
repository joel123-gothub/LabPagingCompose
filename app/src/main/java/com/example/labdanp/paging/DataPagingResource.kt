package com.example.labdanp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.labdanp.datos.Data
import kotlin.math.min

class DataPagingSource(private val data: List<Data>) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 0
            val pageSize = params.loadSize
            val start = page * pageSize
            val end = start + pageSize
            val pageData = data.subList(start, min(end, data.size))
            val nextPage = if (end < data.size) page + 1 else null
            LoadResult.Page(
                data = pageData,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }
}
