package com.example.ngochieu.pctsv.Model;

/**
 * Created by NgocHieu on 5/16/2017.
 */

public class Form {
    private int idForm;
    private int idAccount;
    private String formName;
    private String receivingTime;
    private String timeOfRegistration;

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(String receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(String timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }

    @Override
    public String toString() {
        return "Form{" +
                "idForm=" + idForm +
                ", idAccount=" + idAccount +
                ", formName='" + formName + '\'' +
                ", receivingTime='" + receivingTime + '\'' +
                ", timeOfRegistration='" + timeOfRegistration + '\'' +
                '}';
    }
}
