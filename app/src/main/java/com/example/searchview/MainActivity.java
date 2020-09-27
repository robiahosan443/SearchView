package com.example.searchview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameet, ageet, rollet;
    Button save;

    List<SavetoModel> userinfo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameet = findViewById(R.id.nameid);
        ageet = findViewById(R.id.ageid);
        rollet = findViewById(R.id.rollid);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("userinfo");

        userinfo = new ArrayList<>();
        save = findViewById(R.id.saveid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);


                save();
            }
        });


    }

    private void save() {

        String Name = nameet.getText().toString();
        String Age = ageet.getText().toString();
        String Roll = rollet.getText().toString();

        int roll = Integer.parseInt(Roll);

        // String Roll=String.valueOf(roll.getText());
        //String Roll = String.valueOf(Integer.parseInt(roll.getText().toString()));

        SavetoModel savetoModel = new SavetoModel(Name, Age, roll);
        //SavetoModel savetoModel = new SavetoModel(Name,Age,Integer.valueOf(Roll));

        String key= databaseReference.push().getKey();

        databaseReference.child(key).setValue(savetoModel);
    }
}