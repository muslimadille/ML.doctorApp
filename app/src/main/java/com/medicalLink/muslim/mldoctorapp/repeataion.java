package com.medicalLink.muslim.mldoctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class repeataion extends AppCompatActivity {
    private FirebaseAuth mAuthent;
    private DatabaseReference order_DataBase;
    private DatabaseReference accepted_DataBase;
    private DatabaseReference refused_DataBase;
    private DatabaseReference doc_DataBase;

    private RecyclerView dlist;


    private CardView orders_btn;
    private CardView accepted_btn;
    private CardView refused_btn;

    private ImageView next_day;
    private ImageView pre_day;

    private TextView date;
    private TextView DAY_TXT;

    private String days;
    private String Did;
    private String CurrentYear;
    private String CurrentMonth;
    private String CurrentDay;
    private String CurrentHour;
    private String CurrentMinit;
    private String CurrentSecond;
    private String y;
    private String mon;
    private String d;
    private String h;
    private String min;
    private String sec;
    private String totalTime;
    private String totalClock;
    private String formattedDate;
    private String CHODAY;
    private String initialdate;
    private String engDATE;
    private ImageView home_btn;

    int l = 9;
    int m = 9;
    int n = 9;
    int o = 9;
    int p = 9;
    int q = 9;
    int r = 9;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeataion);


        next_day = (ImageView) findViewById(R.id.next_day);
        pre_day = (ImageView) findViewById(R.id.previos_day);
        date = (TextView) findViewById(R.id.date_txt);
        DAY_TXT = (TextView) findViewById(R.id.day_text);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        //.............................................................................................
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(repeataion.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //..............................................................................................


        //..............................................................................................
        //time

        String NN = Locale.getDefault().getDisplayLanguage();
        SimpleDateFormat syf = new SimpleDateFormat("yyy");
        SimpleDateFormat smonf = new SimpleDateFormat("MM");
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        SimpleDateFormat shf = new SimpleDateFormat("HH");
        SimpleDateFormat sminf = new SimpleDateFormat("mm");
        SimpleDateFormat ssf = new SimpleDateFormat("ss");


        y = syf.format(new Date());
        mon = smonf.format(new Date());
        d = sdf.format(new Date());
        h = shf.format(new Date());
        min = sminf.format(new Date());
        sec = ssf.format(new Date());

        if (NN.equals("العربية")) {

            CurrentYear = arabicToDecimal(y);
            CurrentMonth = arabicToDecimal(mon);
            CurrentDay = arabicToDecimal(d);
            CurrentHour = arabicToDecimal(h);
            CurrentMinit = arabicToDecimal(min);
            CurrentSecond = arabicToDecimal(sec);
            totalTime = CurrentYear + "_" + CurrentMonth + "_" + CurrentDay;
            totalClock = CurrentHour + ":" + CurrentMinit + ":" + CurrentSecond;
            // Toast.makeText(Main2Activity.this,totalTime,Toast.LENGTH_LONG).show();
        } else {
            CurrentYear = y;
            CurrentMonth = mon;
            CurrentDay = d;
            CurrentHour = h;
            CurrentMinit = min;
            CurrentSecond = sec;
            totalTime = CurrentYear + "_" + CurrentMonth + "_" + CurrentDay;
            totalClock = CurrentHour + ":" + CurrentMinit + ":" + CurrentSecond;

        }
        // ..........................................................................................................................
        //intialization

        dlist = (RecyclerView) findViewById(R.id.appointment_recyc);
        dlist.setHasFixedSize(true);
        dlist.setLayoutManager(new LinearLayoutManager(this));
        mAuthent = FirebaseAuth.getInstance();
        Did = mAuthent.getCurrentUser().getUid();
        order_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("orders").child(totalTime);
        doc_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did);
        //......................................................................................................................................................
        doc_DataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                boolean sat = (boolean) dataSnapshot.child("days").child("sat").getValue();
                boolean sun = (boolean) dataSnapshot.child("days").child("sun").getValue();
                boolean mon = (boolean) dataSnapshot.child("days").child("mon").getValue();
                boolean tue = (boolean) dataSnapshot.child("days").child("tue").getValue();
                boolean wed = (boolean) dataSnapshot.child("days").child("wed").getValue();
                boolean thu = (boolean) dataSnapshot.child("days").child("thu").getValue();
                boolean fri = (boolean) dataSnapshot.child("days").child("fri").getValue();

                if (sat) {
                    l = 7;
                }
                if (sun) {
                    m = 1;
                }
                if (mon) {
                    n = 2;
                }
                if (tue) {
                    o = 3;
                }
                if (wed) {
                    p = 4;
                }
                if (thu) {
                    q = 5;
                }
                if (fri) {
                    r = 6;
                }

                //...............................................................................................
                final Calendar calendar = Calendar.getInstance();
                final SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");

                int i = 0;
                for (int x = 0; ; x++) {

                    formattedDate = df.format(calendar.getTime());

                    calendar.add(Calendar.DATE, i);
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    if (day == l || day == m || day == n || day == o || day == p || day == q || day == r) {

                        formattedDate = df.format(calendar.getTime());

                        int day_name = calendar.get(Calendar.DAY_OF_WEEK);
                        switch (day_name) {
                            case (Calendar.SUNDAY):
                                days = "الاحد";
                                break;
                            case Calendar.MONDAY:
                                days = "الاثنين";
                                break;
                            case Calendar.TUESDAY:
                                days = "الثلاثاء";
                                break;
                            case Calendar.WEDNESDAY:
                                days = "الاربعاء";
                                break;
                            case Calendar.THURSDAY:
                                days = "الخميس";
                                break;
                            case Calendar.FRIDAY:
                                days = "الجمعة";
                                break;
                            case Calendar.SATURDAY:
                                days = "السبت";
                                break;
                        }

                        initialdate = formattedDate;
                        date.setText(formattedDate);
                        DAY_TXT.setText(days);
                        CHODAY = date.getText().toString();
                        engDATE = arabicToDecimal(CHODAY);


                        break;

                    }
                    i = 1;


                }
                //.............................................................................................................................
                //NEXT DATE


                next_day.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for (int i = 1; i < 8 || i > 8; i++) {
                            int x = 1;
                            calendar.add(Calendar.DATE, +x);
                            int day = calendar.get(Calendar.DAY_OF_WEEK);
                            if (day == l || day == m || day == n || day == o || day == p || day == q || day == r) {

                                formattedDate = df.format(calendar.getTime());
                                int day_name = calendar.get(Calendar.DAY_OF_WEEK);

                                switch (day_name) {
                                    case (Calendar.SUNDAY):
                                        days = "الاحد";
                                        break;
                                    case Calendar.MONDAY:
                                        days = "الاثنين";
                                        break;
                                    case Calendar.TUESDAY:
                                        days = "الثلاثاء";
                                        break;
                                    case Calendar.WEDNESDAY:
                                        days = "الاربعاء";
                                        break;
                                    case Calendar.THURSDAY:
                                        days = "الخميس";
                                        break;
                                    case Calendar.FRIDAY:
                                        days = "الجمعة";
                                        break;
                                    case Calendar.SATURDAY:
                                        days = "السبت";
                                        break;
                                }

                                date.setText(formattedDate);
                                DAY_TXT.setText(days);
                                CHODAY = date.getText().toString();
                                engDATE = arabicToDecimal(CHODAY);
                                //.....................................................................................................................................................

                                //RECYCLER VIEW

                                accepted_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("accepted").child(engDATE);
                                refused_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("refused").child(engDATE);

                                FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                        (
                                                appointment_adapter.class,
                                                R.layout.repeatation_item,
                                                repeataion.appointmentViewHolder.class,
                                                order_DataBase.orderByChild("book_state").equalTo("اعادة")

                                        ) {
                                    @Override
                                    protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                        viewHolder.setPatient_name(model.getPatient_name());
                                        viewHolder.setPatient_address(model.getPatient_address());
                                        viewHolder.setPatient_time(model.getPatient_time());
                                        viewHolder.setPatient_id(model.getPatient_id());
                                        viewHolder.setPatient_phone(model.getPatient_phone());


                                    }
                                };
                                dlist.setAdapter(firebaseRecyclerAdapter);
                                firebaseRecyclerAdapter.notifyDataSetChanged();
                                //......................................................................................................................
                                orders_btn = (CardView) findViewById(R.id.orders_btn);
                                orders_btn.setBackgroundResource(R.drawable.tab_bg_blu_left);
                                orders_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        orders_btn.setBackgroundResource(R.drawable.tab_bg_blu_left);
                                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);
                                        refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);

                                        FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                                (
                                                        appointment_adapter.class,
                                                        R.layout.repeatation_item,
                                                        repeataion.appointmentViewHolder.class,
                                                        order_DataBase.orderByChild("book_state").equalTo("اعادة")

                                                ) {
                                            @Override
                                            protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                                viewHolder.setPatient_name(model.getPatient_name());
                                                viewHolder.setPatient_address(model.getPatient_address());
                                                viewHolder.setPatient_time(model.getPatient_time());
                                                viewHolder.setPatient_id(model.getPatient_id());
                                                viewHolder.setPatient_phone(model.getPatient_phone());


                                            }
                                        };
                                        dlist.setAdapter(firebaseRecyclerAdapter);
                                        firebaseRecyclerAdapter.notifyDataSetChanged();
                                    }
                                });
                                //......................................................................................................................
                                accepted_btn = (CardView) findViewById(R.id.accepted_btn);
                                accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);
                                accepted_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_bleu_center);
                                        orders_btn.setBackgroundResource(R.drawable.tab_bg_whit_left);
                                        refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);
                                        FirebaseRecyclerAdapter<accepted_adapter, repeataion.acceptedViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<accepted_adapter, acceptedViewHolder>
                                                (
                                                        accepted_adapter.class,
                                                        R.layout.repeatation_item,
                                                        repeataion.acceptedViewHolder.class,
                                                        accepted_DataBase.orderByChild("book_state").equalTo("اعادة")


                                                ) {
                                            @Override
                                            protected void populateViewHolder(acceptedViewHolder viewHolder, accepted_adapter model, int position) {
                                                viewHolder.setPatient_name(model.getPatient_name());
                                                viewHolder.setPatient_address(model.getPatient_address());
                                                viewHolder.setPatient_id(model.getPatient_id());
                                                viewHolder.setMeeting_time(model.getMeeting_time());
                                                viewHolder.setPatient_number(model.getPatient_number());
                                                viewHolder.setPatient_phone(model.getPatient_phone());


                                            }
                                        };
                                        dlist.setAdapter(firebaseRecyclerAdapter1);
                                        firebaseRecyclerAdapter1.notifyDataSetChanged();


                                    }
                                });
                                //......................................................................................................................
                                refused_btn = (CardView) findViewById(R.id.refused_btn);
                                refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);
                                refused_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        refused_btn.setBackgroundResource(R.drawable.tab_bg_blue_rigt);
                                        orders_btn.setBackgroundResource(R.drawable.tab_bg_whit_left);
                                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);

                                        FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                                (
                                                        appointment_adapter.class,
                                                        R.layout.repeatation_item,
                                                        repeataion.appointmentViewHolder.class,
                                                        refused_DataBase.orderByChild("book_state").equalTo("اعادة")

                                                ) {
                                            @Override
                                            protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                                viewHolder.setPatient_name(model.getPatient_name());
                                                viewHolder.setPatient_address(model.getPatient_address());
                                                viewHolder.setPatient_time(model.getPatient_time());
                                                viewHolder.setPatient_id(model.getPatient_id());
                                                viewHolder.setPatient_phone(model.getPatient_phone());


                                            }
                                        };
                                        dlist.setAdapter(firebaseRecyclerAdapter);
                                        firebaseRecyclerAdapter.notifyDataSetChanged();
                                    }
                                });
                                //.............................................................................................................


                                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


                                break;

                            } else x++;

                        }


                    }
                });
                pre_day.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for (int i = 1; !formattedDate.equals(initialdate); i++) {
                            int x = -1;
                            calendar.add(Calendar.DATE, x);
                            int day = calendar.get(Calendar.DAY_OF_WEEK);
                            if (day == l || day == m || day == n || day == o || day == p || day == q || day == r) {


                                formattedDate = df.format(calendar.getTime());

                                int day_name = calendar.get(Calendar.DAY_OF_WEEK);

                                switch (day_name) {
                                    case (Calendar.SUNDAY):
                                        days = "الاحد";
                                        break;
                                    case Calendar.MONDAY:
                                        days = "الاثنين";
                                        break;
                                    case Calendar.TUESDAY:
                                        days = "الثلاثاء";
                                        break;
                                    case Calendar.WEDNESDAY:
                                        days = "الاربعاء";
                                        break;
                                    case Calendar.THURSDAY:
                                        days = "الخميس";
                                        break;
                                    case Calendar.FRIDAY:
                                        days = "الجمعة";
                                        break;
                                    case Calendar.SATURDAY:
                                        days = "السبت";
                                        break;
                                }

                                date.setText(formattedDate);
                                DAY_TXT.setText(days);
                                CHODAY = date.getText().toString();
                                engDATE = arabicToDecimal(CHODAY);

                                //.....................................................................................................................................................

                                //.....................................................................................................................................................
                                accepted_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("accepted").child(engDATE);
                                refused_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("refused").child(engDATE);

                                FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                        (
                                                appointment_adapter.class,
                                                R.layout.repeatation_item,
                                                repeataion.appointmentViewHolder.class,
                                                order_DataBase.orderByChild("book_state").equalTo("اعادة")

                                        ) {
                                    @Override
                                    protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                        viewHolder.setPatient_name(model.getPatient_name());
                                        viewHolder.setPatient_address(model.getPatient_address());
                                        viewHolder.setPatient_time(model.getPatient_time());
                                        viewHolder.setPatient_id(model.getPatient_id());
                                        viewHolder.setPatient_phone(model.getPatient_phone());


                                    }
                                };
                                dlist.setAdapter(firebaseRecyclerAdapter);
                                firebaseRecyclerAdapter.notifyDataSetChanged();
                                //......................................................................................................................
                                orders_btn = (CardView) findViewById(R.id.orders_btn);
                                orders_btn.setBackgroundResource(R.drawable.tab_bg_blu_left);
                                orders_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        orders_btn.setBackgroundResource(R.drawable.tab_bg_blu_left);
                                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);
                                        refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);

                                        FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                                (
                                                        appointment_adapter.class,
                                                        R.layout.repeatation_item,
                                                        repeataion.appointmentViewHolder.class,
                                                        order_DataBase.orderByChild("book_state").equalTo("اعادة")

                                                ) {
                                            @Override
                                            protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                                viewHolder.setPatient_name(model.getPatient_name());
                                                viewHolder.setPatient_address(model.getPatient_address());
                                                viewHolder.setPatient_time(model.getPatient_time());
                                                viewHolder.setPatient_id(model.getPatient_id());
                                                viewHolder.setPatient_phone(model.getPatient_phone());


                                            }
                                        };
                                        dlist.setAdapter(firebaseRecyclerAdapter);
                                        firebaseRecyclerAdapter.notifyDataSetChanged();
                                    }
                                });
                                //......................................................................................................................
                                accepted_btn = (CardView) findViewById(R.id.accepted_btn);
                                accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);
                                accepted_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_bleu_center);
                                        orders_btn.setBackgroundResource(R.drawable.tab_bg_whit_left);
                                        refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);
                                        FirebaseRecyclerAdapter<accepted_adapter, repeataion.acceptedViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<accepted_adapter, acceptedViewHolder>
                                                (
                                                        accepted_adapter.class,
                                                        R.layout.repeatation_item,
                                                        repeataion.acceptedViewHolder.class,
                                                        accepted_DataBase.orderByChild("book_state").equalTo("اعادة")


                                                ) {
                                            @Override
                                            protected void populateViewHolder(acceptedViewHolder viewHolder, accepted_adapter model, int position) {
                                                viewHolder.setPatient_name(model.getPatient_name());
                                                viewHolder.setPatient_address(model.getPatient_address());
                                                viewHolder.setPatient_id(model.getPatient_id());
                                                viewHolder.setMeeting_time(model.getMeeting_time());
                                                viewHolder.setPatient_number(model.getPatient_number());
                                                viewHolder.setPatient_phone(model.getPatient_phone());


                                            }
                                        };
                                        dlist.setAdapter(firebaseRecyclerAdapter1);
                                        firebaseRecyclerAdapter1.notifyDataSetChanged();


                                    }
                                });
                                //......................................................................................................................
                                refused_btn = (CardView) findViewById(R.id.refused_btn);
                                refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);
                                refused_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        refused_btn.setBackgroundResource(R.drawable.tab_bg_blue_rigt);
                                        orders_btn.setBackgroundResource(R.drawable.tab_bg_whit_left);
                                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);

                                        FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                                (
                                                        appointment_adapter.class,
                                                        R.layout.repeatation_item,
                                                        repeataion.appointmentViewHolder.class,
                                                        refused_DataBase.orderByChild("book_state").equalTo("اعادة")

                                                ) {
                                            @Override
                                            protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                                viewHolder.setPatient_name(model.getPatient_name());
                                                viewHolder.setPatient_address(model.getPatient_address());
                                                viewHolder.setPatient_time(model.getPatient_time());
                                                viewHolder.setPatient_id(model.getPatient_id());
                                                viewHolder.setPatient_phone(model.getPatient_phone());


                                            }
                                        };
                                        dlist.setAdapter(firebaseRecyclerAdapter);
                                        firebaseRecyclerAdapter.notifyDataSetChanged();
                                    }
                                });
                                //.............................................................................................................


                                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


                                break;
                            } else x--;
                        }
                        ///////////////////////////////////////
                        //  c.add(Calendar.DATE, -1);
                        //  formattedDate = df.format(c.getTime());

                        //  Log.v("PREVIOUS DATE : ", formattedDate);
                        //  date.setText(formattedDate);
                    }
                });
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                //.....................................................................................................................................................
                accepted_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("accepted").child(engDATE);
                refused_DataBase = FirebaseDatabase.getInstance().getReference().child("doctors").child(Did).child("booking").child("refused").child(engDATE);

                FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                        (
                                appointment_adapter.class,
                                R.layout.repeatation_item,
                                repeataion.appointmentViewHolder.class,
                                order_DataBase.orderByChild("book_state").equalTo("اعادة")

                        ) {
                    @Override
                    protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                        viewHolder.setPatient_name(model.getPatient_name());
                        viewHolder.setPatient_address(model.getPatient_address());
                        viewHolder.setPatient_time(model.getPatient_time());
                        viewHolder.setPatient_id(model.getPatient_id());
                        viewHolder.setPatient_phone(model.getPatient_phone());


                    }
                };
                dlist.setAdapter(firebaseRecyclerAdapter);
                firebaseRecyclerAdapter.notifyDataSetChanged();
                //......................................................................................................................
                orders_btn = (CardView) findViewById(R.id.orders_btn);
                orders_btn.setBackgroundResource(R.drawable.tab_bg_blu_left);
                orders_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orders_btn.setBackgroundResource(R.drawable.tab_bg_blu_left);
                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);
                        refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);

                        FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                (
                                        appointment_adapter.class,
                                        R.layout.repeatation_item,
                                        repeataion.appointmentViewHolder.class,
                                        order_DataBase.orderByChild("book_state").equalTo("اعادة")

                                ) {
                            @Override
                            protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                viewHolder.setPatient_name(model.getPatient_name());
                                viewHolder.setPatient_address(model.getPatient_address());
                                viewHolder.setPatient_time(model.getPatient_time());
                                viewHolder.setPatient_id(model.getPatient_id());
                                viewHolder.setPatient_phone(model.getPatient_phone());


                            }
                        };
                        dlist.setAdapter(firebaseRecyclerAdapter);
                        firebaseRecyclerAdapter.notifyDataSetChanged();
                    }
                });
                //......................................................................................................................
                accepted_btn = (CardView) findViewById(R.id.accepted_btn);
                accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);
                accepted_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_bleu_center);
                        orders_btn.setBackgroundResource(R.drawable.tab_bg_whit_left);
                        refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);
                        FirebaseRecyclerAdapter<accepted_adapter, repeataion.acceptedViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<accepted_adapter, acceptedViewHolder>
                                (
                                        accepted_adapter.class,
                                        R.layout.repeatation_item,
                                        repeataion.acceptedViewHolder.class,
                                        accepted_DataBase.orderByChild("book_state").equalTo("اعادة")


                                ) {
                            @Override
                            protected void populateViewHolder(acceptedViewHolder viewHolder, accepted_adapter model, int position) {
                                viewHolder.setPatient_name(model.getPatient_name());
                                viewHolder.setPatient_address(model.getPatient_address());
                                viewHolder.setPatient_id(model.getPatient_id());
                                viewHolder.setMeeting_time(model.getMeeting_time());
                                viewHolder.setPatient_number(model.getPatient_number());
                                viewHolder.setPatient_phone(model.getPatient_phone());


                            }
                        };
                        dlist.setAdapter(firebaseRecyclerAdapter1);
                        firebaseRecyclerAdapter1.notifyDataSetChanged();


                    }
                });
                //......................................................................................................................
                refused_btn = (CardView) findViewById(R.id.refused_btn);
                refused_btn.setBackgroundResource(R.drawable.tab_bg_whit_rigt);
                refused_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refused_btn.setBackgroundResource(R.drawable.tab_bg_blue_rigt);
                        orders_btn.setBackgroundResource(R.drawable.tab_bg_whit_left);
                        accepted_btn.setBackgroundResource(R.drawable.tab_bg_whit_center);

                        FirebaseRecyclerAdapter<appointment_adapter, repeataion.appointmentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<appointment_adapter, appointmentViewHolder>
                                (
                                        appointment_adapter.class,
                                        R.layout.repeatation_item,
                                        repeataion.appointmentViewHolder.class,
                                        refused_DataBase.orderByChild("book_state").equalTo("اعادة")

                                ) {
                            @Override
                            protected void populateViewHolder(appointmentViewHolder viewHolder, appointment_adapter model, int position) {
                                viewHolder.setPatient_name(model.getPatient_name());
                                viewHolder.setPatient_address(model.getPatient_address());
                                viewHolder.setPatient_time(model.getPatient_time());
                                viewHolder.setPatient_id(model.getPatient_id());


                            }
                        };
                        dlist.setAdapter(firebaseRecyclerAdapter);
                        firebaseRecyclerAdapter.notifyDataSetChanged();
                    }
                });
                //.............................................................................................................


                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


                //................................................................................................

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //..........................................................................................................................................


//................................................................................................................
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static class appointmentViewHolder extends RecyclerView.ViewHolder {
        View mView;


        public appointmentViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setPatient_name(String patient_name) {
            TextView Nam = (TextView) mView.findViewById(R.id.text_customer_name);
            Nam.setText(patient_name);
        }

        public void setPatient_address(String patient_address) {
            TextView ADD = (TextView) mView.findViewById(R.id.text_costumer_add);
            ADD.setText(patient_address);
        }

        public void setPatient_id(String patient_id) {
            TextView pid = (TextView) mView.findViewById(R.id.text_costumer_id);
            pid.setText(patient_id);

        }

        public void setPatient_time(String patient_time) {
            TextView ptime = (TextView) mView.findViewById(R.id.text_order_time);
            ptime.setText(patient_time);


        }

        public void setPatient_phone(String patient_phone) {
            TextView P_PHONE = (TextView) mView.findViewById(R.id.text_costumer_phone);
            P_PHONE.setText(patient_phone);
        }


    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static class acceptedViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public acceptedViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setPatient_name(String patient_name) {
            TextView Nam = (TextView) mview.findViewById(R.id.text_customer_name);
            Nam.setText(patient_name);
        }

        public void setPatient_address(String patient_address) {
            TextView ADD = (TextView) mview.findViewById(R.id.text_costumer_add);
            ADD.setText(patient_address);
        }

        public void setPatient_id(String patient_id) {
            TextView pid = (TextView) mview.findViewById(R.id.text_costumer_id);
            pid.setText(patient_id);

        }

        public void setMeeting_time(String patient_time) {
            TextView ptime = (TextView) mview.findViewById(R.id.text_order_time);
            ptime.setText(patient_time);


        }

        public void setPatient_number(String patient_number) {
            TextView p_num = (TextView) mview.findViewById(R.id.text_costumer_num);
            p_num.setText(patient_number);
        }

        public void setPatient_phone(String patient_phone) {
            TextView P_PHONE = (TextView) mview.findViewById(R.id.text_costumer_phone);
            P_PHONE.setText(patient_phone);

        }
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //تحويل الارقام العربية للانجليزية


    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

    private static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
