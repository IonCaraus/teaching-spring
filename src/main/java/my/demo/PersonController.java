package my.demo;

import my.demo.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;
    private final MyProperties myProperties;

    public PersonController(PersonRepository personRepository, MyProperties myProperties) {
        this.personRepository = personRepository;
        this.myProperties = myProperties;
        System.out.println("Loaded properties: " + myProperties.getProp1());
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

    @GetMapping("/{personNumber}")
    public String viewPersonDetails(@PathVariable("personNumber") String personNumber, Model model) {
        // In a real application, you'd retrieve the person from the database
        Person person = personRepository.getByPersonNumber(personNumber);
        if (person != null) {
            model.addAttribute("person", person);
            return "personDetails";
        }
        // If person not found, redirect to the list or show an error message
        return "redirect:/persons";
    }

    @GetMapping("/search")
    public String searchPersonsByEmail(@RequestParam("email") String email, Model model) {
        List<Person> searchResults = personRepository.getByEmail(email);

        model.addAttribute("persons", searchResults);
        return "personList";
    }
}
