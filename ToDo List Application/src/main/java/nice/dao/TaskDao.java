package nice.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import nice.models.Task;

@Transactional
public interface TaskDao extends CrudRepository<Task, Long> {

	Task findByTaskName(String taskName);
}
