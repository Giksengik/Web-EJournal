package ru.vlasov.EJournal.models;

public class Teacher extends Participant {
    public String position;
    public String qualifications;
    public Teacher(String fullName, String phone, int cardID, String position, String qualifications) {
        super(fullName, phone, cardID);
        this.position = position;
        this.qualifications = qualifications;
    }
    public static String status="TEACHER";

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Teacher.status = status;
    }
    public Teacher(){

    }
}
