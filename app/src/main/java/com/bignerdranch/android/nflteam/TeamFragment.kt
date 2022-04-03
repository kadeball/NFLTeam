package com.bignerdranch.android.nflteam

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


    private lateinit var team: Team
    private lateinit var name: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val teamId: UUID = arguments?.getSerializable(ARG_TEAM_ID) as UUID
        team = TeamListViewModel.getTeam(teamId)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)

        name = view.findViewById(R.id.team_name) as TextView

        return view
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {

            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This space intentionally left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                team.teamName = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                // This one too
            }
        }

        name.addTextChangedListener(titleWatcher)
    }
}