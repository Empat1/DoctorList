package com.example.doctorlist.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorlist.Client.Client;
import com.example.doctorlist.Client.Doctor;
import com.example.doctorlist.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoFragment extends Fragment {

    private TextView name,qualification,description , services;
    private CircleImageView profileImage;
    private Client client = Client.getClient();
    private int doctorId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        doctorId = getArguments().getInt("DoctorId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_info, container, false);

        name = root.findViewById(R.id.nameView);
        qualification = root.findViewById(R.id.qualification);
        description = root.findViewById(R.id.description);
        services = root.findViewById(R.id.services);
        profileImage = root.findViewById(R.id.profile_image);

        setDataInView();

        return root;
    }

    private void setDataInView(){
        Doctor doctor = client.getDoctorsSelect().get(doctorId);

        name.setText(doctor.getName());
        qualification.setText(doctor.getQualification());

        description.setText(duplicatedString(doctor.getDesc()));
        services.setText(duplicatedString(doctor.getServices()));

        if(!doctor.getPhoto().equals("-1"))
            profileImage.setImageBitmap(decodeBase64(doctor.getPhoto()));

    }

    private String duplicatedString(String s){
        String out ="";
        for(char c : s.toCharArray()){
            if(c == '\n') out += '\n';
            out+=c;
        }
        return out;
    }

    private Bitmap decodeBase64(String photo){
        byte[] decodedString = Base64.decode(photo , Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0 , decodedString.length);
    }
}