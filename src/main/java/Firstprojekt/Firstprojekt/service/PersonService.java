package Firstprojekt.Firstprojekt.service;

import Firstprojekt.Firstprojekt.dto.PersonCreateRequest;
import Firstprojekt.Firstprojekt.dto.PersonPatchRequest;
import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PersonResponse getPeople(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Person> personsPage = personRepository.findAll(pageable);

        List<PersonPatchRequest> personDtoList = personsPage.getContent()
                .stream()
                .map(person -> new PersonPatchRequest(
                        person.getName(),
                        person.getAge(),
                        person.getWorkEmail()
                ))
                .toList();

        return PersonResponse.builder()
                .personDto(personDtoList)
                .last(personsPage.isLast())
                .totalElements((int) personsPage.getTotalElements())
                .totalPages(personsPage.getTotalPages())
                .first(personsPage.isFirst())
                .size(personsPage.getSize())
                .number(personsPage.getNumber())
                .build();
    }


    public PersonResponse addPerson(PersonCreateRequest personDto) {
        Person saved = personRepository.save(mapper.map(personDto, Person.class));
        return mapper.map(saved, PersonResponse.class);
    }

    public PersonResponse updatePerson(PersonPatchRequest personDto, Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.map(personDto, person);
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
