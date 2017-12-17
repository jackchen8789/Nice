package nice.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import nice.models.ToDoListNTask;

@Transactional
public interface TodoListNTaskDao extends CrudRepository<ToDoListNTask, Long> {

}
