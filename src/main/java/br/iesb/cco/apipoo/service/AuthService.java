package br.iesb.cco.apipoo.service;

import br.iesb.cco.apipoo.model.UserEntity;
import br.iesb.cco.apipoo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service @Slf4j
public class AuthService {
    public static final Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private UserRepository repo;

    @Autowired
    private JavaMailSender mailSender;
    public boolean sendEmail(String toEmail,
                             String subject,
                             String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("asureteste123@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        try {
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String login(UserEntity user) {
        String email = user.getEmail();
        String pass = user.getPassword();

        for (UserEntity u : repo.findAll()) {
            if (u.getEmail().equals(email) && u.getPassword().equals(pass)) {
                return u.getToken();
            }
        }
        return null;
    }

    public List<UserEntity> getUser(String email) {
        log.info("Buscando usuário {}", email);
        return repo.findByEmailContaining(email);
    }

    public List<UserEntity> getUsers() {
        log.info("Buscando usuários");
        return repo.findAll();
    }

    public int signup(UserEntity user) {
        Matcher matcher = VALID_EMAIL.matcher(user.getEmail());
        if (!matcher.find()) {
            return 1;
        }

        if (user.getPassword().length() < 6) {
            return 2;
        }

        log.info("Salvando usuário {} no banco de dados", user.getEmail());

        UserEntity entity = new UserEntity("", "", "");
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        String token = UUID.randomUUID().toString();
        entity.setToken(token);

        repo.save(entity);

        return 0;
    }

    public int forgotPassword(UserEntity user) {
        Matcher matcher = VALID_EMAIL.matcher(user.getEmail());
        if (!matcher.find()) {
            return 1;
        }
        String forgot = "Recuperando senha";
        if (!sendEmail(user.getEmail(),forgot, UUID.randomUUID().toString())) {
            return 2;
        }
        return 0;
    }

}
