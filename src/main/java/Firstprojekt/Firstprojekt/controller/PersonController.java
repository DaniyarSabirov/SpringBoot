package Firstprojekt.Firstprojekt.controller;

import Firstprojekt.Firstprojekt.dto.PersonCreateRequest;
import Firstprojekt.Firstprojekt.dto.PersonDto;
import Firstprojekt.Firstprojekt.dto.PersonPatchRequest;
import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
             @RequestParam(defaultValue = "10") int size,
             @RequestParam(defaultValue = "name") String sortBy,
             @RequestParam(defaultValue = "asc") String direction){
        return ResponseEntity.ok(personService.getPeople(page, size, sortBy, direction));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(@RequestParam String message){
        return ResponseEntity.ok(message);
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> addPerson(@RequestBody @Valid PersonCreateRequest personCreateRequest) {
        return ResponseEntity.status(201).body(personService.addPerson(personCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id,
                                                  @RequestBody @Valid PersonPatchRequest personDto){
        return ResponseEntity.ok(personService.updatePerson(personDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDto> deletePerson(@PathVariable Long id) {

        PersonDto personDto = personService.findPersonById(id);

        boolean removed = personService.deletePerson(id);
        return removed ? ResponseEntity.ok(personDto)
                : ResponseEntity.status(404).body(personDto);
    }
}
