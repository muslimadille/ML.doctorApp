package com.medicalLink.muslim.mldoctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    private DatabaseReference mDataBase;
    private FirebaseAuth mAuth;

    public String doc_id;

    private TextView name;//
    private TextView visitors;//
    private TextView rate;//
    private TextView info;//
    private TextView address;//
    private TextView clinic_services;
    private TextView days;
    private TextView from;//
    private TextView to;//
    private TextView price;//
    private TextView reprice;//
    private TextView per_phone;//
    private TextView clinc_phone;//
    private TextView mail;//
    private TextView face;//
    private TextView gendar;//
    private TextView city;//
    private TextView field;


    private RatingBar ratingBar2;
    private String SAT="";
    private String SUN="";
    private String MON="";
    private String TUE="";
    private String WED="";
    private String THU="";
    private String FRI="";
    private String DAYS="";

    private ImageView home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth=FirebaseAuth.getInstance();
        doc_id=mAuth.getCurrentUser().getUid();
        mDataBase=FirebaseDatabase.getInstance().getReference().child("doctors").child(doc_id);
        home_btn=(ImageView)findViewById(R.id.home_btn);
        //.............................................................................................
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //..............................................................................................



        //..............................................................................................
        ratingBar2=(RatingBar)findViewById(R.id.ratingBar2);
        name=(TextView)findViewById(R.id.text_name);
        field=(TextView)findViewById(R.id.text_field);
        visitors=(TextView)findViewById(R.id.text_visitors);
        rate=(TextView)findViewById(R.id.text_rate);
        info=(TextView)findViewById(R.id.text_info);
        address=(TextView)findViewById(R.id.text_address);
        clinic_services=(TextView)findViewById(R.id.text_services);
        days=(TextView)findViewById(R.id.text_days);
        from=(TextView)findViewById(R.id.text_from);
        to=(TextView)findViewById(R.id.text_to);
        price=(TextView)findViewById(R.id.text_price);
        reprice=(TextView)findViewById(R.id.text_reprice);
        per_phone=(TextView)findViewById(R.id.text_doc_phone);
        clinc_phone=(TextView)findViewById(R.id.text_clinic_phone);
        mail=(TextView)findViewById(R.id.text_mail);
        face=(TextView)findViewById(R.id.text_face);
        gendar=(TextView)findViewById(R.id.text_gendar);
        city=(TextView)findViewById(R.id.text_city);
     //.............................................................................................

        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String VISITORS=(String) dataSnapshot.child("visitors").getValue();
                String RATE=(String) dataSnapshot.child("rates").getValue();
                String NAME=(String) dataSnapshot.child("name").getValue();
                String INFO=(String) dataSnapshot.child("info").getValue();
                String ADD=(String) dataSnapshot.child("address").getValue();
                String SERVICES=(String) dataSnapshot.child("services").getValue();
                String FROM=(String) dataSnapshot.child("from").getValue();
                String TO=(String) dataSnapshot.child("to").getValue();
                String PRICE=(String) dataSnapshot.child("price").getValue();
                String REPRICE=(String) dataSnapshot.child("reprice").getValue();
                String GENDAR=(String) dataSnapshot.child("gendar").getValue();
                String CITY=(String) dataSnapshot.child("region").getValue();
                String FIELD=(String) dataSnapshot.child("field").getValue();
                String PER_PHONE=(String) dataSnapshot.child("contacts").child("d_phone").getValue();
                String C_PHONE=(String) dataSnapshot.child("contacts").child("c_phone").getValue();
                String MAIL=(String) dataSnapshot.child("contacts").child("mail").getValue();
                String FACE=(String) dataSnapshot.child("contacts").child("face").getValue();

                boolean sat =(boolean)dataSnapshot.child("days").child("sat").getValue();
                boolean sun =(boolean)dataSnapshot.child("days").child("sun").getValue();
                boolean mon =(boolean)dataSnapshot.child("days").child("mon").getValue();
                boolean tue=(boolean)dataSnapshot.child("days").child("tue").getValue();
                boolean wed =(boolean)dataSnapshot.child("days").child("wed").getValue();
                boolean thu =(boolean)dataSnapshot.child("days").child("thu").getValue();
                boolean fri =(boolean)dataSnapshot.child("days").child("fri").getValue();
            //..................................................................................
                //....................................................................
                name.setText("د."+NAME);
                field.setText(FIELD);
                rate.setText(RATE);
                price.setText(PRICE);
                info.setText(INFO);
                from.setText(FROM);
                to.setText(TO);
                clinic_services.setText(SERVICES);
                address.setText(ADD);
                ratingBar2.setRating(Float.parseFloat(RATE));
                visitors.setText(VISITORS);
                reprice.setText(REPRICE);
                per_phone.setText(PER_PHONE);
                clinc_phone.setText(C_PHONE);
                mail.setText(MAIL);
                face.setText(FACE);
                gendar.setText(GENDAR);
                city.setText(CITY);

                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                if (sat){
                    SAT="السبت,";

                }
                if(sun){
                    SUN="الاحد,";
                }
                if(mon){
                    MON="الاثنين,";
                }
                if(tue){
                    TUE="الثلاثاء,";
                }
                if(wed){
                    WED="الاربعاء,";
                }
                if(thu){
                    THU="الخميس,";
                }
                if(fri){
                    FRI="الجمعة,";
                }

                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                DAYS  =SAT+SUN+MON+TUE+WED+THU+FRI;
                days.setText(DAYS);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
     //.............................................................................................

    }
}
