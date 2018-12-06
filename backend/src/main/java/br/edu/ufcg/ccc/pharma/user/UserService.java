package br.edu.ufcg.ccc.pharma.user;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userDAO;

    @Autowired
    public UserService(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    public User saveUser() {
        return null;
    }

    private void verifyIfUserExists(Long id) {
        Optional<User> userOptional = this.userDAO.findById(id);

        if (!userOptional.isPresent())
            throw new ResourceNotFoundException("User not found for ID: " + id);
    }

}
