package com.a13118059.DataModel;

public class Item_Database {
    //Declare variables for the array
    int id;
    String name, phone;
    boolean checked;
    //Create the method for adding data to  array
    public Item_Database(int id, String name, String phone, boolean checked){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.checked = checked;
    }

    //Get and set methods for the variables:

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}