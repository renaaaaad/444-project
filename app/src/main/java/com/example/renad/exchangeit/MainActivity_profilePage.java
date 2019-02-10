package com.example.renad.exchangeit;

import android.app.Fragment;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.annotation.GlideModule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_profilePage extends AppCompatActivity {
    private BottomNavigationView navigation;
    private TextView name_text;
    private String name_from_firebase;
    private String user_id;
    private String user_name;
    ImageView test ;
    //------------------------------------------------ the data base varible
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile_page);
        name_text = (TextView)findViewById(R.id.name);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        test =(ImageView)findViewById(R.id.image_test);
        user_id = firebaseUser.getUid();
//------------------------------------------------------------------

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/Users/"+user_id+"/");




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        navigation  = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void showdata(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds :dataSnapshot.getChildren()){
            User user_new = new User();
            user_new.setFname(ds.child(user_id).getValue(User.class).getFname());
            user_new.setLname(ds.child(user_id).getValue(User.class).getLname());
            user_new.setCity(ds.child(user_id).getValue(User.class).getCity());
            user_new.setFname(ds.child(user_id).getValue(User.class).getFname());
            user_new.setEmail(ds.child(user_id).getValue(User.class).getEmail());
            user_new.setPhoneNumber(ds.child(user_id).getValue(User.class).getPhoneNumber());
            user_new.setId(user_id);
          //  String p = (ds.child(user_id).child("Products").getValue(Product.class).getPath());




            name_text.setText(user_new.getFname());
        }//for part


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


    public void Add(View view) {
        Intent intent2 = new Intent(getApplicationContext(),addProduct.class);
        intent2.putExtra("User_Id",user_id);
        startActivity(intent2);
    }

    public void AddProduct(View view) {
        Intent intent2 = new Intent(getApplicationContext(),addProduct.class);
        intent2.putExtra("User_Id",user_id);
        startActivity(intent2);
    }
}
