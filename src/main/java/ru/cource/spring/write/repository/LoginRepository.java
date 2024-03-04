package ru.cource.spring.write.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.spring.write.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login,Long> {
}
