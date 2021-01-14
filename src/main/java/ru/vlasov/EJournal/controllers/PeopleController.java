package ru.vlasov.EJournal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlasov.EJournal.dao.PeopleDAO;
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
        return("EJournal/people");
    }
    @GetMapping("/people/new")
    public String newPerson(){
        return "EJournal/new";
    }



}
