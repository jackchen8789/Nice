package nice.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import nice.models.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  User findByUserName(String userName);

}