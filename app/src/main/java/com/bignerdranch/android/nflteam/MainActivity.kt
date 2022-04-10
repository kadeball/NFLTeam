package com.bignerdranch.android.nflteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*


class MainActivity : AppCompatActivity(), TeamListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = TeamListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    // replace Team List fragment with Team Detail fragment
    override fun onteamSelected(teamId: UUID) {
        val fragment = TeamFragment.newInstance(teamId)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
