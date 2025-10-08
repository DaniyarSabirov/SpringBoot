package Firstprojekt.Firstprojekt.service;

import Firstprojekt.Firstprojekt.dto.PersonCreateRequest;
import Firstprojekt.Firstprojekt.dto.PersonDto;
import Firstprojekt.Firstprojekt.dto.PersonPatchRequest;
import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.model.Hobby;
import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.repository.HobbyRepository;
import Firstprojekt.Firstprojekt.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final HobbyRepository hobbyRepository;
    private final ModelMapper mapper;


    public PersonService(PersonRepository personRepository, HobbyRepository hobbyRepository, ModelMapper mapper) {
        this.personRepository = personRepository;
        this.hobbyRepository = hobbyRepository;
        this.mapper = mapper;
    }
    public PersonResponse getPeople(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Person> personsPage = personRepository.findAll(pageable);

        List<PersonDto> listPersonDto = personsPage.getContent()
                .stream().map(person -> mapper.map(person, PersonDto.class)).toList();

        return PersonResponse.builder()
                .personDto(listPersonDto)
                .last(personsPage.isLast())
                .totalElements((int) personsPage.getTotalElements())
                .totalPages(personsPage.getTotalPages())
                .first(personsPage.isFirst())
                .size(personsPage.getSize())
                .pageNumber(personsPage.getNumber())
                .build();
    }


    public PersonDto addPerson(PersonCreateRequest personCreateRequest) {
        Person saved = personRepository.save(mapper.map(personCreateRequest, Person.class));
        return mapper.map(saved, PersonDto.class);
    }

    public PersonDto updatePerson(PersonPatchRequest personPatchRequest, Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.map(personPatchRequest, person);
        Person saved = personRepository.save(person);
        return mapper.map(saved, PersonDto.class);
    }

    public boolean deletePerson(Long id){
        personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        personRepository.deleteById(id);
        return true;
    }

    public PersonDto findPersonById(Long id) {
        return mapper.map(personRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)), PersonDto.class);
    }


    public Person addHobbyToPerson(Long personId, Long hobbyId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Hobby hobby = hobbyRepository.findById(hobbyId)
                .orElseThrow(() -> new RuntimeException("Hobby not found"));

        person.getHobbies().add(hobby);
        personRepository.save(person);

        return person;
    }

    public List<Person> findByHobbyName(String name) {
        return personRepository.findByHobbyName(name);
    }

    public List<Person> findByCity(String city) {
        return personRepository.findByCity(city);
    }
}
