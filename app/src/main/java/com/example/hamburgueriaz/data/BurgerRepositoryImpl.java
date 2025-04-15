package com.example.hamburgueriaz.data;

import android.content.Context;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.domain.Burger;
import com.example.hamburgueriaz.domain.BurgerRepository;

import java.util.Arrays;
import java.util.List;

public class BurgerRepositoryImpl implements BurgerRepository {

    private final Context context;

    public BurgerRepositoryImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public List<Burger> findAll() {
        return Arrays.asList(
                new Burger(context.getString(R.string.egg_burger), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "20.00", R.drawable.unsplash_burger_photo),
                new Burger(context.getString(R.string.cheese_burger), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "20.00", R.drawable.unsplash_burger_photo),
                new Burger(context.getString(R.string.chicken_burger), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "20.00", R.drawable.unsplash_burger_photo),
                new Burger(context.getString(R.string.veggie_burger), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "20.00", R.drawable.unsplash_burger_photo)
        );
    }
}
