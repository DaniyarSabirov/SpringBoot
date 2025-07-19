package Firstprojekt.Firstprojekt.controller;


import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        return ResponseEntity.ok(personService.getPeople());
    }


    @PostMapping("/add")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        boolean exist = personService.addPerson(person);
        if(exist) return ResponseEntity.status(409).body("Person with this ID is already exists");

        return ResponseEntity.status(201).body("Person successfully added");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody Person newPerson){

       boolean updated = personService.updatePerson(newPerson, id);
       if(updated) return ResponseEntity.status(201).body("Person successfully updated");
       return ResponseEntity.status(404).body("Update failed. No such ID");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        boolean removed = personService.deletePerson(id);
        if (removed) {
            return ResponseEntity.ok("Person deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Person not found.");
        }
    }
}



