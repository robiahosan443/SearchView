package com.example.searchview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<SavetoModel> userinfo;
    List<SavetoModel> filteruserinfo;
    MyAdapter adapter;
    Toolbar toolbar;
    SearchView searchView;

    private RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = findViewById(R.id.recyclerViewId);

        userinfo = new ArrayList<>();
        filteruserinfo = new ArrayList<>();

        toolbar = findViewById(R.id.mytoolbarid);
        searchView = findViewById(R.id.searchId);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("userinfo");


        adapter = new MyAdapter(userinfo, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        retriveData();

        if (filteruserinfo != null) {
            search(filteruserinfo);
        }
    }

    private void search(final List<SavetoModel> filteruserinfo1) {
        new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<SavetoModel> filter = new ArrayList<>();
                if (query.isEmpty()) {
                    filter = filteruserinfo;
                } else {
                    for (SavetoModel item : filteruserinfo1) {
                        if (item.getSavename().toLowerCase().contains(query.toLowerCase())) {
                            filter = filteruserinfo;
                        }


                    }
                }
                filter.clear();
                filteruserinfo.addAll(filter);
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<SavetoModel> infofilter = new ArrayList<>();
                if (newText.isEmpty() || newText.equals(" ")) {
                    infofilter = filteruserinfo;
                } else {
                    for (SavetoModel iteminfo : filteruserinfo1) {
                        if (iteminfo.getSavename().toLowerCase().contains(newText.toLowerCase())) {
                            infofilter = filteruserinfo;
                        }
                    }
                }
                infofilter.clear();
                filteruserinfo.addAll(infofilter);
                adapter.notifyDataSetChanged();
                return true;
            }
        };
    }


    private void retriveData() {

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = "";
                String age = "";
                int roll = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    name = dataSnapshot.child("savename").getValue().toString();
                    age = dataSnapshot.child("saveage").getValue().toString();
                    roll = Integer.parseInt(dataSnapshot.child("saveroll").getValue().toString());


                    SavetoModel model = new SavetoModel();


                    model.setSavename(name);
                    model.setSaveage(age);
                    model.setSaveroll(roll);

                    userinfo.add(model);
                    filteruserinfo.add(model);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}