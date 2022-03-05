package com.bignerdranch.android.nflteam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "TeamListFragment"

class TeamListFragment : Fragment() {

    private lateinit var teamRecyclerView: RecyclerView
    private var adapter: TeamAdapter? = null



    private val teamListViewModel: TeamListViewModel by lazy {
        ViewModelProviders.of(this).get(TeamListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${teamListViewModel.teams.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_list, container, false)

        teamRecyclerView =
            view.findViewById(R.id.team_recycler_view) as RecyclerView
        teamRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val teams = teamListViewModel.teams
        adapter = TeamAdapter(teams)
        teamRecyclerView.adapter = adapter
    }

    private inner class TeamHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val nameTextView: TextView = itemView.findViewById(R.id.team_name)
        val stadiumTextView: TextView = itemView.findViewById(R.id.stadium_name)
    }

    private inner class TeamAdapter(var teams: List<Team>)
        : RecyclerView.Adapter<TeamHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : TeamHolder {
            val view = layoutInflater.inflate(R.layout.list_item_team, parent, false)
            return TeamHolder(view)
        }

        override fun getItemCount() = teams.size

        override fun onBindViewHolder(holder: TeamHolder, position: Int) {
            val team = teams[position]
            holder.apply {
                nameTextView.text = team.teamName
                stadiumTextView.text = team.stadium
            }
        }
    }


    companion object {
        fun newInstance(): TeamListFragment {
            return TeamListFragment()
        }
    }
}