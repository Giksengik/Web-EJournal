package ru.vlasov.EJournal.models;

public class Learner extends Participant {
    public Parent [] parents;
    public Learner(String fullName, String phone, int cardID,String status
    ,String firstParentName,String firstParentPhone,String secondParentName,String secondParentPhone) {
        super(fullName, phone, cardID,status);
        this.parents= new Parent[]{new Parent(firstParentName, firstParentPhone),
                new Parent(secondParentName, secondParentPhone)};
    }
    public Parent[] getParents() {
        return parents;
    }
    public void setParents(Parent[] parents) {
        this.parents = parents;
    }
    public Learner(){
    }
    public String firstParentName;
    public String firstParentPhone;
    public String secondParentName;
    public String secondParentPhone;
    public String getFirstParentName() {
        return firstParentName;
    }

    public void setFirstParentName(String firstParentName) {
        this.firstParentName = firstParentName;
    }

    public String getFirstParentPhone() {
        return firstParentPhone;
    }

    public void setFirstParentPhone(String firstParentPhone) {
        this.firstParentPhone = firstParentPhone;
    }

    public String getSecondParentName() {
        return secondParentName;
    }

    public void setSecondParentName(String secondParentName) {
        this.secondParentName = secondParentName;
    }

    public String getSecondParentPhone() {
        return secondParentPhone;
    }

    public void setSecondParentPhone(String secondParentPhone) {
        this.secondParentPhone = secondParentPhone;
    }
}
