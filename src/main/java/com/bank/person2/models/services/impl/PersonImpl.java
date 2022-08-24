package com.bank.person2.models.services.impl;

import com.bank.person2.models.entities.Person;
import com.bank.person2.models.repository.PersonRepository;
import com.bank.person2.models.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonImpl implements PersonService
{
    @Autowired
    PersonRepository dao;

    @Override
    public Mono<List<Person>> FindAll()
    {
        return dao.findAll()
                .collectList();
    }

    @Override
    public Mono<Person> Find(Long id) {
        return dao.findById(id);
    }

    @Override
    public Mono<Person> Create(Person person) {
        return dao.save(person);
    }

    @Override
    public Mono<Person> Update(Long id, Person person) {
        return dao.existsById(id).flatMap(check -> {
            if (check)
            {
                return dao.save(person);
            }
            else
                return Mono.empty();
        });
    }

    @Override
    public Mono<Object> Delete(Long id) {
        return dao.existsById(id).flatMap(check -> {
            if (check)
                return dao.deleteById(id).then(Mono.just(true));
            else
                return Mono.empty();
        });
    }
}
