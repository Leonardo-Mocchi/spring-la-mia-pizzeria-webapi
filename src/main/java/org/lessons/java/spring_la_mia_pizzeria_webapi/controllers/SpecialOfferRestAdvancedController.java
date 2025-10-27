package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/special-offers")
public class SpecialOfferRestAdvancedController {

    @Autowired
    private SpecialOfferService specialOfferService;

    //> INDEX
    @GetMapping
    public List<SpecialOffer> index() {

        List<SpecialOffer> specialOffers = specialOfferService.findAll();
        return specialOffers;
    }

    //> SHOW
    @GetMapping("/{id}")
    public ResponseEntity<SpecialOffer> show(@PathVariable Integer id) {

        Optional<SpecialOffer> specialOfferAttempt = specialOfferService.findById(id);

        if (specialOfferAttempt.isEmpty()) {
            return new ResponseEntity<SpecialOffer>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SpecialOffer>(specialOfferAttempt.get(), HttpStatus.OK);
    }

    //> STORE
    @PostMapping
    public ResponseEntity<SpecialOffer> store(@RequestBody SpecialOffer specialOffer) {

        return new ResponseEntity<SpecialOffer>(specialOfferService.create(specialOffer), HttpStatus.OK);
    }

    //> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SpecialOffer> update(@PathVariable Integer id, @RequestBody SpecialOffer specialOffer) {

        if (specialOfferService.findById(id).isEmpty()) {
            return new ResponseEntity<SpecialOffer>(HttpStatus.NOT_FOUND);
        }
        specialOffer.setId(id);
        return new ResponseEntity<SpecialOffer>(specialOfferService.update(specialOffer), HttpStatus.OK);
    }

    //> DELETE  
    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialOffer> delete(@PathVariable Integer id) {
        if (specialOfferService.findById(id).isEmpty()) {
            return new ResponseEntity<SpecialOffer>(HttpStatus.NOT_FOUND);
        }

        specialOfferService.deleteById(id);
        return new ResponseEntity<SpecialOffer>(HttpStatus.OK);
    }
}
