package nice.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nice.enumeration.TaskStatus;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String taskName;
  
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

    /*
    @ManyToMany(mappedBy = "todo_list")
    private Set<ToDoList> todoLists;
    */
    
    private String desc;
    private TaskStatus status;
    
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	/**
	 * @return the todoLists
	 */
	/*
	public Set<ToDoList> getTodoLists() {
		return todoLists;
	}
	*/

	/**
	 * @param todoLists the todoLists to set
	 */
	/*
	public void setTodoLists(Set<ToDoList> todoLists) {
		this.todoLists = todoLists;
	}
	*/

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", desc=" + desc + ", status=" + status + "]";
	}
    

    
}
