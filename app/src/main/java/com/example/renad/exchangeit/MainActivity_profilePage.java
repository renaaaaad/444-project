package com.example.renad.exchangeit;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_profilePage extends AppCompatActivity {
private BottomNavigationView navigation;
private TextView name_text;
private DataSnapshot dataSnapshot;
private DatabaseReference databaseReference;
private String name;
private String uid;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main_profile_page);
name_text = (TextView)findViewById(R.id.name);

      FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
      FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
uid = firebaseUser.getUid();
databaseReference = FirebaseDatabase.getInstance().getReference();
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
name= dataSnapshot.child(uid).child("fname").getValue(String.class);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


        name_text.setText(name);


        navigation  = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    BottomNavigationView.OnNavigationItemSelectedListener  mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragment_profile fragment = new fragment_profile();
            fragment_seartch fragment_seartch = new fragment_seartch();
            fragment_sitting_option fragment_sitting = new fragment_sitting_option();
            fragment_request fragment_request = new fragment_request();
            switch (item.getItemId()) {
                case R.id.search:
                    loadFragment(fragment);
                 return true;
                 //-------------------------------------------------------------
                case R.id.profile:
                    loadFragment(fragment_seartch);
                    return true;
                 //------------------------------------------------------
                case R.id.sitting:
                    loadFragment(fragment_sitting);
                    return true;
                 //---------------------------------------------------
                case R.id.requests:
                    loadFragment(fragment_request);
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(fragment_request fragment_reqest) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment_reqest);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadFragment(fragment_sitting_option fragment_sitting) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment_sitting);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadFragment(fragment_seartch fragment_seartch) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment_seartch);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadFragment(fragment_profile fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
