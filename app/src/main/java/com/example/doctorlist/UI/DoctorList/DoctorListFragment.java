package com.example.doctorlist.UI.DoctorList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.doctorlist.Client.Client;
import com.example.doctorlist.Client.Doctor;
import com.example.doctorlist.Client.MainFragment;
import com.example.doctorlist.R;
import com.example.doctorlist.UI.InfoFragment;
import com.example.doctorlist.UI.MainActivity;

import java.util.List;
import java.util.TimerTask;

public class DoctorListFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private Client client;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doctor__list_, container, false);

        spinner = root.findViewById(R.id.spinner);
        recyclerView = root.findViewById(R.id.recylerView);
        button = root.findViewById(R.id.inMain);

        spinner.setOnItemSelectedListener(this);
        button.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);


        client = Client.getClient();
        if(!recovery()) {
            requestSpecializations();
        }
        return root;
    }

    private boolean recovery(){
        if (client.getDoctorsSelect() != null){
            initializeAdapter(client.getDoctorsSelect());

            setDateForSpinner();
            return true;
        }
        return false;
    }

    private void initializeAdapter(List<Doctor> list) {
        CardDoctorAdapter cardDoctorAdapter = new CardDoctorAdapter(list , getContext());
        recyclerView.setAdapter(cardDoctorAdapter);
    }

    private void requestSpecializations(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncDownloadDateSpecialization();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDateForSpinner();
                    }
                });
            }
        }).start();
    }

    private void syncDownloadDateSpecialization(){
        if(client.setSpecializationsRequest()) return;
        syncDownloadDateSpecialization();
    }

    private void setDateForSpinner(){
        ArrayAdapter<String> adapter  =  new  ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                client.specializationsName(client.getSpecializations()));
        spinner.setAdapter(adapter);
    }

    private void requestDoctor(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncDownloadDateDoctor();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDateForAdapter(id);
                    }
                });
            }
        }).start();
    }

    private void setDateForAdapter(int id){
        client.setSelectionDoctor(client.getDoctors() , id);
        initializeAdapter(client.getDoctorsSelect());
    }

    private void syncDownloadDateDoctor(){
        if(client.setDoctorsRequest()) return;
        syncDownloadDateDoctor();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(client.getSpecializations() != null)
        requestDoctor(client.getSpecializations()[position].getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = new MainFragment();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,  fragment).addToBackStack(null)
                .commit();
    }
}