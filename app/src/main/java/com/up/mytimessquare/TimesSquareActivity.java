package com.up.mytimessquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import TimesSquare.CalendarPickerView;
import TimesSquare.MonthView;

public class TimesSquareActivity extends AppCompatActivity implements CalendarPickerView.OnDateSelectedListener {
    private static final String TAG = "TimesSquareActivity";
    private CalendarPickerView calendar;
    public boolean isRoundTrip = false;
    private UserTicketChoose ticketChoose;
    private Intent intent;
    private String from = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_square);
        intent = getIntent();
        if(intent!=null){
            ticketChoose = (UserTicketChoose) intent.getExtras().get("userTicketChoose");
        }

        if (ticketChoose != null) {
            MonthView.tag[0] = "入住";
            MonthView.tag[1] = "离店";
        }

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Calendar nextDay = Calendar.getInstance();
        nextDay.add(Calendar.DAY_OF_MONTH,1);
        calendar.setOnDateSelectedListener(this);
        //Date selectedDates = new Date();
        Collection<Date> selectedDates = new ArrayList<>();
        selectedDates.add(ticketChoose.getStartDate());
        selectedDates.add(ticketChoose.getEndDate());
        calendar.init(new Date(), nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDates(selectedDates);

    }

    @Override
    public void onDateSelected(Date date) {
        if (ticketChoose.isRoundTrip()) {
            if (calendar.getSelectedDates().size() < 2) {
                Toast.makeText(this,"请选择返程日期",Toast.LENGTH_SHORT).show();
            } else {
                Date date1 = calendar.getSelectedDates().get(0);
                ticketChoose.setSDetailDate(formatDate(date1)[0]);
                ticketChoose.setSDate(formatDate(date1)[1]);
                ticketChoose.setSWeek(formatDate(date1)[2]);
                ticketChoose.setStartDate(date1);
                Date date2 = calendar.getSelectedDates().get(calendar.getSelectedDates().size() - 1);
                ticketChoose.setEDetailDate(formatDate(date2)[0]);
                ticketChoose.setEDate(formatDate(date2)[1]);
                ticketChoose.setEWeek(formatDate(date2)[2]);
                ticketChoose.setEndDate(date2);
                EventBus.getDefault().post(ticketChoose);
                finish();
            }
        } else {
            ticketChoose.setSDetailDate(formatDate(date)[0]);
            ticketChoose.setSDate(formatDate(date)[1]);
            ticketChoose.setSWeek(formatDate(date)[2]);
            EventBus.getDefault().post(ticketChoose);
            finish();
        }


    }

    public String[] formatDate(Date date) {
        String[] formateDate = new String[3];
        SimpleDateFormat formatStr = new SimpleDateFormat("MM月dd日", Locale.getDefault());
        String month = formatStr.format(date);
        formatStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String detailDate = formatStr.format(date);
        formatStr = new SimpleDateFormat("EEE", Locale.getDefault());
        String day = formatStr.format(date);

        formateDate[0] = detailDate;
        formateDate[1] = month;
        formateDate[2] = day;
        return formateDate;
    }

    @Override
    public void onDateUnselected(Date date) {

    }
}
