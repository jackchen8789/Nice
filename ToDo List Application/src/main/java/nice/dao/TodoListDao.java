package nice.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import nice.models.ToDoList;

@Transactional
public interface TodoListDao extends CrudRepository<ToDoList, Long> {

	//Task findByTaskName(String taskName);
}
