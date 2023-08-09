package com.example.sprintm6.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprintm6.R
import com.example.sprintm6.ViewModel.PhoneViewModel
import com.example.sprintm6.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : PhoneViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PhoneAdapter()
        binding.RvList.adapter= adapter
        binding.RvList.layoutManager= GridLayoutManager(context,2)
        viewModel.getPhoneList().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }
        })

        adapter.selectedPhone().observe(viewLifecycleOwner, Observer {
            it?.let {
                // válidar si capta la seleccion
                Log.d("seleccion1", it.id.toString())

            }
            val bundle = Bundle().apply {
                putInt("PhoneId", it.id)
                putString("phoneName", it.name)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

        })

    }

}