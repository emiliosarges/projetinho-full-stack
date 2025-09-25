package com.example.people.controller;

import com.example.people.dto.PersonRequest;
import com.example.people.dto.PersonResponse;
import com.example.people.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
@CrossOrigin(origins = "http://localhost:5173")
public class PersonController {

    private final PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse create(@Valid @RequestBody PersonRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<PersonResponse> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public PersonResponse update(@PathVariable Long id, @Valid @RequestBody PersonRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
