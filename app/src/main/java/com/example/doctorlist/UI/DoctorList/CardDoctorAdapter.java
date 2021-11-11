package com.example.doctorlist.UI.DoctorList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorlist.Client.Doctor;
import com.example.doctorlist.R;
import com.example.doctorlist.UI.InfoFragment;
import com.example.doctorlist.UI.MainActivity;

import java.util.List;

public class CardDoctorAdapter extends RecyclerView.Adapter<CardDoctorAdapter.CardDoctorHolder> {

    public class CardDoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        ImageView photoDoctor;
        TextView title, specializationTextView, qualificationTextView;
        Button appointment, info;
        int nDoctor;

        public CardDoctorHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.doctorCardView);
            photoDoctor = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.titleTextView);
            specializationTextView = itemView.findViewById(R.id.specializationView);
            qualificationTextView = itemView.findViewById(R.id.qualification);
            appointment = itemView.findViewById(R.id.appointmentButton);
            info = itemView.findViewById(R.id.infoButton);

            info.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.infoButton:
                    startInfoFragment();
            }
        }

        private void startInfoFragment(){
            Fragment fragment = new InfoFragment();
            FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putInt("DoctorId" , nDoctor);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,  fragment).addToBackStack(null)
                    .commit();
        }
    }

    List<Doctor> doctorList;
    Context context;

    public CardDoctorAdapter(List<Doctor> doctorList , Context context){
       this.doctorList = doctorList;
       this.context = context;
    }

    @NonNull
    @Override
    public CardDoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card,parent,false);
        return new CardDoctorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDoctorHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nDoctor = position;

        Doctor doctor = doctorList.get(position);

        holder.title.setText(doctor.getName());
        holder.specializationTextView.setText(doctor.getSpecialization());
        holder.qualificationTextView.setText(doctor.getQualification());

        if(doctor.getPhoto().equals("-1")) {
            holder.photoDoctor.setImageResource(R.mipmap.defult);
        }else{
            holder.photoDoctor.setImageBitmap(decodeBase64(doctor.getPhoto()));
        }
    }

    private Bitmap decodeBase64(String photo){
        byte[] decodedString = Base64.decode(photo , Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0 , decodedString.length);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

}
