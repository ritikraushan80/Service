package com.example.innobuzzapplication.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.innobuzzapplication.R
import com.example.innobuzzapplication.databinding.FragmentDataDetailsBinding
import com.example.innobuzzapplication.databinding.FragmentMainBinding
import com.example.innobuzzapplication.utils.Constant
import com.example.innobuzzapplication.utils.OnClickListener
import com.example.innobuzzapplication.utils.SharedPref

class DataDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentDataDetailsBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentDataDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = SharedPref.read(Constant.ID, null).toString()
        val userId = SharedPref.read(Constant.USER_ID, null).toString()
        val title = SharedPref.read(Constant.TITLE, null).toString()
        val description = SharedPref.read(Constant.DESCRIPTION, null).toString()

        binding.id.text = id
        binding.userId.text = userId
        binding.title.text = title
        binding.description.text = description

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}