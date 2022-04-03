package com.bignerdranch.android.nflteam

import java.util.*

data class Team(
    var teamID: String = "",
    var teamName: String = "",
    var logoFile: String = "",
    var conference: String = "",
    var division: String = "",
    var stadium: String = "",
    var latitude: Double = 0.0,
    var longitude: Double= 0.0,
    val id: UUID = UUID.randomUUID()
    )