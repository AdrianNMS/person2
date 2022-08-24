package com.bank.person2.models.services;

import com.bank.person2.models.entities.Person;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonService
{
    Mono<List<Person>> FindAll();
    Mono<Person> Find(Long id);
    Mono<Person> Create(Person person);
    Mono<Person> Update(Long id, Person person);
    Mono<Object> Delete(Long id);
}
