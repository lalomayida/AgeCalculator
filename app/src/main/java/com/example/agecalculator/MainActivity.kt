/*
* Autor: Luis Eduardo Mayida González
* Semestre: 7° Semestre
* Fecha de creación: 3 de octubre de 2020
* Materia: Programación de dispositivos móviles
* Tema: Proyecto Integrador Intersemestral
* */

package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDataPicker.setOnClickListener{view->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()

        val year= myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(
                this,
                "Year $selectedYear, " +
                        "Month ${selectedMonth+1}, " +
                        "Day $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/$selectedMonth/$selectedYear"
            tvSelectedDate.setText(selectedDate)

            val selectedDateInMinutes = getTimeInMillis(selectedDayOfMonth,selectedMonth,selectedYear)
            val currentDay = Date()
            val currentDateInMinutes = currentDay.time
            val differenceInMinutes = (currentDateInMinutes - selectedDateInMinutes)/60000
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
        },year,month,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
    fun getTimeInMillis(day: Int, month: Int, year: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[year, month] = day
        return calendar.timeInMillis
    }
}