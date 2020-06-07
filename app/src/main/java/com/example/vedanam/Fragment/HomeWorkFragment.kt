package com.example.vedanam.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vedanam.Adapter.HomeWorkAdapter
import com.example.vedanam.Model.ExamListInputModel
import com.example.vedanam.Model.HomeWorkOutputModel
import com.example.vedanam.R
import com.example.vedanam.Repository.HomeWorkRepository
import com.example.vedanam.databinding.FragmentHomeworkBinding
import com.example.vedanam.getNetworkService
import com.example.vedanam.ViewModel.HomeWorkViewModel

class HomeWorkFragment : Fragment() {

    private lateinit var homeWorkViewModel: HomeWorkViewModel
    private lateinit var binding: FragmentHomeworkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homework, container, false)
        binding.setLifecycleOwner(this)

        val repository = HomeWorkRepository(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this,
                HomeWorkViewModel.FACTORY(
                    repository
                )
            )
            .get(HomeWorkViewModel::class.java)

        viewModel.getHomeWorkList(ExamListInputModel("getHomeworkGroupByAdmin"))

        viewModel.mutableHomeWorkLiveData.observe(this) { value ->
            value?.let {

                Log.i("HomeWorkResponse",value.get(0).task)
                val homeWorklist:List<HomeWorkOutputModel> = value

                try {
                    var adapter: HomeWorkAdapter = HomeWorkAdapter(homeWorklist,activity!!)
                    binding.homeWorkRecycle!!.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.homeWorkRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("homeworkAdapterExp",e.message)
                }


            }
        }

        return binding.root
    }
}