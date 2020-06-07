package com.example.vedanam.Fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vedanam.Adapter.SubjectAdapter
import com.example.vedanam.Model.AttendanceInputModel
import com.example.vedanam.Model.AttendanceModel
import com.example.vedanam.Model.SubjectInputModel
import com.example.vedanam.Model.SubjectOutputModel

import com.example.vedanam.R
import com.example.vedanam.Repository.AttendanceRepository
import com.example.vedanam.Repository.SubjectRepository
import com.example.vedanam.Util.EventDecorator
import com.example.vedanam.ViewModel.AttendanceViewModel
import com.example.vedanam.ViewModel.SubjectViewmodel
import com.example.vedanam.databinding.FragmentSubjectBinding
import com.example.vedanam.getNetworkService
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_attendance.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubjectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSubjectBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subject, container, false)
        binding.setLifecycleOwner(this)

        val repository = SubjectRepository(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this, SubjectViewmodel.FACTORY(repository))
            .get(SubjectViewmodel::class.java)

        viewModel.onGetStudentSubjects(SubjectInputModel("getSubjectList","1"))

        viewModel.mutableSubjectLiveData.observe(this) { value ->
            value?.let {

                Log.i("SubjectResponse",value.get(0).subject_name)
                val subjectlist:List<SubjectOutputModel> = value

                try {
                    var adapter: SubjectAdapter = SubjectAdapter(subjectlist,activity!!)
                    binding.subjectRecycle!!.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.subjectRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("subjectAdapterException",e.message)
                }


            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SubjectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubjectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
