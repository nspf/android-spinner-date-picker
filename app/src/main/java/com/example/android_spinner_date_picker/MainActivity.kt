package com.example.android_spinner_date_picker

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.android_spinner_date_picker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.date.setOnClickListener { openSpinnerDialog() }
    }

    private fun openSpinnerDialog() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(this, R.style.SpinnerDatePickerDialog, { _, year, month, dayOfMonth ->
                binding.date.setText(formatDate(year, month, dayOfMonth))
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH])

        datePickerDialog.show()
    }

    private fun formatDate(date: Date) = SimpleDateFormat.getDateInstance().apply { timeZone = TimeZone.getTimeZone("UTC") }.format(date)

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int) = formatDate(Calendar.getInstance().apply {
        timeZone = TimeZone.getTimeZone("UTC")
        set(year, month, dayOfMonth)
    }.time)
}