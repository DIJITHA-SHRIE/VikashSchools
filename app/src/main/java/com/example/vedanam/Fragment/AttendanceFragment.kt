package com.example.vedanam.Fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.vedanam.Model.AttendanceInputModel
import com.example.vedanam.Model.AttendanceModel
import com.example.vedanam.R
import com.example.vedanam.Repository.AttendanceRepository
import com.example.vedanam.Util.EventDecorator
import com.example.vedanam.ViewModel.AttendanceViewModel
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
 * Use the [AttendanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AttendanceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view =  inflater.inflate(R.layout.fragment_attendance, container, false)

        val repository = AttendanceRepository(getNetworkService())
        val viewModel = ViewModelProviders
            .of(this, AttendanceViewModel.FACTORY(repository))
            .get(AttendanceViewModel::class.java)

        viewModel.onGetStudentAttendance(AttendanceInputModel("getAttendanceList","1","2020-2021"))

        viewModel.mutableAttendanceLiveData.observe(this) { value ->
            value?.let {

                Log.i("AttendanceResponse",value.get(0).subject)
                val attendancelist:List<AttendanceModel> = value

                var events = ArrayList<CalendarDay>()
                val sdf = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
                for (i in 0 until attendancelist.size) {
                    val date:Date = sdf.parse(attendancelist.get(i).date)
                    val day = CalendarDay.from(2020,4,24)
                    events.add(day)
                }

                calendarProli.addDecorator(EventDecorator(Color.GREEN,events))




            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AttendanceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AttendanceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
