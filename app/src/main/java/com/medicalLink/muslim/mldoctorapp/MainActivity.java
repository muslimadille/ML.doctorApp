package com.medicalLink.muslim.mldoctorapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    viewPager_adapter ViewPager_adapter;
    LinearLayout profile;
    LinearLayout appoint;
    LinearLayout remeeting;
    LinearLayout visitor;
    ImageView setting;

    private String days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=(ViewPager)findViewById(R.id.viewPAGER);
        ViewPager_adapter=new viewPager_adapter(this);
        viewPager.setAdapter(ViewPager_adapter);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
        //...............................................................................................

        profile=(LinearLayout)findViewById(R.id.profile_btn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, com.medicalLink.muslim.mldoctorapp.profile.class);
                startActivity(intent);
            }
        });
        //.........................................................................................................
        appoint=(LinearLayout)findViewById(R.id.appointment_btn);
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,appointment.class);
                startActivity(intent);
            }
        });
        //.......................................................................................................
        remeeting=(LinearLayout)findViewById(R.id.repetaion_btn);
        remeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,repeataion.class);
                startActivity(intent);
            }
        });
        //.....................................................................................................
        visitor=(LinearLayout)findViewById(R.id.visitation_btn);
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,visitors.class);
                startActivity(intent);

            }
        });





       Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);





        switch (day) {
            case Calendar.SUNDAY: days="الاحد";
                break;

            case Calendar.MONDAY:days="الاثنين";
                break;


            case Calendar.TUESDAY:days="التلات";
                break;
                // etc.
            case Calendar.WEDNESDAY:days="الاربع";
                break;
            case Calendar.THURSDAY:days="الخميس";
                break;
            case Calendar.SATURDAY:days="السبت";
                break;
            case Calendar.FRIDAY:days="الجمعة";
                break;
        }
       // Toast.makeText(MainActivity.this,days,Toast.LENGTH_LONG).show();

       //...........................................................................................
        setting=(ImageView)findViewById(R.id.setting_btn);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,setting);
                popupMenu.getMenuInflater().inflate(R.menu.settin_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public class MyTimerTask extends TimerTask {


        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }
                    else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
