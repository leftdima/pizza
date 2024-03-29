package com.baranchik.service;

import com.baranchik.composits.PizzaTransform;
import com.baranchik.model.Pizza;
import com.baranchik.model.Pizzeria;
import com.baranchik.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    @Transactional
    public List<Pizza> getPizzaList() {
        return pizzaRepository.getPizzaList();
    }

    @Override
    @Transactional
    public List<PizzaTransform> getTransformsPizzas() {
        List<PizzaTransform> pizzaTransforms = new ArrayList<>();
        List<Pizza> pizzas = pizzaRepository.getPizzaList();
        pizzas.forEach(pizza -> pizzaTransforms.add(new PizzaTransform(pizza, false)));
        return pizzaTransforms;
    }

    @Override
    @Transactional
    public Pizzeria getDefaultPizzeria() {
        return pizzaRepository.getDefaultPizzeria();
    }

    @Override
    @Transactional
    public Pizzeria getPizzeria(Integer id) {
        return pizzaRepository.getPizzeria(id);
    }

    @Override
    @Transactional
    public List<Pizzeria> getPizzerias(){
        return pizzaRepository.getPizzerias();
    }
}
