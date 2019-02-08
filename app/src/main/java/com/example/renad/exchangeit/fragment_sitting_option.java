package com.example.renad.exchangeit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class fragment_sitting_option extends Fragment {
private Button button_edit ;
    private FragmentManager supportFragmentManager;
    private FirebaseAuth firebaseAuth;
    private Button button_logout ;
private  Button button_resetPass;
    public fragment_sitting_option() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_sitting_option, container, false);
        Button button = (Button) view.findViewById(R.id.editButton);
        button_logout =(Button)view.findViewById(R.id.logout_button) ;
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              startActivity(new Intent(getContext(),sitting.class));
            }
        });
button_logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        firebaseAuth.getInstance().signOut();
        startActivity(new Intent(getContext(),loginPage.class));
    }
});

button_resetPass =(Button)view.findViewById(R.id.rest_password);
button_resetPass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});






        return view;
    }



    private void loadFragment(fragment_sitting fragment_sitting) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment_sitting);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    public FragmentManager getSupportFragmentManager() {
        return supportFragmentManager;
    }
}



