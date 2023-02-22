package com.dogandpigs.fitnote.presentation.member.memberlist

import com.dogandpigs.fitnote.data.source.remote.model.Info

internal fun Info.toPresentation(): MemberUiModel = MemberUiModel(
    id = this.id,
    userName = this.userName,
)
