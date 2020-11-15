package com.example.mdtan.amaderuzp;


public class Complain {

  /*  userid=String.valueOf(id);

    String Topic=topic.getText().toString();
    String Details=details.getText().toString();
    String Name=name.getText().toString();
    String Address=address.getText().toString();
    String PAddress=paddress.getText().toString();
    String Email=email.getText().toString();
    String Phn_Number=numberET.getText().toString();*/

    private String  id,doptor,topic,details,name,address,paddress,email,phn_number;

    public Complain(){

    }

    public Complain(String id, String doptor, String topic, String details, String name, String address, String paddress, String email, String phn_number) {
        this.id = id;
        this.doptor = doptor;
        this.topic = topic;
        this.details = details;
        this.name = name;
        this.address = address;
        this.paddress = paddress;
        this.email = email;
        this.phn_number = phn_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoptor() {
        return doptor;
    }

    public void setDoptor(String doptor) {
        this.doptor = doptor;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhn_number() {
        return phn_number;
    }

    public void setPhn_number(String phn_number) {
        this.phn_number = phn_number;
    }
}
