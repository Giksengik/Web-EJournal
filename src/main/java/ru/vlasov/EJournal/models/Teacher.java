package ru.vlasov.EJournal.models;

public class Teacher extends Participant {
    public String position;
    public String qualifications;
    public Teacher(String fullName, String phone, int cardID, String position, String qualifications,String status) {
        super(fullName, phone, cardID,status);
        this.position = position;
        this.qualifications = qualifications;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
    public Teacher(){}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
