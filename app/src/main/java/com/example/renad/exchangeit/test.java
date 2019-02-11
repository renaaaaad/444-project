package com.example.renad.exchangeit;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class test extends AppCompatActivity {
    private String path;
    private Uri uri2 ;
    private static final  int RESULT_LOAD_IMAGE=1;
    private static Button image_insert ;
    private Button cancel;
    private Button add ;
    ImageView itemimage;
    TextView image_error;
    TextView productname ,productdiscription;
    Spinner category;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private StorageReference mStorageRef;
    private Uri  selectedimage;
    private EditText dis ;
    String user_id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Users")
                    .child(firebaseUser.getUid());
            user_id  = firebaseUser.getUid();
        }



        image_insert = (Button)findViewById(R.id.explor_phone) ;
        cancel = (Button) findViewById(R.id.cancel_home);
        itemimage =(ImageView) findViewById(R.id.itemimage);
        add=(Button)findViewById(R.id.add_button);
        productname=findViewById(R.id.product_name);
        productdiscription=findViewById(R.id.Discription);
        category=findViewById(R.id.category);
        mStorageRef= FirebaseStorage.getInstance().getReference();
        dis = (EditText)findViewById(R.id.editText6);







        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity_profilePage.class));
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(productname.getText().toString().equals("")){
                    productname.setError(" Please Enter The Name of the Product ");
                    productname.findFocus();
                    return;
                }
                String p_name =  productname.getText().toString();
                String p_des =  productdiscription.getText().toString();
                String p_cat =  category.getSelectedItem().toString();
                if(selectedimage!=null){


                    final StorageReference storageReference = mStorageRef.child(user_id+"/"+p_name+".jpg");

                    storageReference.putFile(selectedimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    });











                }//if
                else {
                    image_error.setError(" Please Choose Image  ");
                    image_error.findFocus();
                    return;
                }


                if(productdiscription.getText().toString().equals("")){
                    productdiscription.setError(" Please Enter Description of the Product ");
                    productdiscription.findFocus();
                    return;
                }
                String p_dess =  dis.getText().toString();

                Product product= new Product(p_name,p_dess,p_cat,path);

                // to stor the dproduct to the user and the alon (table)

                firebaseDatabase.child("Products").child(p_name).setValue(path).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity( new Intent(getApplicationContext(),MainActivity_profilePage.class));
                        }
                    }
                });





            }//on click
        });



        image_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
            }
        });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data != null ){
            selectedimage=data.getData();
            itemimage.setImageURI(selectedimage);


        }






    }

}


/*
package com.example.renad.exchangeit;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private TextView name_text , city2;
    private String name_from_firebase;
    private String user_id;
    private String user_name;
    Button cancel ;
    //------------------------------------------------ the data base varible
  private FirebaseAuth firebaseAuth;
  private FirebaseDatabase firebaseDatabase;
  private FirebaseAuth.AuthStateListener authStateListener;
  private DatabaseReference databaseReference;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile_page);
        name_text = (TextView)findViewById(R.id.name);
city2 =        (TextView)findViewById(R.id.city);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String userid=user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                   @Override
                                                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                       name_text.setText(dataSnapshot.child("fname").getValue().toString());
                                                                       city2.setText(dataSnapshot.child("city").getValue().toString());
                                                                   }

                                                                   @Override
                                                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                   }
                                                               });
//String nname = databaseReference.child("Users").child(user_id).child("fname").toString();


                navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
cancel  = (Button)findViewById(R.id.button6);
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        firebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),loginPage.class));
    }
});
    }//oncreat

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
    }
}

 */
/*                        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Users");
        reference2.child(userid).child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    GridView gridView = (GridView)findViewById(R.id.grid);
                    for(DataSnapshot ds :dataSnapshot.getChildren()) {
                        Product product = new Product();
                        product.setPath(ds.getValue(Product.class).getPath());
                        product.setCategory(ds.getValue(Product.class).getCategory());
                        product.setName(ds.getValue(Product.class).getName());
                        product.setDiscription(ds.getValue(Product.class).getDiscription());

                    }//for
                    }//if
                    else return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
