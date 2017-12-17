package nice.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="todolist_task")
public class ToDoListNTask {
	
	@EmbeddedId
	private TodoListNTaskId key;
	
	public ToDoListNTask() {}

	public ToDoListNTask(TodoListNTaskId key) {
		this.key = key;
	}
	/**
	 * @return the todoListId
	 */
	public TodoListNTaskId getKey() {
		return key;
	}

	/**
	 * @param todoListId the todoListId to set
	 */
	public void setKey(TodoListNTaskId key) {
		this.key = key;
	}

	

	

}
