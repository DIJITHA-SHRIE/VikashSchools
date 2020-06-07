package com.example.vedanam.Util

import android.R.color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.*


class EventDecorator(val attendanceColor:Int,val attendnceDays: ArrayList<CalendarDay>) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {

        return  attendnceDays.contains(day)

    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(DotSpan(10F, attendanceColor))
    }

}