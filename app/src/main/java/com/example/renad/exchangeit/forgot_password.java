package com.example.renad.exchangeit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
private Button button_forgetPass;
private String user_email;
FirebaseAuth firebaseAuth ;
private EditText email2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth = FirebaseAuth.getInstance();
        email2 = (EditText)findViewById(R.id.email) ;

        button_forgetPass =(Button) findViewById(R.id.button2);
        button_forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string =  email2.getText().toString();
               if(string!=null) {
                   firebaseAuth.sendPasswordResetEmail(email2.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               startActivity(new Intent(getApplicationContext(),loginPage.class));
                           }
                           else {
                               email2.setError("Error With This Password  ");
                               email2.requestFocus();
                           }
                       }
                   });
               }//if
                else {
                   email2.setError("Please enter The Email Address   ");
                   email2.requestFocus();
               }

            }
        });

    }//on create
}
