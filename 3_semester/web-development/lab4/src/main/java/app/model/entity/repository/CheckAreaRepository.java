package app.model.entity.repository;

import app.model.entity.CheckAreaEntity;
import app.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckAreaRepository extends JpaRepository<CheckAreaEntity, Long> {

    List<CheckAreaEntity> findAllByOwnerID(UserEntity entity);

    void deleteAllByOwnerID(UserEntity entity);
}
