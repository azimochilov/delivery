package com.delivery.fastfood.repositories;

import com.delivery.fastfood.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    Menu findByName(String name);

}
