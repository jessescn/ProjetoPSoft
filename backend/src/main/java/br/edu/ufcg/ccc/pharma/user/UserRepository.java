package br.edu.ufcg.ccc.pharma.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByNameIgnoreCaseContaining(String name);
    List<User> findByEmailIgnoreCaseContaining(String email);
    List<User> findByCpf(String cpf);
}
