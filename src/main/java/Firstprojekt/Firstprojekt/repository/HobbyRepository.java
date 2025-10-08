package Firstprojekt.Firstprojekt.repository;



import Firstprojekt.Firstprojekt.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long>{

    @Query("SELECT h.name, COUNT(p) FROM Hobby h JOIN h.persons p GROUP BY h.name")
    List<Hobby[]> countPersonsByHobby();
}
