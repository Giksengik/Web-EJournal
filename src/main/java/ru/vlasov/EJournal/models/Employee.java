package ru.vlasov.EJournal.models;

public class Employee extends Participant {
    public String position;
    public Employee(String fullName, String phone, int cardID,String position) {
        super(fullName, phone, cardID);
        this.position=position;
    }
    public static String status="EMPLOYEE";
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public Employee() {
    }
}
