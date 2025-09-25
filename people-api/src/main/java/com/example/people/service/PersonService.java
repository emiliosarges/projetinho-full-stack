package com.example.people.service;

import com.example.people.domain.Person;
import com.example.people.dto.PersonRequest;
import com.example.people.dto.PersonResponse;
import com.example.people.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repo;

    public PersonResponse create(PersonRequest req) {
        Person p = Person.builder()
                .name(req.getName())
                .birthDate(req.getBirthDate())
                .build();
        p = repo.save(p);
        return toResponse(p);
    }

    public List<PersonResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PersonResponse findById(Long id) {
        Person p = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        return toResponse(p);
    }

    public PersonResponse update(Long id, PersonRequest req) {
        Person p = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        p.setName(req.getName());
        p.setBirthDate(req.getBirthDate());
        return toResponse(repo.save(p));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Pessoa não encontrada");
        repo.deleteById(id);
    }

    private PersonResponse toResponse(Person p) {
        return PersonResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .birthDate(p.getBirthDate())
                .build();
    }
}
