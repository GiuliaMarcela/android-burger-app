package com.example.hamburgueriaz.domain;

import java.util.List;

public interface BurgerRepository {
    List<Burger> findAll();
}
