package app.service;

import app.auth.CurrentUser;
import app.model.entity.UserEntity;
import app.model.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service // Service - спринг аннотация - управляется контейнером спринг
public class CurrentUserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired // инъекция зависимости UserRepository=>UserEntity через конструктор
    public CurrentUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override // Возвращаем объект CurrenUser по имени пользователя
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity foundUser = repository.findByUsername(username);

        if (foundUser == null)
            throw new UsernameNotFoundException("Cannot find user with name " + username);

        return CurrentUser.fromEntity(foundUser);
    }
}
