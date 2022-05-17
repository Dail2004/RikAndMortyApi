package com.dail.reckandmortyapi.presentation.ui.fragment.characters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.rikandmortyapi.databinding.FragmentDialogFilterCharacterBinding

class DialogFilterFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogFilterCharacterBinding
    private var isAlive = false
    private var status = "unknown"

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogFilterCharacterBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListener()
        return dialog
    }

    private fun setupListener() {
        binding.chipStatusAlive.setOnCheckedChangeListener { chip, isChecked ->
            Log.d("TAG", "setupListener: $isChecked")
            status = if (isChecked) {
                "alive"
            } else {
                "dead"
            }
        }

        binding.chipStatusDead.setOnCheckedChangeListener { chip, isChecked ->
            status = if (isChecked) {
                "dead"
            } else {
                "alive"
            }
        }

        binding.chipStatusUnknown.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                status
            }
        }
        binding.applyBtn.setOnClickListener {
            findNavController().navigate(
                DialogFilterFragmentDirections.dialogFragmentToCharacters().setStatus(status)
            )
        }
    }
}