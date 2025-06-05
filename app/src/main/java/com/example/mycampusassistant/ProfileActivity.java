package com.example.mycampusassistant;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    EditText edtName, edtID, edtCourse;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtName = findViewById(R.id.edtName);
        edtID = findViewById(R.id.edtID);
        edtCourse = findViewById(R.id.edtCourse);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String id = edtID.getText().toString();
            String course = edtCourse.getText().toString();

            Toast.makeText(this, "Profile Saved!", Toast.LENGTH_SHORT).show();
            // You could also save this in SharedPreferences
        });
    }
}
