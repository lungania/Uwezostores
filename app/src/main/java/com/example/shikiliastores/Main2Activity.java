package com.example.shikiliastores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<String> categoryList;
    private ArrayList<Facility> facilityList;
    SearchView searchView;


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem Item = menu.findItem(R.id.menusearch);
//        SearchView searchView = (SearchView) Item.getActionView();
//
//        //final ArrayAdapter adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, facilityList.get(4));
//
//
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String s) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String s) {
////                adapter.getFilter().filter(s);
////
////                return false;
////            }
////        });
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if (id==R.id.item2){
            startActivity(new Intent(Main2Activity.this,FacilityActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        searchView=findViewById(R.id.menusearch);




        List<Facility> facility1=new ArrayList<>();
//
        final RecyclerView myrv=(RecyclerView)findViewById(R.id.recyclerview_id);

        myrv.setLayoutManager(new GridLayoutManager(this,2));


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();



        //read from database
        myRef.child("Facility").limitToLast(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                facilityList=new ArrayList<>();


                for (DataSnapshot child :dataSnapshot.getChildren()){
                    final Facility facility=child.getValue(Facility.class);

                    facilityList.add(facility);

                }
                final RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(facilityList,Main2Activity.this);
                myrv.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    }

