package ru.vlasov.EJournal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlasov.EJournal.dao.PeopleDAO;
import ru.vlasov.EJournal.models.Participant;

@Controller
@RequestMapping("/EJournal")
public class PeopleController {
    public PeopleDAO peopleDAO;
    @Autowired
    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }
    @GetMapping()
    public String showMenu(){
        return "EJournal/menu";
    }
    @GetMapping("/people")
    public String peopleIndex(Model model){
        model.addAttribute("people",peopleDAO.index());
        peopleDAO.setPeople(peopleDAO.index());
        return("EJournal/people");
    }
    @GetMapping("/people/new")
    public String newPerson(){
        return "EJournal/new";
    }
    @GetMapping("/people/{id}")
    public String show(@PathVariable("id")int id,Model model){
        if(peopleDAO.getPeople()==null){
            peopleDAO.setPeople(peopleDAO.index());
        }
        for(Participant item : peopleDAO.getPeople()){
            if(item.getCardID()==id){
                switch(item.getStatus()){
                    case "EMPLOYEE":
                        model.addAttribute(peopleDAO.showEmployee(id));
                        return"EJournal/show_employee";
                    case"TEACHER":
                        model.addAttribute(peopleDAO.showTeacher(id));
                        return "EJournal/show_teacher";
                    case "LEARNER":
                        model.addAttribute(peopleDAO.showLearner(id));
                        return "EJournal/show_learner";
                }
                break;
            }
        }
        return null;
    }


}
