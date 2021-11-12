package com.example.doctorlist.Client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.doctorlist.R;

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        button = root.findViewById(R.id.doctorsButton);
        button.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.doctorsButton:
                goToDoctorListFragment();
        }
    }

    private void goToDoctorListFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.popBackStack();
    }
}