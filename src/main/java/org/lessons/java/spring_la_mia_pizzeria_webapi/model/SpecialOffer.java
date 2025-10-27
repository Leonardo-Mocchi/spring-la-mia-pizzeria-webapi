package org.lessons.java.spring_la_mia_pizzeria_webapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "special_offers")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "The Beginning date of the Special Offer MUST NOT BE NULL")
    @PastOrPresent(message = "The Beginning date of the Special Offer MUST NOT BE IN THE FUTURE")
    private LocalDate offerFirstDay;

    @NotNull(message = "The Ending date of the Special Offer MUST NOT BE NULL")
    private LocalDate offerLastDay;

    @NotBlank(message = "The name of the Special Offer MUST NOT BE NULL, EMPTY NOR BLANK")
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public SpecialOffer() {
    }

    public SpecialOffer(int id, LocalDate offerFirstDay, LocalDate offerLastDay, String title, Pizza pizza) {
        this.id = id;
        this.offerFirstDay = offerFirstDay;
        this.offerLastDay = offerLastDay;
        this.title = title;
        this.pizza = pizza;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOfferFirstDay() {
        return this.offerFirstDay;
    }

    public void setOfferFirstDay(LocalDate offerFirstDay) {
        this.offerFirstDay = offerFirstDay;
    }

    public LocalDate getOfferLastDay() {
        return this.offerLastDay;
    }

    public void setOfferLastDay(LocalDate offerLastDay) {
        this.offerLastDay = offerLastDay;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", offerFirstDay='" + getOfferFirstDay() + "'" +
                ", offerLastDay='" + getOfferLastDay() + "'" +
                ", title='" + getTitle() + "'" +
                ", pizza='" + getPizza() + "'" +
                "}";
    }
}