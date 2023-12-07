package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.securities.SecurityUtils;
import com.delivery.fastfood.services.products.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuResource {
    private final MenuService menuService;

    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getAllMenu(){
        System.out.println(SecurityUtils.getUserName());
        return  menuService.getAll();
    }
}
