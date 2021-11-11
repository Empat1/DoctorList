package com.example.doctorlist.Client;

import java.util.Arrays;

public class Doctor {
    int $id;
    int id;
    String name;
    String photo;
    String desc;
    String specialization;
    String qualification;
    String services;
    int DOCT_IDs[];

    public Doctor() {
    }

    public int get$id() {
        return $id;
    }

    public void set$id(int $id) {
        this.$id = $id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public int[] getDOCT_IDs() {
        return DOCT_IDs;
    }

    public void setDOCT_IDs(int[] DOCT_IDs) {
        this.DOCT_IDs = DOCT_IDs;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "$id=" + $id +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", desc=" + desc +
                ", specialization='" + specialization + '\'' +
                ", qualification='" + qualification + '\'' +
                ", services='" + services + '\'' +
                ", DOCT_IDs=" + Arrays.toString(DOCT_IDs) +
                '}';
    }
}

