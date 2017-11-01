package reaper.repository;

import org.springframework.data.jpa.repository.Query;
import reaper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sorumi on 17/5/12.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    //TODO 这里应该删掉，还没测
    @Query(value = "SELECT MAX(id) from user;", nativeQuery = true)
    Integer findNextId();

    User findUserById(Integer id);

    User findUserByUsername(String username);

}
