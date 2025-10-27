package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.IngredientService;
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
@RequestMapping("/api/ingredients")
public class IngredientRestAdvancedController {

    @Autowired
    private IngredientService ingredientService;

    //? INDEX
    @GetMapping
    public List<Ingredient> index() {

        List<Ingredient> ingredients = ingredientService.findAll();
        return ingredients;
    }

    //? SHOW
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> show(@PathVariable Integer id) {

        Optional<Ingredient> ingredientAttempt = ingredientService.findById(id);

        if (ingredientAttempt.isEmpty()) {
            return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Ingredient>(ingredientAttempt.get(), HttpStatus.OK);
    }

    //? STORE
    @PostMapping
    public ResponseEntity<Ingredient> store(@RequestBody Ingredient ingredient) {

        return new ResponseEntity<Ingredient>(ingredientService.create(ingredient), HttpStatus.OK);
    }

    //? UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable Integer id, @RequestBody Ingredient ingredient) {

        if (ingredientService.findById(id).isEmpty()) {
            return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
        }
        ingredient.setId(id);
        return new ResponseEntity<Ingredient>(ingredientService.update(ingredient), HttpStatus.OK);
    }

    //? DELETE  
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable Integer id) {
        if (ingredientService.findById(id).isEmpty()) {
            return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
        }

        ingredientService.deleteById(id);
        return new ResponseEntity<Ingredient>(HttpStatus.OK);
    }
}
