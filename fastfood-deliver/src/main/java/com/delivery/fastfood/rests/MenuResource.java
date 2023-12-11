package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.domain.entities.Product;
import com.delivery.fastfood.securities.SecurityUtils;
import com.delivery.fastfood.services.products.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuResource {
    private final MenuService menuService;

    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/product")
    public ResponseEntity create(@RequestBody Menu product){
        menuService.create(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity delete(@RequestBody Long id){
        menuService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/products")
    public ResponseEntity getAll(){
        List<Menu> products = menuService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getById(@RequestBody Long id){
        return ResponseEntity.ok(menuService.getById(id));
    }

    @PutMapping("/product/{name}")
    public ResponseEntity update(@PathVariable String name,@RequestBody Menu menu){
        Menu updatedMenu = menuService.update(name,menu);
        return ResponseEntity.ok(updatedMenu);
    }
}
