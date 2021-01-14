package ru.vlasov.EJournal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
