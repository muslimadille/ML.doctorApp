package com.medicalLink.muslim.mldoctorapp;



public class appointment_adapter {

    private String patient_name;
    private String patient_address;
    private String patient_id;
    private String patient_time;
    private String patient_phone;



    public  appointment_adapter() {


    }


    public appointment_adapter(String patient_name, String patient_address, String patient_id, String patient_time,String patient_phone) {
        this.patient_name = patient_name;
        this.patient_address = patient_address;
        this.patient_id = patient_id;
        this.patient_time = patient_time;
        this.patient_time=patient_time;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_time() {
        return patient_time;
    }

    public void setPatient_time(String patient_time) {
        this.patient_time = patient_time;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }
}
