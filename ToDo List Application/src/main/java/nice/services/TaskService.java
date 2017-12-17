package nice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nice.models.Task;
import nice.models.ToDoList;
import nice.dao.TaskDao;
import nice.dao.TodoListDao;

@Service
public class TaskService {

	@Autowired
    private TaskDao taskDao;

	@Transactional
    public Iterable<Task> findAll() {
    	return taskDao.findAll();
    }

    @Transactional
	public Task findById(long id) {
		Task task;

        try {
             task = taskDao.findOne(id);
        } catch (Exception e) {
            task = null;
        }

        return task;
	}

	
}