package org.lessons.java.spring_la_mia_pizzeria_webapi.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "You CAN'T make a cool new Pizza without giving it a name!")
    @Size(min = 2, max = 50, message = "Name must be within 2 and 50 characters long")
    private String name;

    @Lob
    @NotBlank(message = "Please provide a description for this pizza!")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters long")
    private String description;

    @NotBlank(message = "Image Link must not be null, empty nor blank")
    @Size(min = 5, max = 255, message = "The Image Link must be within 5 and 255 characters long")
    private String picture;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be set higher than 0")
    @DecimalMax(value = "99.99", message = "Price cannot be set higher than 99.99")
    @Digits(integer = 2, fraction = 2, message = "Price must include cents")
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<SpecialOffer> offers;

    @ManyToMany
    @JoinTable(name = "pizza_ingredients", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Pizza() {
    }

    public Pizza(int id, String name, String description, String picture, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SpecialOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<SpecialOffer> offers) {
        this.offers = offers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format("Pizza n° %d is '%s' and costs %.2f € || %s", this.id, this.name, this.price, this.picture);
    }

}
