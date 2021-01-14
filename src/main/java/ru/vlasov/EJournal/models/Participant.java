package ru.vlasov.EJournal.models;

public class Participant extends Person {
    public int cardID;
    public Participant(String fullName, int phone, int cardID) {
        super(fullName, phone);
        this.cardID = cardID;
    }
}
