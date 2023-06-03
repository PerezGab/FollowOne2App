package com.example.gabbinete.followone2.ui.grand_prix_profile

import com.example.gabbinete.followone2.domain.GrandPrix

data class GrandPrixProfileState(
    val grandPrix: GrandPrix?,
    val isLoading: Boolean
)