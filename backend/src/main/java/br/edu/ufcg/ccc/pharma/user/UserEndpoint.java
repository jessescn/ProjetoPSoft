package br.edu.ufcg.ccc.pharma.user;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import br.edu.ufcg.ccc.pharma.user.User;
import br.edu.ufcg.ccc.pharma.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserEndpoint {

    private final UserRepository userDAO;

    @Autowired
    public UserEndpoint(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(this.userDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {

        verifyIfUserExists(id);

        Optional<User> userOptional = this.userDAO.findById(id);
        @SuppressWarnings("OptionalGetWithoutIsPresent") User user = userOptional.get();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<?> getPersonByName(@PathVariable("name") String name) {
        List<User> listUsers = this.userDAO.findByNameIgnoreCaseContaining(name);

        if (listUsers.isEmpty())
            throw new ResourceNotFoundException("No user like '" + name + "' was found!");

        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.userDAO.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.verifyIfUserExists(id);

        this.userDAO.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        this.userDAO.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void verifyIfUserExists(Long id) {
        Optional<User> userOptional = this.userDAO.findById(id);

        if (!userOptional.isPresent())
            throw new ResourceNotFoundException("User not found for ID: " + id);
    }
}