package com.example.starsgallery.service;

import com.example.starsgallery.beans.ClassStart;
import com.example.starsgallery.dao.IDao;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<ClassStart> {

    private List<ClassStart> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    private void seed() {
        stars.add(new ClassStart("Emma Watson",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Emma_Watson_2013.jpg/440px-Emma_Watson_2013.jpg", 4.5f));
        stars.add(new ClassStart("Tom Cruise",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Tom_Cruise_by_Gage_Skidmore_2.jpg/440px-Tom_Cruise_by_Gage_Skidmore_2.jpg", 4.2f));
        stars.add(new ClassStart("Scarlett Johansson",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Scarlett_Johansson_by_Gage_Skidmore_2_%28cropped%29.jpg/440px-Scarlett_Johansson_by_Gage_Skidmore_2_%28cropped%29.jpg", 4.7f));
        stars.add(new ClassStart("Leonardo DiCaprio",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Leonardo_Dicaprio_Cannes_2019.jpg/440px-Leonardo_Dicaprio_Cannes_2019.jpg", 4.8f));
        stars.add(new ClassStart("Chris Hemsworth",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Chris_Hemsworth_by_Gage_Skidmore.jpg/440px-Chris_Hemsworth_by_Gage_Skidmore.jpg", 4.6f));
        stars.add(new ClassStart("Jennifer Lawrence",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Jennifer_Lawrence_SDCC_2015.jpg/440px-Jennifer_Lawrence_SDCC_2015.jpg", 4.4f));
        stars.add(new ClassStart("Robert Downey Jr",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg/440px-Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg", 4.9f));
        stars.add(new ClassStart("Margot Robbie",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Margot_Robbie_in_2019.jpg/440px-Margot_Robbie_in_2019.jpg", 4.7f));
        stars.add(new ClassStart("Will Smith",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Will_Smith_2011.jpg/440px-Will_Smith_2011.jpg", 4.3f));
        stars.add(new ClassStart("Natalie Portman",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Natalie_Portman_Cannes_2015.jpg/440px-Natalie_Portman_Cannes_2015.jpg", 4.5f));
        stars.add(new ClassStart("Keanu Reeves",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Keanu_Reeves_2014.jpg/440px-Keanu_Reeves_2014.jpg", 4.9f));
        stars.add(new ClassStart("Zendaya",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Zendaya_at_the_2019_InStyle_and_Warner_Bros._Golden_Globes_Party_%28cropped%29.jpg/440px-Zendaya_at_the_2019_InStyle_and_Warner_Bros._Golden_Globes_Party_%28cropped%29.jpg", 4.6f));
        stars.add(new ClassStart("Dwayne Johnson",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Dwayne_Johnson_2014_%28cropped%29.jpg/440px-Dwayne_Johnson_2014_%28cropped%29.jpg", 4.4f));
        stars.add(new ClassStart("Gal Gadot",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Gal_Gadot_by_Gage_Skidmore.jpg/440px-Gal_Gadot_by_Gage_Skidmore.jpg", 4.5f));
        stars.add(new ClassStart("Brad Pitt",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Brad_Pitt_2019_by_Glenn_Francis.jpg/440px-Brad_Pitt_2019_by_Glenn_Francis.jpg", 4.7f));
        stars.add(new ClassStart("Morgan Freeman",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Morgan_Freeman_2018.jpg/440px-Morgan_Freeman_2018.jpg", 4.9f));
    }

    @Override public boolean create(ClassStart o) { return stars.add(o); }

    @Override
    public boolean update(ClassStart o) {
        for (ClassStart s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setRating(o.getRating());
                return true;
            }
        }
        return false;
    }

    @Override public boolean delete(ClassStart o) { return stars.remove(o); }

    @Override
    public ClassStart findById(int id) {
        for (ClassStart s : stars) if (s.getId() == id) return s;
        return null;
    }

    @Override public List<ClassStart> findAll() { return stars; }
}