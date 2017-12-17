package nice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nice.enumeration.TaskStatus;
import nice.exception.TaskNotFoundException;
import nice.exception.UserNotFoundException;
import nice.models.Task;
import nice.models.ToDoList;
import nice.dao.TaskDao;
import nice.models.User;
import nice.services.TaskService;
import nice.services.TodoListService;
import nice.services.UserService;

@Controller
@EnableAutoConfiguration
public class UsersController {

    @Autowired
    private TodoListService todoListService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskDao taskDao;

    @RequestMapping(value="/users", method=RequestMethod.GET)
    @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
    @ResponseBody
    User getUserByIdOrUserName(@PathVariable("id") String idOrUserName) {
        //return userService.findByIdOrUserName(idOrUserName);
    	User user = userService.findByIdOrUserName(idOrUserName);
    	
    	if (user == null) {
    		throw new UserNotFoundException("idOrUserName-" + idOrUserName);
    	}
    	
    	return user;
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    @ResponseBody
    User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request.getUserName());
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    @ResponseBody
    User updateUser(@PathVariable("id") Long id, @RequestBody CreateUserRequest request) {
        return userService.updateUser(id, request.getUserName());
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    void deleteUserById(@PathVariable("id") Long id, @RequestBody CreateUserRequest request) {
    	User user = userService.deleteUserById(id);
    	
    	if (user == null) {
    		throw new UserNotFoundException("id-" + id);
    	}
    }
    
    @RequestMapping(value="/users", method=RequestMethod.DELETE)
    @ResponseBody
    void deleteAllUsers() {
    	userService.deleteAllUsers();  	
    	
    }
    
    @RequestMapping(value="/users/{id}/tasks", method=RequestMethod.POST)
    @ResponseBody
    Task createTask(@PathVariable("id") String idOrUserName, @RequestBody Task task) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        task.setUser(user);
        taskDao.save(task);
        
        return task;
    }
    

    @RequestMapping(value="/users/{id}/tasks", method=RequestMethod.GET)
    @ResponseBody
    List<Task> getAllTasks(@PathVariable("id") String idOrUserName) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        return user.getTasks();
    }
    
    @RequestMapping(value="/users/{id}/completetasks", method=RequestMethod.GET)
    @ResponseBody
    List<Task> getCompleteTasks(@PathVariable("id") String idOrUserName) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        List<Task> completeTasks = new ArrayList<>();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
			if (task.getStatus() == TaskStatus.COMPLETE) {
				completeTasks.add(task);
			}
		}

        return completeTasks;
    }
    
    @RequestMapping(value="/users/{id}/notcompletetasks", method=RequestMethod.GET)
    @ResponseBody
    List<Task> getNotCompleteTasks(@PathVariable("id") String idOrUserName) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        List<Task> completeTasks = new ArrayList<>();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
			if (task.getStatus() != TaskStatus.COMPLETE) {
				completeTasks.add(task);
			}
		}

        return completeTasks;
    }
    
    @RequestMapping(value="/users/{id}/inprogresstasks", method=RequestMethod.GET)
    @ResponseBody
    List<Task> getInProgressTasks(@PathVariable("id") String idOrUserName) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        List<Task> completeTasks = new ArrayList<>();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
			if (task.getStatus() == TaskStatus.IN_PROGRESS) {
				completeTasks.add(task);
			}
		}

        return completeTasks;
    }

    @RequestMapping(value="/users/{id}/notstartedtasks", method=RequestMethod.GET)
    @ResponseBody
    List<Task> getNotStartedTasks(@PathVariable("id") String idOrUserName) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        List<Task> completeTasks = new ArrayList<>();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
			if (task.getStatus() == TaskStatus.NOT_STARTED) {
				completeTasks.add(task);
			}
		}

        return completeTasks;
    }
    
    
    @RequestMapping(value="/users/{id}/tasks/{taskid}", method=RequestMethod.PUT)
    @ResponseBody
    Task updateTask(@PathVariable("id") String idOrUserName, @PathVariable("taskid") Long taskid, @RequestBody Task task) {
    	User user = userService.findByIdOrUserName(idOrUserName);
        if (user == null) {
        	throw new UserNotFoundException("id-" + idOrUserName);
        }
        
        Task matchedTask = null;
        List<Task> tasks = user.getTasks();
        Iterator<Task> itor = tasks.iterator();
        while (itor.hasNext()) {
        	Task myTask = itor.next();
        	if (myTask.getId() == taskid.longValue()) {
        		matchedTask = myTask;
        		break;
        	}
        }
        
        if (matchedTask != null) {
        	matchedTask.setTaskName(task.getTaskName());
        	matchedTask.setDesc(task.getDesc());
        	matchedTask.setStatus(task.getStatus());
        	taskDao.save(matchedTask);
        } else {
        	throw new TaskNotFoundException("taskid-" + taskid);
        }
        
        return matchedTask;
    }
    
    /*
    @RequestMapping(value="/todolists", method=RequestMethod.GET)
    @ResponseBody
    Iterable<ToDoList> getAllTodoLists() {
        return todoListService.findAll();
    }
    
    @RequestMapping(value="/todolists/{id}", method=RequestMethod.GET)
    @ResponseBody
    Iterable<Task> getTodoLists(@PathVariable("id") Long id) {
        ToDoList todoList = todoListService.findById(id);
        
        return todoList.getTasks();
    }
    
    @RequestMapping(value="/todolists", method=RequestMethod.DELETE)
    @ResponseBody
    void deleteAllTodoLists() {
    	todoListService.deleteAllTodoLists();  	
    	
    }
   
    @RequestMapping(value="/todolists/{id}/tasks/{ids}", method=RequestMethod.POST)
    @ResponseBody
    ToDoList addTasks(@PathVariable Long listId, @PathVariable List<Long> taskIds) {
    	ToDoList todoList = todoListService.findById(listId);
    	
    	if (todoList != null) {
    		Set<Task> tasks = new HashSet<>();
    		for (Long taskId: taskIds) {
    			Task task = taskService.findById(taskId);
    			if (task!= null) {
    				tasks.add(task);
    			}
    		}
    		
    		if (!tasks.isEmpty()) {
    			todoList.setTasks(tasks);
    		}
    	}
       
    	return todoList;
    }
    */
}