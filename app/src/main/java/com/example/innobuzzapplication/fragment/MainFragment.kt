package com.example.innobuzzapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innobuzzapplication.R
import com.example.innobuzzapplication.adapter.DataListAdapter
import com.example.innobuzzapplication.apiService.ApiInterface
import com.example.innobuzzapplication.apiService.Retrofit
import com.example.innobuzzapplication.database.AppDatabase
import com.example.innobuzzapplication.database.entities.Content
import com.example.innobuzzapplication.databinding.FragmentMainBinding
import com.example.innobuzzapplication.model.ApiData
import com.example.innobuzzapplication.repository.DataRepository
import com.example.innobuzzapplication.utils.OnClickListener
import com.example.innobuzzapplication.utils.SharedPref
import com.example.innobuzzapplication.viewModel.ApiDataViewModelFactory
import com.example.innobuzzapplication.viewModel.DataViewModel

class MainFragment : Fragment() , OnClickListener {
    private lateinit var _binding: FragmentMainBinding
    private val binding get() = _binding
    private lateinit var  viewModel: DataViewModel
    private val dataList = ArrayList<ApiData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.service.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)

        }

        onGetApi()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })
    }
    private fun onGetApi(){
        val apiInterface = Retrofit.getInstance().create(ApiInterface::class.java)
        val dataRepository = DataRepository(apiInterface)
        binding.loader.visibility = View.VISIBLE

        val contentDao = AppDatabase.getInstance(requireContext()).contentDao()

        viewModel = ViewModelProvider(this, ApiDataViewModelFactory(dataRepository))[DataViewModel::class.java]
        viewModel.data.observe(requireActivity()) { it ->
            binding.loader.visibility = View.GONE
            it.forEach {
                dataList.add(it)

                val content = Content(
                    0,
                    it.id,
                    it.userId,
                    it.title,
                    it.body
                )
                view.let { it ->
                    if (it != null) {
                        contentDao.insertContent(content)
                        SharedPref.init(it.context)
                    }
                }
            }
            binding.rvRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = DataListAdapter(dataList,this)
            binding.rvRecyclerView.adapter = adapter

        }
    }


    override fun callBack(Id: Int) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, DataDetailsFragment())
        fragmentTransaction.addToBackStack("Data Details").commit()
    }
}