package ru.vlasov.EJournal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlasov.EJournal.dao.PeopleDAO;
import ru.vlasov.EJournal.models.Employee;
import ru.vlasov.EJournal.models.Learner;
import ru.vlasov.EJournal.models.Participant;
import ru.vlasov.EJournal.models.Teacher;

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
    @GetMapping("/people/new_learner")
    public String newLearner(@ModelAttribute("learner") Learner learner){
        return("/EJournal/new_learner");
    }
    @GetMapping("/people/new_employee")
    public String newEmployee(@ModelAttribute("employee")Employee employee){
        return("/EJournal/new_employee");
    }
    @GetMapping("/people/new_teacher")
    public String newTeacher(@ModelAttribute("teacher")Teacher teacher){
        return("/EJournal/new_teacher");
    }
    @RequestMapping(value = "/people", method = RequestMethod.POST,params="{}")
    public String createEmployee(@ModelAttribute("employee")Employee employee){
        peopleDAO.addNewEmployee(employee);
        return"redirect:/EJournal/people";
    }
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public String createLearner(@ModelAttribute("learner")Learner learner){
        peopleDAO.addNewLearner(learner);
        return"redirect:/EJournal/people";
    }



}
