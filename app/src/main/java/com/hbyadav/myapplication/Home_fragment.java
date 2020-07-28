package com.hbyadav.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.tourguide.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class Home_fragment extends Fragment {

    StudentbaseAdapter studentAdapter;
    Student_display student = new Student_display();
    public String username = "";
    public FloatingActionButton editProfile, logoutBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_student_home, container, false);

        studentAdapter = new StudentbaseAdapter(getActivity());
        studentAdapter = studentAdapter.open();
        //EditText editText=(EditText)container.findViewById(R.id.editname);
       // String username = editText.getText().toString();

//        editProfile = (FloatingActionButton) view.findViewById(R.id.editPBtn);
//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {            // button to allow user to edit profile
//                Intent intent = new Intent(getActivity(), Profile.class);
//                startActivity(intent);
//            }
//        });

        logoutBtn = (FloatingActionButton) view.findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {            // redirect user to login page on logout
                Intent intent = new Intent(getActivity(), Student.class);
                startActivity(intent);
            }
        });

        username = student.Username();
        if (username != null) {
            Cursor _cursor = studentAdapter.getSingleEntry(username);
            int id = _cursor.getInt(0);
            String name = _cursor.getString(2);
            String fees = _cursor.getString(3);
            String school = _cursor.getString(4);
            String yr = _cursor.getString(5);
            String phone = _cursor.getString(6);
            PhoneNumberUtils.formatNumber(phone);           // format as phone number

            TextView uTv = (TextView) view.findViewById(R.id.uname);
            uTv.setText("Username: " + username);
            TextView nTv = (TextView) view.findViewById(R.id.name);
            nTv.setText("Name: " + name);
            TextView sTv = (TextView) view.findViewById(R.id.school);
            sTv.setText("School: " + school);
            TextView yTv = (TextView) view.findViewById(R.id.year);
            yTv.setText("Graduation Year: " + yr);
            TextView pTv = (TextView) view.findViewById(R.id.Phone);
            pTv.setText("Phone Number: " + phone);
            TextView fTv = (TextView) view.findViewById(R.id.Fees);
            fTv.setText("Fees: $" + fees);
        }
        return view;
    }
}
