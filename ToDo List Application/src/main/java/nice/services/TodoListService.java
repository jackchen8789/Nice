package nice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nice.models.ToDoList;
import nice.dao.TodoListDao;

@Service
public class TodoListService {
	
	private static final Logger log = LoggerFactory.getLogger(TodoListService.class);

	@Autowired
    private TodoListDao todoListDao;

	@Transactional
    public Iterable<ToDoList> findAll() {
    	return todoListDao.findAll();
    }

    @Transactional
	public ToDoList findById(long id) {
		ToDoList todoList;

        try {
             todoList = todoListDao.findOne(id);
        } catch (Exception e) {
            todoList = null;
        }

        return todoList;
	}

	@Transactional
	public ToDoList createTodoList(String listName) {
		System.out.println("createTodolist: " + listName);
		log.info("createTodolist: " + listName);
		ToDoList todoList = new ToDoList(listName);
        return todoListDao.save(todoList);
	}

	@Transactional
	public ToDoList updateTodoList(Long id, String listName) {
		try {
			ToDoList todoList = todoListDao.findOne(id);
			todoList.setName(listName);
            return todoListDao.save(todoList);
        } catch (Exception e) {
            return null;
        }
	}
	
	@Transactional
	public ToDoList deleteTodoListById(Long id) {
		try {
			ToDoList user = todoListDao.findOne(id);
            todoListDao.delete(user);
            return user;
        } catch (Exception e) {
           return null;
        }
	}
	
	@Transactional
	public void deleteAllTodoLists() {
		todoListDao.deleteAll();
	}
	
}