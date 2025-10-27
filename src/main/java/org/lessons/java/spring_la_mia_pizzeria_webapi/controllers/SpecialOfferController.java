package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.SpecialOfferService;
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
@RequestMapping("/special-offers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferService specialOfferService;

    // > INDEX
    @GetMapping
    public String index(Model model) {

        List<SpecialOffer> specialOffers = specialOfferService.findAll();

        model.addAttribute("offers", specialOffers);
        model.addAttribute("offersQuantity", specialOffers.size());
        return "/special-offers/index";
    }

    // > SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("offer", specialOfferService.getById(id));
        return "/special-offers/show";
    }

    // > STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingresult,
            Model model) {

        if (bindingresult.hasErrors()) {
            return "/special-offers/create-or-edit";
        }
        specialOfferService.create(formSpecialOffer);

        return "redirect:/special-offers/" + formSpecialOffer.getPizza().getId();
    }

    // > EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("specialOffer", specialOfferService.findById(id).get());
        model.addAttribute("edit", true);
        return "/special-offers/create-or-edit";
    }

    // > UPDATE
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/special-offers/create-or-edit";
        }
        specialOfferService.update(formSpecialOffer);

        return "redirect:/special-offers/" + formSpecialOffer.getPizza().getId();
    }
}
