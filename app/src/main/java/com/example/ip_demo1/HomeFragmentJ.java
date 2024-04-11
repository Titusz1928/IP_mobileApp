package com.example.ip_demo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragmentJ extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<DataClass> dataList;
    private ArrayList<String> titleList;
    private ArrayList<String> dateList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //doctor info
        ImageView expandDoctorButton = view.findViewById(R.id.HOMDoctorInfoButtonImage);
        TableRow doctorInfoRow1a = view.findViewById(R.id.doctor_info_row1a);
        TableRow doctorInfoRow1b = view.findViewById(R.id.doctor_info_row1b);
        TableRow doctorInfoRow2a = view.findViewById(R.id.doctor_info_row2a);
        TableRow doctorInfoRow2b = view.findViewById(R.id.doctor_info_row2b);
        TableRow doctorInfoRow3a = view.findViewById(R.id.doctor_info_row3a);
        TableRow doctorInfoRow3b = view.findViewById(R.id.doctor_info_row3b);

        //recoms
        CardView buttonRecom1 = view.findViewById(R.id.llRecom1CardView);

        //card menus
        CardView to_fisa_medicala = view.findViewById(R.id.llFisaMedicalaCardView);
        CardView to_alarm = view.findViewById(R.id.llAlarmeCardView);

        expandDoctorButton.setOnClickListener(v -> {
            if (doctorInfoRow1a.getVisibility() == View.GONE) {
                doctorInfoRow1a.setVisibility(View.VISIBLE);
                doctorInfoRow1b.setVisibility(View.VISIBLE);
                doctorInfoRow2a.setVisibility(View.VISIBLE);
                doctorInfoRow2b.setVisibility(View.VISIBLE);
                doctorInfoRow3a.setVisibility(View.VISIBLE);
                doctorInfoRow3b.setVisibility(View.VISIBLE);
                expandDoctorButton.setImageResource(R.drawable.minimize);
            } else {
                doctorInfoRow1a.setVisibility(View.GONE);
                doctorInfoRow1b.setVisibility(View.GONE);
                doctorInfoRow2a.setVisibility(View.GONE);
                doctorInfoRow2b.setVisibility(View.GONE);
                doctorInfoRow3a.setVisibility(View.GONE);
                doctorInfoRow3b.setVisibility(View.GONE);
                expandDoctorButton.setImageResource(R.drawable.expand);
            }
        });

        buttonRecom1.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), RecommendationActivityJ.class);
            startActivity(intent);
        });

        to_fisa_medicala.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), FisaMedicalaWebActivityJ.class);
            startActivity(intent);
        });

        to_alarm.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AlarmsActivityJ.class);
            startActivity(intent);
        });

        return view;
    }

    private void getData() {
        for (int i = 0; i < titleList.size(); i++) {
            DataClass dataClass = new DataClass(titleList.get(i), dateList.get(i));
            dataList.add(dataClass);
        }
        recyclerView.setAdapter(new AdapterClass(dataList));
    }
}
