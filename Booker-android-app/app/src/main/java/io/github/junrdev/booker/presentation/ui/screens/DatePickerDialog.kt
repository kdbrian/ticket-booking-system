package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.databinding.FragmentDatePickerDialogBinding
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class DatePickerDialog : DialogFragment() {

    private var _binding: FragmentDatePickerDialogBinding? = null
    private val binding get() = _binding
    lateinit var pickedDate : Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDatePickerDialogBinding.inflate(inflater, container, false)
            .also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        var date: String? = null
        binding?.apply {
            calendarView.minDate = calendar.timeInMillis
            calendarView.setOnDateChangeListener { _, year, month, pickeddate ->
                pickedDate = Date().apply {
                    setYear(year)
                    setMonth(month)
                    setDate(pickeddate)
                }
                date = "$year $month $pickeddate"
            }

            button3.setOnClickListener {
                setFragmentResult("date", bundleOf("date" to pickedDate.toGMTString()))
                dismiss()
            }

        }

    }

    companion object {
        fun newInstance(): DatePickerDialog {
            val args = Bundle()
                .apply { }
            val fragment = DatePickerDialog()
            fragment.arguments = args
            return fragment
        }
    }

}