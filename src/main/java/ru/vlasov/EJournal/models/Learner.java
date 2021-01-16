package ru.vlasov.EJournal.models;

public class Learner extends Participant {
    public Parent [] parents;
    public Learner(String fullName, String phone, int cardID, Parent[] parents,String status) {
        super(fullName, phone, cardID,status);
        this.parents = parents;
    }

    public Parent[] getParents() {
        return parents;
    }

    public void setParents(Parent[] parents) {
        this.parents = parents;
    }
    public Learner(){

    }
}
