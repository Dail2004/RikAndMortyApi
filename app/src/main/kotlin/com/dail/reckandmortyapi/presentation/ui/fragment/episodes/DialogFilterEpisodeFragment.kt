package com.dail.reckandmortyapi.presentation.ui.fragment.episodes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.rikandmortyapi.databinding.FragmentDialogFilterEpisodeBinding

class DialogFilterEpisodeFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogFilterEpisodeBinding
    private var isAlive = false
    private var status = "unknown"

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogFilterEpisodeBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListener()
        return dialog
    }

    private fun setupListener() {
        val episode = binding.episodeFilter.text.toString()
        val name = binding.nameEpisodeFilter.text.toString()
        onInput(episode, name)
    }

    private fun onInput(episode: String, name: String) {
        findNavController().navigate(
            DialogFilterEpisodeFragmentDirections.actionDialogFilterEpisodeFragmentToEpisodesFragment()
                .setEpisode(episode).setName(name)
        )
    }
}
