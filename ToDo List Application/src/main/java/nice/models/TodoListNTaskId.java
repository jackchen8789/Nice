package nice.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TodoListNTaskId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "todolist_id")
	long todoListId;
	
	@Column(name = "task_id")
	long taskId;
	
	public TodoListNTaskId() {}
	
	public TodoListNTaskId(long todoListId, long taskId) {
		this.todoListId = todoListId;
		this.taskId = taskId;
	}

	/**
	 * @return the todoListId
	 */
	public long getTodoListId() {
		return todoListId;
	}

	/**
	 * @param todoListId the todoListId to set
	 */
	public void setTodoListId(long todoListId) {
		this.todoListId = todoListId;
	}

	/**
	 * @return the taskId
	 */
	public long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	
	
}
