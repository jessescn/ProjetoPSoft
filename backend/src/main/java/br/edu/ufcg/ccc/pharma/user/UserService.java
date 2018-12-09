package br.edu.ufcg.ccc.pharma.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setAdmin(false);
        this.save(user);
    }

    public void saveAdmin(User user) {
        user.setAdmin(true);
        this.save(user);
    }

    private void save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(1);
        this.userRepository.save(user);
    }
}
