package app.service;

import app.model.CheckArea;
import app.model.entity.CheckAreaEntity;
import app.model.entity.repository.CheckAreaRepository;
import app.model.entity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.ZoneId;
import java.util.List;

@Service // спринг компонент
public class AreaResultService {

    private final UserRepository userRepository;
    private final CheckAreaRepository areaRepository;

    @Autowired // инъекция зависимости UserRepository(UserEntity), CheckAreaRepository(CheckAreaEntity)
    public AreaResultService(final UserRepository userRepository,
                             final CheckAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
    }

    // area - точка
    // username - текущий пользователь
    public void pushToDB(CheckArea area, Principal username) {
        final CheckAreaEntity entity = new CheckAreaEntity();
        entity.setX(area.getRequest().getX());
        entity.setY(area.getRequest().getY());
        entity.setR(area.getRequest().getR());
        entity.setResult(area.isResult());
        entity.setExecutedAt(area.getExecutedAt().atZone(ZoneId.systemDefault()).toLocalDateTime());
        entity.setExecutionTime(area.getExecutionTime());
        entity.setOwnerID(userRepository.findByUsername(username.getName()));
        areaRepository.save(entity);
        // ставим в entity нужные поля и сохраняем
    }

    // метод возвращает все записи по юзернейму
    public List<CheckArea> getAllByUsername(Principal principal) {
        List<CheckAreaEntity> resultEntity = areaRepository
                .findAllByOwnerID(userRepository.findByUsername(principal.getName()));

        return resultEntity.stream().map(CheckArea::fromEntity).toList();
    }

    @Transactional // выполнить в рамках транзакции (атомарно)
    public void removeAllFromUser(Principal principal) {
        areaRepository.deleteAllByOwnerID(userRepository.findByUsername(principal.getName()));
    }
}
