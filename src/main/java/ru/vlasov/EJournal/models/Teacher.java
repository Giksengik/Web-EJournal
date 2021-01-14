package ru.vlasov.EJournal.models;

public class Teacher extends Participant {
    public String position;
    public String [] qualifications;
    public Teacher(String fullName, int phone, int cardID, String position, String[] qualifications) {
        super(fullName, phone, cardID);
        this.position = position;
        this.qualifications = qualifications;
    }
    public static String status="PARENT";
}
