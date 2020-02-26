package com.medicalLink.muslim.mldoctorapp;



public class accepted_adapter {

    private String patient_name;
    private String patient_address;
    private String patient_id;
    private String meeting_time;
    private String patient_number;
    private String patient_phone;



    public  accepted_adapter() {


    }

    public accepted_adapter(String patient_name, String patient_address, String patient_id, String meeting_time, String patient_number,String patient_phone) {
        this.patient_name = patient_name;
        this.patient_address = patient_address;
        this.patient_id = patient_id;
        this.meeting_time = meeting_time;
        this.patient_number = patient_number;
        this.patient_phone=patient_phone;
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

    public String getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(String meeting_time) {
        this.meeting_time = meeting_time;
    }

    public String getPatient_number() {
        return patient_number;
    }

    public void setPatient_number(String patient_number) {
        this.patient_number = patient_number;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }
}
