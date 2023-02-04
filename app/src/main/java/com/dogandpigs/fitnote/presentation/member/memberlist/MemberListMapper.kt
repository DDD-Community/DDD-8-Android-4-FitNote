package com.dogandpigs.fitnote.presentation.member.memberlist

import com.dogandpigs.fitnote.data.source.remote.model.Member

internal fun Member.toPresentation(): MemberUiModel = MemberUiModel(
    id = this.userId?.toLongOrNull() ?: 0,
    userName = this.userName ?: "",
)
