package org.lessons.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.SpecialOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

    @Autowired
    private SpecialOffersRepository specialOffersRepository;

    public List<SpecialOffer> findAll() {
        return specialOffersRepository.findAll();
    }

    public List<SpecialOffer> findAllSortedByTitle() {
        return specialOffersRepository.findAll(Sort.by("title"));
    }

    public Optional<SpecialOffer> findById(Integer id) {
        return specialOffersRepository.findById(id);
    }

    public SpecialOffer getById(Integer id) {

        //? gestire casistica in cui non viene trovata la risorsa
        Optional<SpecialOffer> specialOfferAttempt = specialOffersRepository.findById(id);

        if (specialOfferAttempt.isEmpty()) {
            /* throw new NameNotFoundException(); */
            //> lanciamo una not found con una response entity
        }

        return specialOfferAttempt.get();
    }

    public SpecialOffer create(SpecialOffer specialOffer) {
        return specialOffersRepository.save(specialOffer);
    }

    public SpecialOffer update(SpecialOffer specialOffer) {
        return specialOffersRepository.save(specialOffer);
    }

    public void delete(SpecialOffer specialOffer) {
        specialOffersRepository.delete(specialOffer);
    }

    public void deleteById(Integer id) {

        SpecialOffer specialOffer = getById(id);

        //, come sopra

        specialOffersRepository.delete(specialOffer);
    }

    public Boolean existsById(Integer id) {
        return specialOffersRepository.existsById(id);
    }

    public Boolean exists(SpecialOffer specialOffer) {
        return existsById(specialOffer.getId());
    }
}
