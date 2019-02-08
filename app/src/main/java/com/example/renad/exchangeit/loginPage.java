package com.example.renad.exchangeit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginPage extends AppCompatActivity {

  public  EditText mail ;
  public EditText pass;
  public Button logInButton ;
  private FirebaseAuth firebaseAuth;
  private ProgressDialog processDialog;
  private TextView button_forgetPass;
  private String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        // to check if the user is log in or not

         logInButton = (Button)findViewById(R.id.button2);
         mail = (EditText)findViewById(R.id.email);
         pass = (EditText)findViewById(R.id.passord);
         firebaseAuth= FirebaseAuth.getInstance();
        button_forgetPass =(TextView)findViewById(R.id.forgetPassword);
        button_forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),forgot_password.class));
            }
        });



    }

//===============================================================================================

public void login(View view)
{
String emailenterd = mail.getText().toString();
String passenterd = pass.getText().toString();

if(emailenterd==null){
    mail.setError("Please Enter Your E-mail  ");
    mail.requestFocus();
    return;
}
if(passenterd==null){
    pass.setError("Please Enter Your Password  ");
    pass.requestFocus();
    return;
}


firebaseAuth.signInWithEmailAndPassword(emailenterd,passenterd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {


        if(task.isSuccessful()){
            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity_profilePage.class));
            }
            else {
                mail.setError("Please Verify This Email, Check your Email  ");
                mail.requestFocus();            }

        }

        else {
            mail.setError("The E-mail Is Wrong or The Password is Uncorrect   ");
            mail.requestFocus();
        }


    }
});

}

public void register(View view)
{
Intent redisterPage = new Intent(loginPage.this,registerPage.class);
startActivity(redisterPage);
}

    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),forgetPasseord.class));
    }
}// class
