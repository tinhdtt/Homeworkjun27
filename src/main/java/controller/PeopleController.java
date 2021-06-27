package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.PersonRepository;

import java.util.Map;

@RestController
@RequestMapping
public class PeopleController {
    @Autowired private PersonRepository personRepo;
    @GetMapping("groupbynationalitythencount")
    public ResponseEntity<Map<String, Long>> groupPeopleByNationalThenCount() {
        return ResponseEntity.ok().body(personRepo.groupPeopleByNationalThenCount());
    }


}
