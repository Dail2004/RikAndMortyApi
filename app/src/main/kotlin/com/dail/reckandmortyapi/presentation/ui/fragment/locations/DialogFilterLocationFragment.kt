package com.dail.reckandmortyapi.presentation.ui.fragment.locations

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.rikandmortyapi.databinding.FragmentDialogFilterLocationBinding

class DialogFilterLocationFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogFilterLocationBinding
    private var isAlive = false
    private var status = "unknown"

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogFilterLocationBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListener()
        return dialog
    }

    private fun setupListener() {
        val type = binding.typeFilter.text.toString()
        val dimension = binding.dimensionFilter.text.toString()
        binding.applyButton.setOnClickListener {
            findNavController().navigate(
                DialogFilterLocationFragmentDirections.actionDialogFilterLocationFragmentToLocationsFragment()
                    .setType(type).setDimension(dimension)
            )
        }
    }
}
