package com.delivery.fastfood.services.products;


import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.exception.NotFoundException;
import com.delivery.fastfood.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu){
        Menu exsistsMenu = menuRepository.findByName(menu.getName());
        if(exsistsMenu == null){
            throw new NotFoundException("Product already exsists! ");
        }
        return menuRepository.save(menu);
    }

    public void delete(Menu menu){
        menuRepository.delete(menu);
    }

    public Menu update(String name, Menu updtMenu) {
        Menu existingMenu = menuRepository.findByName(name);

        if (existingMenu == null) {
            throw new NotFoundException("Product not found");
        }

        existingMenu.setName(updtMenu.getName());
        existingMenu.setPrice(updtMenu.getPrice());


        return menuRepository.save(existingMenu);
    }

    public List<Menu> getAll(){
        return menuRepository.findAll();
    }

    public Menu getById(Long id){
        Menu existingMenu = menuRepository.findById(id).get();
        if (existingMenu == null) {
            throw new NotFoundException("Product not found with this id");
        }
        return menuRepository.findById(id).get();
    }
}
