package Firstprojekt.Firstprojekt.service;

import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper mapper;

    public PersonService(PersonRepository personRepository, ModelMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    public List<PersonResponse> getPeople(){
        return personRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, PersonResponse.class))
                .toList();
    }

    public PersonResponse addPerson(Person person) {
        Person saved = personRepository.save(person);
        return mapper.map(saved, PersonResponse.class);
    }

    public PersonResponse updatePerson(Person newPerson, Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.map(newPerson, person);
        Person saved = personRepository.save(person);
        return mapper.map(saved, PersonResponse.class);
    }

    public boolean deletePerson(Long id){
        personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        personRepository.deleteById(id);
        return true;
    }
}
