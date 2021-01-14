package ru.vlasov.EJournal.models;

public class Learner extends Participant {
    public Parent [] parents;
    public Learner(String fullName, int phone, int cardID, Parent[] parents) {
        super(fullName, phone, cardID);
        this.parents = parents;
    }
    public static String status="LEARNER";
}
