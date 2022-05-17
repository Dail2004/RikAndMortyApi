package com.dail.reckandmortyapi.presentation.ui.fragment.search

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.rikandmortyapi.databinding.FragmentDialogFilterSearchBinding

class DialogFilterSearchFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogFilterSearchBinding
    private var isAlive = false
    private var status = "unknown"

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogFilterSearchBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListener()
        return dialog
    }

    private fun setupListener() {
    }
}