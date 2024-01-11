package app.controller;

import app.auth.session.SessionHandler;
import app.configuration.AppConfig;
import app.model.entity.UserEntity;
import app.model.entity.repository.UserRepository;
import app.model.request.UserRequestDTO;
import app.model.response.AuthSessionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin // корс разрешает
@RestController
@RequestMapping
public class AuthController {
    private final AuthenticationManager manager;
    private final SessionHandler handler;
    private final UserRepository repository;
    private final AppConfig config;

    @Autowired
    public AuthController(final AuthenticationManager manager,
                          final SessionHandler handler,
                          final UserRepository repository,
                          final AppConfig config) {
        this.manager = manager;
        this.handler = handler;
        this.repository = repository;
        this.config = config;
    }

    @CrossOrigin
    @PostMapping("/login") // обрабатываем логин
    // ожидаем запрос в виде UserRequestDTO, возвращаем ответ в виде AuthSessionResponseDTO
    public ResponseEntity<AuthSessionResponseDTO> login(@RequestBody final UserRequestDTO dto) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()
        )); // аутентификация c помощью AuthentificationManager, создает токен

        final String sessionID = handler.register(dto.getUsername()); // регистрируем сессию пользователя
        final AuthSessionResponseDTO responseDTO = new AuthSessionResponseDTO(); // создаем ответ
        responseDTO.setSessionID(sessionID);
        return ResponseEntity.ok(responseDTO);
    }

    @CrossOrigin
    @DeleteMapping("/logout") // обрабатываем logout
    public ResponseEntity<?> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication) {
        handler.invalidate(authentication); // завершаем сессию
        return ResponseEntity.noContent().build(); // пустой ответ
    }

    @CrossOrigin
    @PostMapping("/register") // обрабатываем регистрацию
    // ожидаем запрос в виде UserRequestDTO, отвечаем AuthSessionResponseDTO
    public ResponseEntity<AuthSessionResponseDTO> register(@RequestBody final UserRequestDTO dto) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(config.passwordEncoder().encode(dto.getPassword()));
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        try { // пытаемся сохранить нового пользователя
            repository.save(newUser);
        } catch (Throwable e) {  // если не вышло то либо пользователь уже есть либо база отвалилась
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error. Unable to register user. User already exists or database error");
        }
        return login(dto); // если все ок то идем в логин
    }
}
