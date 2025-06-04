package com.example.mycampusassistant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class ClassScheduleActivity extends AppCompatActivity {

    private LinearLayout scheduleList;
    private EditText inputSchedule;
    private ArrayList<String> schedules;
    private SharedPreferences preferences;

    private static final String PREF_NAME = "class_schedule_pref";
    private static final String SCHEDULE_KEY = "schedule_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        scheduleList = findViewById(R.id.scheduleList);
        inputSchedule = findViewById(R.id.inputSchedule);
        Button btnAddSchedule = findViewById(R.id.btnAddSchedule);
        Button btnBack = findViewById(R.id.btnBack);

        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        schedules = loadSchedules();

        for (String item : schedules) {
            addScheduleView(item);
        }

        btnAddSchedule.setOnClickListener(v -> {
            String text = inputSchedule.getText().toString().trim();
            if (!text.isEmpty()) {
                schedules.add(text);
                addScheduleView(text);
                saveSchedules();
                inputSchedule.setText("");
            } else {
                Toast.makeText(this, "Please enter a schedule", Toast.LENGTH_SHORT).show();
            }
        });

        // Back button finishes this activity, returning to previous screen
        btnBack.setOnClickListener(v -> finish());
    }

    private void addScheduleView(String scheduleText) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setPadding(8, 8, 8, 8);
        itemLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView = new TextView(this);
        textView.setText(scheduleText);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        textView.setTextSize(16);

        // Edit button
        ImageButton editBtn = new ImageButton(this);
        editBtn.setImageResource(android.R.drawable.ic_menu_edit);
        editBtn.setBackgroundColor(0x00000000); // Transparent
        editBtn.setOnClickListener(v -> showEditDialog(textView));

        // Delete (bin) button
        ImageButton deleteBtn = new ImageButton(this);
        deleteBtn.setImageResource(android.R.drawable.ic_menu_delete);
        deleteBtn.setBackgroundColor(0x00000000); // Transparent
        deleteBtn.setOnClickListener(v -> {
            scheduleList.removeView(itemLayout);
            schedules.remove(textView.getText().toString());
            saveSchedules();
        });

        itemLayout.addView(textView);
        itemLayout.addView(editBtn);
        itemLayout.addView(deleteBtn);

        scheduleList.addView(itemLayout);
    }

    private void showEditDialog(TextView textView) {
        EditText editText = new EditText(this);
        editText.setText(textView.getText().toString());

        new android.app.AlertDialog.Builder(this)
                .setTitle("Edit Schedule")
                .setView(editText)
                .setPositiveButton("Save", (dialog, which) -> {
                    String oldText = textView.getText().toString();
                    String newText = editText.getText().toString().trim();
                    if (!newText.isEmpty()) {
                        schedules.set(schedules.indexOf(oldText), newText);
                        textView.setText(newText);
                        saveSchedules();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void saveSchedules() {
        SharedPreferences.Editor editor = preferences.edit();
        JSONArray jsonArray = new JSONArray(schedules);
        editor.putString(SCHEDULE_KEY, jsonArray.toString());
        editor.apply();
    }

    private ArrayList<String> loadSchedules() {
        String json = preferences.getString(SCHEDULE_KEY, null);
        ArrayList<String> list = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
