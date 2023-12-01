package com.delivery.fastfood.repositories;

import com.delivery.fastfood.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);
    User getById(Long id);
}

