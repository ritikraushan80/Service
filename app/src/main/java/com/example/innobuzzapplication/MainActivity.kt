package com.example.innobuzzapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innobuzzapplication.adapter.DataListAdapter
import com.example.innobuzzapplication.apiService.ApiInterface
import com.example.innobuzzapplication.apiService.Retrofit
import com.example.innobuzzapplication.databinding.ActivityMainBinding
import com.example.innobuzzapplication.fragment.MainFragment
import com.example.innobuzzapplication.repository.DataRepository
import com.example.innobuzzapplication.utils.SharedPref
import com.example.innobuzzapplication.viewModel.ApiDataViewModelFactory
import com.example.innobuzzapplication.viewModel.DataViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var  viewModel: DataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val settings: SharedPreferences =
            applicationContext.getSharedPreferences(
                applicationContext.packageName,
                MODE_PRIVATE
            )
        settings.edit().clear().apply()


        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, MainFragment())
        fragmentTransaction.addToBackStack("Data List").commit()


    }
}