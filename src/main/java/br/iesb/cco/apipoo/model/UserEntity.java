package br.iesb.cco.apipoo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String token;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public UserEntity(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void postRemove() {

    }
}