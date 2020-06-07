package com.example.vedanam.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vedanam.Adapter.ManageMarksAdapter
import com.example.vedanam.Model.*
import com.example.vedanam.R
import com.example.vedanam.Repository.ManageMarksRepository
import com.example.vedanam.databinding.FragmentManageMarksBinding
import com.example.vedanam.getNetworkService
import com.example.vedanam.ViewModel.ManageMarksViewModel


class ManageMarksFragment : Fragment() {

    private lateinit var manageMarksViewModel: ManageMarksViewModel
    private lateinit var binding: FragmentManageMarksBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_manage_marks,container,false)
        binding.setLifecycleOwner(this)

        val repository = ManageMarksRepository(getNetworkService())
        val viewModel = ViewModelProviders.of(this,
            ManageMarksViewModel.FACTORY(
                repository
            )
        ).get(ManageMarksViewModel::class.java)
        viewModel.onGetExamList(ExamListInputModel("getExamList"))
        viewModel.onGetClassList(ExamListInputModel("getclassList"))
        viewModel.onGetSectionList(SectionListInputModel("getsectionList","1"))
        viewModel.onGetSubjectList(SubjectInputModel("getsubjectList","1"))
        viewModel.onGetManageMarks(ManageMarksInputmodel("getManageMarks","1","3","2","1"))

        viewModel.mutableManageMarksViewModel.observe(this) { value ->
            value?.let {

                Log.i("ManageMArksResponse",value.get(0).student_name)
                val manageMarksList:List<ManageMarksOutputModel> = value

                try {
                    var adapter: ManageMarksAdapter = ManageMarksAdapter()
                    adapter.addHeaderAndSubmitList(manageMarksList)
                    binding.mmRecycle!!.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    binding.mmRecycle.adapter = adapter
                } catch (e: Exception) {
                    Log.i("subjectAdapterException",e.message)
                }


            }
        }
        viewModel.mutableExamListViewModel.observe(this) { value ->
            value?.let {

                Log.i("ExamListResponse",value.get(0).exam_name)
                val examList:List<ExamListOutputModel> = value

                var examAdapter = ArrayAdapter<ExamListOutputModel>(
                    context!!,
                    R.layout.dropdownlayout,
                    examList
                )
                examAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                binding.examDropdown.setAdapter(examAdapter)

            }
        }
        viewModel.mutableClassListViewModel.observe(this) { value ->
            value?.let {

                Log.i("ClassListResponse",value.get(0).class_name)
                val classList:List<ClassListOutputModel> = value

                var classAdapter = ArrayAdapter<ClassListOutputModel>(
                    context!!,
                    R.layout.dropdownlayout,
                    classList
                )
                binding.classDropdown.setAdapter(classAdapter)

            }
        }
        viewModel.mutableSectionListViewModel.observe(this) { value ->
            value?.let {

                Log.i("SectionListResponse",value.get(0).section_name)
                val sectionList:List<SectionListOutputModel> = value

                var sectionAdapter= ArrayAdapter<SectionListOutputModel>(
                    context!!,
                    R.layout.dropdownlayout,
                    sectionList
                )
                binding.sectionDropdown.setAdapter(sectionAdapter)

            }
        }
        viewModel.mutableSubjectListViewModel.observe(this) { value ->
            value?.let {

                Log.i("SubjectListResponse",value.get(0).subject_name)
                val subjectList:List<SubjectListOutputModel> = value

                var subjectAdapter= ArrayAdapter<SubjectListOutputModel>(
                    context!!,
                    R.layout.dropdownlayout,
                    subjectList
                )
                binding.subjectDropdown.setAdapter(subjectAdapter)

            }
        }

        return binding.root
    }
}