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
import com.example.vedanam.Adapter.NoticeBoardAdapter
import com.example.vedanam.Model.ExamListInputModel
import com.example.vedanam.Model.NoticeOutputModel
import com.example.vedanam.R
import com.example.vedanam.Repository.NoticeBoardRepository
import com.example.vedanam.databinding.FragmentNoticeboardBinding
import com.example.vedanam.getNetworkService
import com.example.vedanam.ViewModel.NoticeBoardViewModel

class NoticeBoardFragment : Fragment() {

    private lateinit var noticeBoardViewModel: NoticeBoardViewModel
    private  lateinit var binding:FragmentNoticeboardBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_noticeboard, container, false)
        binding.setLifecycleOwner(this)

        val repository = NoticeBoardRepository(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this,
                NoticeBoardViewModel.FACTORY(
                    repository
                )
            )
            .get(NoticeBoardViewModel::class.java)

        viewModel.getNoticetList(ExamListInputModel("getNoticeBoard"))

        viewModel.mutableNoticeBoardLiveData.observe(this) { value ->
            value?.let {

                Log.i("NoticeResponse",value.get(0).notice_title)
                val noticelist:List<NoticeOutputModel> = value

                try {
                    var adapter: NoticeBoardAdapter = NoticeBoardAdapter(noticelist,activity!!)
                    binding.noticeRecycle!!.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.noticeRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("subjectAdapterException",e.message)
                }


            }
        }

        return binding.root
    }
}