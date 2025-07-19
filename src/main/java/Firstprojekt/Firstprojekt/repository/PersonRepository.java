package Firstprojekt.Firstprojekt.repository;

import Firstprojekt.Firstprojekt.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
