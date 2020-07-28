package com.hbyadav.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import com.android.tourguide.R;
import com.android.tourguide.R.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.android.tourguide.R;
public class Make_schedule extends AppCompatActivity {
    Spinner classSelect, daySelect;
    ArrayAdapter adapterSpinner, days;
    String courses[] = {"ANCH", "BIOL", "COMP-SCI", "ENGL", "HIST", "MATH", "PHYS"};
    StudentbaseAdapter studentAdapter;
    Student_display student_display = new Student_display();
    public String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_schedule);
        studentAdapter = new StudentbaseAdapter(this);
        studentAdapter = studentAdapter.open();
        username = student_display.Username();
        classSelect = (Spinner) findViewById(R.id.classSelector);
        daySelect = (Spinner) findViewById(R.id.daySelector);
        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        assert classSelect != null;
        classSelect.setAdapter(adapterSpinner);
        ArrayList<String> weekdays = new ArrayList<>();
        weekdays.add("MONDAY");
        weekdays.add("TUESDAY");
        weekdays.add("WEDNESDAY");
        weekdays.add("THURSDAY");
        weekdays.add("FRIDAY");
        weekdays.add("SATURDAY");
        weekdays.add("SUNDAY");
        days = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weekdays);
        assert classSelect != null;
        daySelect.setAdapter(days);
        Button btn = (Button) findViewById(R.id.saveBUTTON_SCHEDULE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSchedule(v);
            }
        });
    }

    private void saveSchedule(View v) {
        // get selected/entered fields
        String daySelected = daySelect.getSelectedItem().toString();
        String classSelected = classSelect.getSelectedItem().toString();
        EditText editText = (EditText) findViewById(R.id.subjectName);
        String className = editText.getText().toString();
        // get time value
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm");
                                             // TODO has issues with afternoon time formatting
        String Hour = String.valueOf(hour);
        String Min = String.valueOf(min);
        String time = Hour + ":" + Min;
        studentAdapter.Schedule(classSelected, className, time, daySelected, username);
        Toast.makeText(getBaseContext(), "Schedule Saved", Toast.LENGTH_SHORT).show();
    }
}
