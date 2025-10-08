package Firstprojekt.Firstprojekt.repository;

import Firstprojekt.Firstprojekt.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobbyName")
    List<Person> findByHobbyName(@Param("hobbyName") String hobbyName);

    @Query("SELECT p FROM Person p WHERE p.address.city = :city")
    List<Person> findByCity(@Param("city") String city);

}
