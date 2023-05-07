package br.iesb.cco.apipoo;

import br.iesb.cco.apipoo.model.UserEntity;
import br.iesb.cco.apipoo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApipooApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApipooApplication.class, args);
    }

}
