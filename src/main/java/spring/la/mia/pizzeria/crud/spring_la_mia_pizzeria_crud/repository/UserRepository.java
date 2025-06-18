package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);
    
} 
