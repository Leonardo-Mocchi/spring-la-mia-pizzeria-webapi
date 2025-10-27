package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.IngredientService;
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
    private IngredientService ingredientService;

    //? INDEX
    @GetMapping
    public String index(Model model) {

        List<Ingredient> ingredients = ingredientService.findAll();

        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    //? SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
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
        ingredientService.create(formIngredient);
        return "redirect:/ingredients";
    }

    //? EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientService.findById(id).get());
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
        ingredientService.update(formIngredient);
        return "redirect:/ingredients";
    }

    //? DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ingredientService.deleteById(id);
        return "redirect:/ingredients";
    }
}
