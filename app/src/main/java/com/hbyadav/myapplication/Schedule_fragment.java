package com.hbyadav.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import com.android.tourguide.R;

import org.w3c.dom.Text;

public class Schedule_fragment extends Fragment {
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> subs;
    StudentbaseAdapter studentAdapter;
    Student_display student_display = new Student_display();
    public String username;
    public TextView prompt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_student_schedule,
                container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_sch);
        assert fab != null;
        FloatingActionButton refreshBtn = (FloatingActionButton) view.findViewById(R.id.refresh);
        prompt = (TextView) view.findViewById(R.id.prompt);
        listView = (ListView) view.findViewById(R.id.schedulerList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {            // button to allow user to add course to schedule
                Intent launchIntent = new Intent(getActivity(), Make_schedule.class);
                startActivity(launchIntent);
            }
        });
                                                    // button to refresh view after updating database (if needed)
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh(view);
            }
        });

        refresh(view);          // initialize with current schedule (if present)

        return view;
    }

    public void refresh(View view) {
        studentAdapter = new StudentbaseAdapter(getActivity());
        studentAdapter = studentAdapter.open();
        username = student_display.Username();

        subs = new ArrayList<>();           // array to store strings for schedule "subtitles"

        // query database based on username, return schedule info for each class
        Cursor cursor = studentAdapter.schedule(username);
        if (cursor == null || cursor.getCount() == 0) {
            prompt.setVisibility(View.VISIBLE);         // if there is no schedule, prompt to user to create one
            Toast.makeText(getActivity(), "No Schedules Available",
                    Toast.LENGTH_LONG).show();
        } else {
            prompt.setVisibility(View.INVISIBLE);       // set prompt to hidden if a schedule is available
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                subs.add("Course Name: " + cursor.getString(2) +
                        "\nSubject: " + cursor.getString(1) + "\non " +
                        cursor.getString(4) + " at " + cursor.getString(3));
                cursor.moveToNext();
            }
        }
        adapter = new ArrayAdapter(getActivity(),       // simple list adapter to display classes
                android.R.layout.simple_list_item_1, subs);
        listView.setAdapter(adapter);
    }
}