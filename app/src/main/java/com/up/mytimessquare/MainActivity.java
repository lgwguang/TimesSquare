package com.up.mytimessquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tv_from_time;
    private TextView tv_from_day;
    private TextView tv_return_time;
    private TextView tv_return_day;
    private UserTicketChoose userTicketChoose;
    private Date startDate,endDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 注册订阅者
        EventBus.getDefault().register(this);
        userTicketChoose = new UserTicketChoose();
        tv_from_time = (TextView)findViewById(R.id.tv_from_time);
        tv_from_day = (TextView)findViewById(R.id.tv_from_day);
        tv_return_time = (TextView)findViewById(R.id.tv_return_time);
        tv_return_day = (TextView)findViewById(R.id.tv_return_day);


        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        endDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日/EEEE",Locale.getDefault());
        startDate = new Date();
        String time = sdf.format(startDate);
        String[] times = time.split("/");

        String nextTime = sdf.format(endDate);
        final String[] nextTimes = nextTime.split("/");

        tv_from_time.setText(times[0]);
        tv_from_day.setText(times[1]);
        tv_return_time.setText(nextTimes[0]);
        tv_return_day.setText(nextTimes[1]);


        /*findViewById(R.id.layout_from_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTicketChoose.setIsRoundTrip(false);
                Intent intent = new Intent();
                intent.putExtra("userTicketChoose", userTicketChoose);
                intent.setClass(MainActivity.this, TimesSquareActivity.class);
                startActivity(intent);
            }
        });*/
        findViewById(R.id.layout_return_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTicketChoose.setIsRoundTrip(true);
                userTicketChoose.setStartDate(startDate);
                userTicketChoose.setEndDate(endDate);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
                System.out.println("点击====================="+sdf2.format(startDate));
                System.out.println("点击====================="+sdf2.format(endDate));

                Intent intent = new Intent();
                intent.putExtra("userTicketChoose", userTicketChoose);
                intent.setClass(MainActivity.this, TimesSquareActivity.class);
                startActivity(intent);
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserTicketChoose userChooseBack) {
        // 更新界面
        tv_from_time.setText(userChooseBack.getSDate());
        tv_from_day.setText(userChooseBack.getSWeek());
        userTicketChoose.setSDetailDate(userChooseBack.getSDetailDate());
        userTicketChoose.setEDetailDate(userChooseBack.getEDetailDate());
        if(userTicketChoose.isRoundTrip())
        {
            tv_return_time.setText(userChooseBack.getEDate());
            tv_return_day.setText(userChooseBack.getEWeek());
        }
        startDate = userChooseBack.getStartDate();
        endDate = userChooseBack.getEndDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println("====================="+sdf2.format(startDate));
        System.out.println("====================="+sdf2.format(endDate));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }
}
