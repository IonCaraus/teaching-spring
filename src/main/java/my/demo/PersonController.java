package my.demo;

import my.demo.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ModelAndView getPersons() {
        List<Person> persons = personRepository.getAll();
        return new ModelAndView("personList", Map.of("persons", persons));
    }

    @GetMapping("/add")
    public String showAddPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person) {
        personRepository.add(person);
        return "redirect:/persons";
    }
}
