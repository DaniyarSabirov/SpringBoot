package Firstprojekt.Firstprojekt.service;

import Firstprojekt.Firstprojekt.dto.PersonCreateRequest;
import Firstprojekt.Firstprojekt.dto.PersonDto;
import Firstprojekt.Firstprojekt.dto.PersonPatchRequest;
import Firstprojekt.Firstprojekt.dto.PersonResponse;
import Firstprojekt.Firstprojekt.model.Person;
import Firstprojekt.Firstprojekt.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
}
