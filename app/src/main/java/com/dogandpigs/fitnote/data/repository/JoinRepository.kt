package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.JoinApi
import javax.inject.Inject

class JoinRepository @Inject constructor(
    private val api: JoinApi
) {
}