package com.delivery.fastfood.services;

import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.domain.entities.User;
import com.delivery.fastfood.repositories.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu){
        return menuRepository.save(menu);
    }

    public void delete(Menu menu){
        menuRepository.delete(menu);
    }

    public Menu update(String name, Menu updtMenu) {
        Menu existingMenu = menuRepository.findByName(name);

        if (existingMenu == null) {
            throw new RuntimeException("Product not found");
        }

        existingMenu.setName(updtMenu.getName());
        existingMenu.setPrice(updtMenu.getPrice());


        return menuRepository.save(existingMenu);
    }

    public Menu getById(Long id){
        Menu existingMenu = menuRepository.findById(id).get();
        if (existingMenu == null) {
            throw new RuntimeException("Product not found with this id");
        }
        return menuRepository.findById(id).get();
    }
}
