package com.bignerdranch.android.nflteam

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

private const val ARG_TEAM_ID = "team_id"


class TeamFragment : Fragment() {
    companion object {
        fun newInstance(teamId: UUID): TeamFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TEAM_ID,teamId)
            }
            return TeamFragment().apply {
                arguments = args
            }
        }
    }

    private lateinit var titleField: TextView
    private lateinit var stadiumField: TextView
    private lateinit var divField: TextView

    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val teamId: UUID = arguments?.getSerializable(ARG_TEAM_ID) as UUID
        team = TeamListViewModel.getTeam(teamId)!!

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)

        titleField = view.findViewById(R.id.team_name) as TextView
        stadiumField = view.findViewById(R.id.stadium_name) as TextView
        divField = view.findViewById(R.id.team_division) as TextView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    override fun onStart() {
        super.onStart()
        Log.d("TeamFragment","start")
    }

    private fun updateUI() {
        titleField.setText(team.teamName)
        stadiumField.setText(team.stadium)
        divField.setText(team.division)
    }

}