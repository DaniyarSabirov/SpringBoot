package Firstprojekt.Firstprojekt.controller;

import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) { this.personService = personService; }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople() {
        return ResponseEntity.ok(personService.getPeople());
    }

    @PostMapping("/add")
    public ResponseEntity<PersonResponse> addPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.status(201).body(personService.addPerson(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long id,
                                                       @RequestBody @Valid Person newPerson){
        return ResponseEntity.ok(personService.updatePerson(newPerson, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        boolean removed = personService.deletePerson(id);
        return removed ? ResponseEntity.ok("Person deleted successfully.")
                : ResponseEntity.status(404).body("Person not found.");
    }
}
