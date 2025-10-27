package org.lessons.java.spring_la_mia_pizzeria_webapi.repository;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PizzasRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findByNameContainingIgnoreCase(String name);

}
