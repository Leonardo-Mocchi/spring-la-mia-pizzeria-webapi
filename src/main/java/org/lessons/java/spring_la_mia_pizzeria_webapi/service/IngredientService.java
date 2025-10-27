package org.lessons.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.IngredientsRepository;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.PizzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientsRepository ingredientRepository;

    @Autowired
    PizzasRepository pizzaRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findAllSortedByName() {
        return ingredientRepository.findAll(Sort.by("name"));
    }

    public Optional<Ingredient> findById(Integer id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient getById(Integer id) {

        //? gestire casistica in cui non viene trovata la risorsa
        Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);

        if (ingredientAttempt.isEmpty()) {
            /* throw new NameNotFoundException(); */
            //> lanciamo una not found con una response entity
        }

        return ingredientAttempt.get();
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {

        int ingredientId = ingredient.getId();

        for (Pizza pizza : pizzaRepository.findAll()) {

            List<Ingredient> pizzaIngredientsList = pizza.getIngredients();

            for (Ingredient ingredientToRemove : new java.util.ArrayList<>(pizzaIngredientsList)) {

                if (ingredientToRemove.getId() == ingredientId) {
                    pizzaIngredientsList.remove(ingredientToRemove);
                }
            }

            // * SERVE A SALVARE LE PIZZE MODIFICATE PER NON AVERE PIù L'INGREDIENTE
            pizzaRepository.save(pizza);
        }

        //* ORA CANCELLO L'INGREDIENTE
        ingredientRepository.delete(ingredient);
    }

    public void deleteById(Integer id) {

        Ingredient ingredient = getById(id);

        for (Pizza pizza : pizzaRepository.findAll()) {

            List<Ingredient> pizzaIngredientsList = pizza.getIngredients();

            for (Ingredient ingredientToRemove : new java.util.ArrayList<>(pizzaIngredientsList)) {

                if (ingredientToRemove.getId() == id) {
                    pizzaIngredientsList.remove(ingredientToRemove);
                }
            }

            // * SERVE A SALVARE LE PIZZE MODIFICATE PER NON AVERE PIù L'INGREDIENTE
            pizzaRepository.save(pizza);
        }

        ingredientRepository.delete(ingredient);
    }

    public Boolean existsById(Integer id) {
        return ingredientRepository.existsById(id);
    }

    public Boolean exists(Ingredient ingredient) {
        return existsById(ingredient.getId());
    }
}
