package com.example.doctorlist.Client;

public class Specialization {

    private int $id;
    private int id;
    private String name;

    public Specialization() {
    }

    public int get$id() {
        return $id;
    }

    public void set$id(int $id) {
        this.$id=$id;
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

    @Override
    public String toString() {
        return name ;
    }
}
