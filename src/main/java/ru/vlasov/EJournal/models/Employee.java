package ru.vlasov.EJournal.models;

public class Employee extends Participant {
    public String position;
    public Employee(String fullName, String phone, int cardID,String position,String status) {
        super(fullName, phone, cardID,status);
        this.position=position;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public Employee() {
    }
}
