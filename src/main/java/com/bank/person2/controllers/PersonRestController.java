package com.bank.person2.controllers;

import com.bank.person2.handler.ResponseHandler;
import com.bank.person2.models.entities.Person;
import com.bank.person2.models.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person2")
public class PersonRestController
{
    @Autowired
    PersonService personService;

    private static final Logger log = LoggerFactory.getLogger(PersonRestController.class);

    @PostMapping
    public Mono<ResponseEntity<Object>> Create(@Validated @RequestBody Person person) {
        log.info("[INIT] create Person");
        return personService.Create(person)
                .flatMap(per -> Mono.just(ResponseHandler.response("Done", HttpStatus.OK, per)))
                .onErrorResume(error -> Mono.just(ResponseHandler.response(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] create Person"));
    }

    @GetMapping
    public Mono<ResponseEntity<Object>> FindAll()
    {
        log.info("[INIT] findAll Person");

        return personService.FindAll()
                .flatMap(people -> Mono.just(ResponseHandler.response("Done", HttpStatus.OK,people)))
                .onErrorResume(error -> Mono.just(ResponseHandler.response(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] findAll Person"));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> Find(@PathVariable Long id) {

        log.info("[INIT] find Person");

        return personService.Find(id)
                .flatMap(person -> Mono.just(ResponseHandler.response("Done", HttpStatus.OK, person)))
                .onErrorResume(error -> Mono.just(ResponseHandler.response(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] find Person"));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Object>> Update(@PathVariable("id") Long id,@Validated @RequestBody Person person) {

        log.info("[INIT] update Person");

        return personService.Update(id,person)
                .flatMap(p -> Mono.just(ResponseHandler.response("Done", HttpStatus.OK, p)))
                .onErrorResume(error -> Mono.just(ResponseHandler.response(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] update Person"));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> Delete(@PathVariable("id") Long id) {

        log.info("[INIT] delete Person");

        return personService.Delete(id)
                .flatMap(o -> Mono.just(ResponseHandler.response("Done", HttpStatus.OK, null)))
                .onErrorResume(error -> Mono.just(ResponseHandler.response(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .switchIfEmpty(Mono.just(ResponseHandler.response("Error", HttpStatus.NO_CONTENT, null)))
                .doFinally(fin -> log.info("[END] delete Person"));
    }

}
