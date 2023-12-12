package com.gleestorm.cargo.core.service;

import com.gleestorm.cargo.authentication.model.User;
import com.gleestorm.cargo.authentication.repository.UserRepository;
import com.gleestorm.cargo.exceptions.UserNotFoundException;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class BaseService {


    final UserRepository userRepository;

    public BaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * On recupère l'utilisateur connecté
     * du contexte de spring security
     *
     * @return
     */

    protected User getCurrentConnectedUser() throws UserNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated()) {
            // Récupérer l'identifiant de l'utilisateur connecté
            String userName =  authentication.getName(); // Ceci retourne généralement le nom d'utilisateur

            val userResponse = userRepository.findByEmail(userName);

            if(userResponse.isEmpty()){
                throw new UserNotFoundException("Cannot find user: "+userName);
            }

            return userResponse.get();
        }

        throw new  UserNotFoundException("Impossibke de retrouver l'utilisateur connecté"); // on lance une exceptin
    }
}
