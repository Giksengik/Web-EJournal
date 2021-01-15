package ru.vlasov.EJournal.models;

public class Participant extends Person {
    public int cardID;
    public Participant(String fullName, String phone, int cardID) {
        super(fullName, phone);
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }
    public Participant(){

    }
}
