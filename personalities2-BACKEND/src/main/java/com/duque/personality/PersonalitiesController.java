package com.duque.personality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/duque")
@CrossOrigin(origins = "http://localhost:5173")
public class PersonalitiesController {

    @Autowired
    private PersonalitiesRepository personalityRepository;

    // GET /duque/personalities - Retrieve all personalities
    @GetMapping(path = "/personalities")
    public ResponseEntity<Iterable<Personalities>> getAllPersonalities() {
        return new ResponseEntity<>(personalityRepository.findAll(), HttpStatus.OK);
    }

    // GET /duque/personalities/{id} - Retrieve a specific personality by ID
    @GetMapping(path = "/personalities/{id}")
    public ResponseEntity<Personalities> getPersonalityById(@PathVariable Integer id) {
        return personalityRepository.findById(id)
                .map(personality -> new ResponseEntity<>(personality, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /duque/personalities - Add a single personality
    @PostMapping(path = "/personalities", consumes = "application/json")
    public ResponseEntity<Personalities> addNewPersonality(@RequestBody Personalities personality) {
        Personalities savedPersonality = personalityRepository.save(personality);
        return new ResponseEntity<>(savedPersonality, HttpStatus.CREATED);
    }

    // POST /duque/personalities/bulk - Add multiple personalities
    @PostMapping(path = "/personalities/bulk", consumes = "application/json")
    public ResponseEntity<List<Personalities>> addMultiplePersonalities(@RequestBody List<Personalities> personalities) {
        List<Personalities> savedPersonalities = (List<Personalities>) personalityRepository.saveAll(personalities);
        return new ResponseEntity<>(savedPersonalities, HttpStatus.CREATED);
    }

    // PUT /duque/personalities/{id} - Update an existing personality
    @PutMapping(path = "/personalities/{id}", consumes = "application/json")
    public ResponseEntity<Personalities> updatePersonality(@PathVariable Integer id, @RequestBody Personalities updatedPersonality) {
        return personalityRepository.findById(id)
                .map(existingPersonality -> {
                    updatedPersonality.setId(id);
                    Personalities savedPersonality = personalityRepository.save(updatedPersonality);
                    return new ResponseEntity<>(savedPersonality, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE /duque/personalities/{id} - Delete a personality by ID
    @DeleteMapping(path = "/personalities/{id}")
    public ResponseEntity<Void> deletePersonality(@PathVariable Integer id) {
        if (personalityRepository.existsById(id)) {
            personalityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}