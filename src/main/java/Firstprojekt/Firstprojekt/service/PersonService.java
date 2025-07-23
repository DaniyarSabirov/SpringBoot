package Firstprojekt.Firstprojekt.service;

import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getPeople(){
        return personRepository.findAll();
    }


    public boolean addPerson(Person person) {
        personRepository.save(person);
        return false;
    }

    public boolean updatePerson(Person newPerson, Long id){

        Person person = personRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());

        personRepository.save(person);

        return true;
    }

    public boolean deletePerson(Long id){

        personRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        personRepository.deleteById(id);

        return true;
    }
}
