package com.example.asuss.instaclonefirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {


    ListView listView;
    PostClass adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> userEmailFromDb;
    ArrayList<String> userCommentFromDb;
    ArrayList<String> userImageUrlFromDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = findViewById(R.id.listViewFeed);

        userEmailFromDb = new ArrayList<>();
        userCommentFromDb = new ArrayList<>();
        userImageUrlFromDb = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new PostClass(userEmailFromDb,userCommentFromDb,userImageUrlFromDb,this);

        listView.setAdapter(adapter);

        getDataFromDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.feed_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.gonderiEkle){

            Intent intent = new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(intent);

        }
        if(item.getItemId() == R.id.cikis){

        }
        return super.onOptionsItemSelected(item);
    }

    public void getDataFromDb(){

        DatabaseReference newReference = firebaseDatabase.getReference("Posts");

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              /*  System.out.println("FBV children :"+dataSnapshot.getChildren());
                System.out.println("FBV key :"+dataSnapshot.getKey());
                System.out.println("FBV value :"+dataSnapshot.getValue());
                System.out.println("FBV priority :"+dataSnapshot.getPriority());*/

              for(DataSnapshot ds :dataSnapshot.getChildren()){

                  //System.out.println("FBV ds value"+ds.getValue());

                  HashMap<String,String> hashMap = (HashMap<String, String>) ds.getValue();
                  System.out.println("FBV useremail :"+hashMap.get("useremail"));
                  userEmailFromDb.add(hashMap.get("useremail"));
                  userCommentFromDb.add(hashMap.get("comment"));

                  userImageUrlFromDb.add(hashMap.get("downloadurl"));
                  adapter.notifyDataSetChanged();

              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

               // Toast.makeText()

            }
        });


    }
}
