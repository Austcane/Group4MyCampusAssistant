package com.example.mycampusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSchedule, btnAssignments, btnMap, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSchedule = findViewById(R.id.btnSchedule);
        btnAssignments = findViewById(R.id.btnAssignments);
        btnMap = findViewById(R.id.btnMap);
        btnProfile = findViewById(R.id.btnProfile);

        btnSchedule.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ClassScheduleActivity.class)));

        btnAssignments.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AssignmentsExamsActivity.class)));

        btnMap.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CampusMapActivity.class)));

        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }
}
