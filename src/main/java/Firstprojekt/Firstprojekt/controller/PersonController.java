package Firstprojekt.Firstprojekt.controller;

import Firstprojekt.Firstprojekt.dto.PersonCreateRequest;
import Firstprojekt.Firstprojekt.dto.PersonPatchRequest;
import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) { this.personService = personService; }

    @GetMapping
    public ResponseEntity<PersonResponse> getPeople
            (@RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(personService.getPeople(page, size));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(@RequestParam String message){
        return ResponseEntity.ok(message);
    }

    @PostMapping("/add")
    public ResponseEntity<PersonResponse> addPerson(@RequestBody @Valid PersonCreateRequest personDto) {
        return ResponseEntity.status(201).body(personService.addPerson(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long id,
                                                       @RequestBody @Valid PersonPatchRequest personDto){
        return ResponseEntity.ok(personService.updatePerson(personDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        boolean removed = personService.deletePerson(id);
        return removed ? ResponseEntity.ok("Person deleted successfully.")
                : ResponseEntity.status(404).body("Person not found.");
    }
}
