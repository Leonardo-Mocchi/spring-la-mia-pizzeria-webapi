package org.lessons.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.PizzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzasRepository pizzaRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> findAllSortedByName() {
        return pizzaRepository.findAll(Sort.by("name"));
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id) {

        //? gestire casistica in cui non viene trovata la risorsa
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

        if (pizzaAttempt.isEmpty()) {
            /* throw new NameNotFoundException(); */
            //> lanciamo una not found con una response entity
        }

        return pizzaAttempt.get();
    }

    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {

        //, OPZIONALE IN CASO USO IL PARAMETRO CASCADE COME è SCRITTO NELLA ONETOMANY IN pizza.JAVA
        /*  for (Borrowing borrowingToDelete : pizza.getBorrowings()) {
        borrowingRepository.delete(borrowingToDelete);
        } */
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {

        Pizza pizza = getById(id);

        //, come sopra

        pizzaRepository.delete(pizza);
    }

    public Boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public Boolean exists(Pizza pizza) {
        return existsById(pizza.getId());
    }
}
