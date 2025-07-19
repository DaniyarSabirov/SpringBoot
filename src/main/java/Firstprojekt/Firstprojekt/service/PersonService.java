package Firstprojekt.Firstprojekt.service;

import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PersonService {

    //private List<Person> people = new ArrayList<>();
    //private int id = 1;

    @Autowired
    PersonRepository personRepository;





    /*@PostConstruct
    public void initData(){
        people.add(new Person(id++, "Ali", 22));
        people.add(new Person(id++, "Vali", 25));
        people.add(new Person(id++, "Gulnora", 30));
    }*/

    public List<Person> getPeople(){
        return personRepository.findAll();
    }


    public boolean addPerson(Person person) {
        if (personRepository.existsById(person.getId()))  return true;

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
