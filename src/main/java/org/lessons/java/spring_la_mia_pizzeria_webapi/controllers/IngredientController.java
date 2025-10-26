package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.IngredientsRepository;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.PizzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsRepository ingredientRepository;

    @Autowired
    private PizzasRepository pizzaRepository;

    //? INDEX
    @GetMapping
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    //? SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        return "ingredients/show";
    }

    //? CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    //? STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    //? DELETE

    //> old simple route (does not work)
    /* @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    } */

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

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

        //* ORA CANCELLO L'INGREDIENTE
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }

    //? EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    //? UPDATE
    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }
}
