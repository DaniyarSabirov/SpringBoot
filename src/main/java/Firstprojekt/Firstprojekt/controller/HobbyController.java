package Firstprojekt.Firstprojekt.controller;

import Firstprojekt.Firstprojekt.model.Hobby;
import Firstprojekt.Firstprojekt.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {

    private final HobbyRepository hobbyRepository;

    public HobbyController(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    @GetMapping
    public List<Hobby> getAll() {
        return hobbyRepository.findAll();
    }

    @PostMapping
    public Hobby create(@RequestBody Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    @GetMapping("/stats")
    public List<Hobby[]> getStats() {
        return hobbyRepository.countPersonsByHobby();
    }
}
