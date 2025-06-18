package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.User;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.UserRepository;

public class DatabaseUserDetailService implements UserDetailsService{

    //Recupero di un utente in base allo username

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> userAttempt = userRepository.findByUsername(username);

        if(userAttempt.isPresent()){
            return new DatabaseUserDetails(userAttempt.get());
        }else{
            throw new UsernameNotFoundException("Username not found");
        }



    }
    
}
