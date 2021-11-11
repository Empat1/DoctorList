package com.example.doctorlist.Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static final String services = "https://testapi.simplex48.ru/api/Web/allspec/5";
    public static final String doctorUrl = "https://testapi.simplex48.ru/api/Web/medicinfo/5/1/";
    private Gson gson;
    private static Client client;

    private Specialization[] specializations;
    private Doctor[] doctors;
    private List<Doctor> doctorsSelect;

    public Specialization[] getSpecializations() {
        return specializations;
    }

    public Doctor[] getDoctors() {
        return doctors;
    }

    public boolean setDoctorsRequest(){
        doctors = gson.fromJson(jsonRequest(doctorUrl), Doctor[].class);
        if(specializations == null) return false;
        return true;
    }

    public boolean setSpecializationsRequest(){

        specializations = gson.fromJson(jsonRequest(services) , Specialization[].class);
        if(specializations == null) return false;
        return true;
    }

    public List<Doctor> getDoctorsSelect() {
        return doctorsSelect;
    }


    public void setSelectionDoctor(Doctor[] doctors ,int criterion){
        ArrayList<Doctor> doctorsList = new ArrayList<>();

        if(doctors == null) return;

        for(Doctor doctor: doctors){
            for(int id: doctor.getDOCT_IDs()){
                if(id == criterion) doctorsList.add(doctor);
            }
        }
        doctorsSelect = doctorsList;
    }
    private Client(){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public static Client getClient(){
        if(client == null){
            client = new Client();
        }
        return client;
    }

    public String jsonRequest(String urlStr){
        URL url = JSONUtils.createUrl(urlStr);
        return JSONUtils.parseUrl(url);
    }

    public ArrayList<String> specializationsName(Specialization[] specializations){
        ArrayList<String> specStr = new ArrayList<>();

        for (Specialization specialization : specializations){
            if (specialization.getName() != null) {
                specStr.add(specialization.getName());
            }
        }
        return specStr;
    }



}
