package com.bignerdranch.android.nflteam

data class Team( var teamID: String = "",
                  var teamName: String = "",
                  var logoFile: String = "",
                  var conference: String = "",
                  var division: String = "",
                  var stadium: String = "",
                  var latitude: String = "",
                  var longitude: String = "")