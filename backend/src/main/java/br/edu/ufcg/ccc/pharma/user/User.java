package br.edu.ufcg.ccc.pharma.user;

import br.edu.ufcg.ccc.pharma.model.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
@Entity
public class User extends AbstractEntity {

    @NotEmpty(message = "'name' field may not be empty")
    @NotNull(message = "'name' field may not be null")
    private String name;

    @NotEmpty(message = "'cpf' field may not be empty")
    @NotNull(message = "'cpf' field may not be null")
    private String cpf;

    @Email(message = "'email' field not valid")
    @NotEmpty(message = "'email' field may not be empty")
    @NotNull(message = "'email' field may not be null")
    private String email;

    public User() {}

    public User(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
