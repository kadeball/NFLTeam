package com.bignerdranch.android.nflteam

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class TeamListFragment : Fragment() {
    interface Callbacks {
        fun onteamSelected(teamId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var teamRecyclerView: RecyclerView
    private var adapter: TeamAdapter = TeamAdapter(emptyList())


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }


    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_list, container, false)

        teamRecyclerView = view.findViewById(R.id.team_recycler_view) as RecyclerView
        teamRecyclerView.layoutManager = LinearLayoutManager(context)
        teamRecyclerView.adapter = adapter
        teamRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("aaa", TeamListViewModel.teams.toString())
        updateUI(TeamListViewModel.teams)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    private fun updateUI(teams: List<Team>) {
        val teams = TeamListViewModel.teams
        adapter = TeamAdapter(teams)
        teamRecyclerView.adapter = adapter
    }


    private inner class TeamAdapter(var teams: List<Team>) : RecyclerView.Adapter<TeamHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TeamHolder {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.list_item_team, parent, false)
            return TeamHolder(view)
        }

        override fun onBindViewHolder(holder: TeamHolder, position: Int) {
            val team = teams[position]
            holder.bind(team)
        }

        override fun getItemCount() = teams.size
    }

    private inner class TeamHolder(view: View)  : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var team: Team

        private val teamName: TextView = itemView.findViewById(R.id.team_name)
        private val stadiumName: TextView = itemView.findViewById(R.id.stadium_name)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(team: Team) {
            this.team = team
            teamName.text = this.team.teamName
            stadiumName.text = this.team.stadium
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${team.teamName} clicked!", Toast.LENGTH_SHORT)
                .show()
            callbacks?.onteamSelected(team.id)
        }
    }

}