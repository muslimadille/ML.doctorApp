package com.medicalLink.muslim.mldoctorapp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signin extends AppCompatActivity {
    private EditText doc_mail;
    private EditText doc_pass;
    private Button sign_btn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mStatlistener;
    public String doc_id;
    private DatabaseReference mDataBase;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        mAuth=FirebaseAuth.getInstance();

        mDataBase=FirebaseDatabase.getInstance().getReference().child("doctors");
        mProgress=new ProgressDialog(this);















        doc_mail=(EditText)findViewById(R.id.doc_mail);
        doc_pass=(EditText)findViewById(R.id.doc_pass);
        sign_btn=(Button)findViewById(R.id.signin_btn);
        //............................................................................
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startSignin();


            }
        });
        //............................................................................

        mStatlistener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (mAuth.getCurrentUser()!=null){

                    doc_id= mAuth.getCurrentUser().getUid();
                    String EMAIL=doc_mail.getText().toString();
                    String PASS=doc_pass.getText().toString();
                    mDataBase.child(doc_id).child("mail").setValue(EMAIL);
                    mDataBase.child(doc_id).child("pass").setValue(PASS);

                    Intent intent=new Intent(signin.this,MainActivity.class);
                    startActivity(intent);

                }

            }
        };


    }
    //.........................................................................................................................

    private void startSignin() {
        String Email=doc_mail.getText().toString();
        String pass =doc_pass.getText().toString();

        if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(pass)){

            Toast.makeText(signin.this,"البريد او الرقم السري فارغ....اكمل البيانات",Toast.LENGTH_SHORT).show();
        }

        else {

            mProgress.setMessage("جاري تحميل البيانات");
            mProgress.show();
            mAuth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    mProgress.dismiss();
                    if (!task.isSuccessful()){
                        Toast.makeText(signin.this,"البيانات غير صحيحة....جرب مرة اخري",Toast.LENGTH_SHORT).show();


                    }

                }
            });
        }


    }
    //..............................................................................................................................
    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mStatlistener);

    }
}
