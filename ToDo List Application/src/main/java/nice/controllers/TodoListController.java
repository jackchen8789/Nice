package nice.controllers;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nice.models.Task;
import nice.models.ToDoList;
import nice.models.User;
import nice.services.TaskService;
import nice.services.TodoListService;

@Controller
@EnableAutoConfiguration
public class TodoListController {
	
	@Autowired
	private TodoListService todoListServvice;
	
	@Autowired
	private TaskService taskService;

    @RequestMapping(value="/todolists", method=RequestMethod.GET)
    @ResponseBody
    Iterable<ToDoList> getAllTodoLists() {
        return todoListServvice.findAll();
    }
    
    @RequestMapping(value="/todolists/{id}", method=RequestMethod.GET)
    @ResponseBody
    Iterable<Task> getTodoLists(@PathVariable("id") Long id) {
        ToDoList todoList = todoListServvice.findById(id);
        
        return todoList.getTasks();
    }
    
    @RequestMapping(value="/todolists", method=RequestMethod.DELETE)
    @ResponseBody
    void deleteAllUsers() {
    	todoListServvice.deleteAllTodoLists();  	
    	
    }
    
    @RequestMapping(value="/todolists", method=RequestMethod.POST)
    @ResponseBody
    ToDoList createTodoList(@RequestBody CreateTodoListRequest request) {
    	return todoListServvice.createTodoList(request.getTodoListName());
    }
   
    @RequestMapping(value="/todolists/{id}/tasks/{ids}", method=RequestMethod.POST)
    @ResponseBody
    ToDoList addTasks(@PathVariable Long listId, @PathVariable List<Long> taskIds) {
    	ToDoList todoList = todoListServvice.findById(listId);
    	
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
}
