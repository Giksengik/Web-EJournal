package ru.vlasov.EJournal.models;

public class Participant extends Person {
    public int cardID;
    public String status;
    public Participant(String fullName, String phone, int cardID,String status) {
        super(fullName, phone);
        this.cardID = cardID;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
