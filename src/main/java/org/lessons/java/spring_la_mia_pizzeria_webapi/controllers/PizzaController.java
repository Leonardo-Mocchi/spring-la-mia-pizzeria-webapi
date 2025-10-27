package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.IngredientsRepository;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientsRepository ingredientRepository;

    // * INDEX

    @GetMapping
    public String pizzas(Model model) {

        List<Pizza> pizzas = pizzaService.findAll();

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // * SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("pizza", pizzaService.getById(id));
        return "pizzas/show";
    }

    // ? Filter for the INDEX route
    @GetMapping("/search")
    public String searchByName(@RequestParam String name, Model model) {
        List<Pizza> pizzas = pizzaService.findByName(name);

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("searched", true);
        return "pizzas/index";
    }

    // * CREATE
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredientsList", ingredientRepository.findAll());
        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientsList", ingredientRepository.findAll());
            return "pizzas/create";
        }
        pizzaService.create(formPizza);
        return "redirect:/pizzas";
    }

    // * UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("pizza", pizzaService.findById(id).get());
        model.addAttribute("ingredientsList", ingredientRepository.findAll());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientsList", ingredientRepository.findAll());
            return "pizzas/edit";
        }
        pizzaService.update(formPizza);
        return "redirect:/pizzas";
    }

    // * DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Pizza pizza = pizzaService.findById(id).get();
        pizzaService.delete(pizza);
        return "redirect:/pizzas";
    }

    // > CREATE for special offer
    @GetMapping("/{id}/special-offers")
    public String specialOffers(@PathVariable Integer id, Model model) {

        SpecialOffer specialOffer = new SpecialOffer();

        specialOffer.setPizza(pizzaService.findById(id).get());

        model.addAttribute("specialOffer", specialOffer);
        return "/special-offers/create-or-edit";
    }
}
