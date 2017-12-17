package nice.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="todo_list")
public class ToDoList {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private long id;
	 private String name;
	 
	 @ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(name = "todolist_task", joinColumns = @JoinColumn(name = "todolist_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
	 private Set<Task> tasks;
	 
	 protected ToDoList() {}

	 public ToDoList(String name) {
	        this.name = name;
	    }
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tasks
	 */
	public Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ToDoList [id=" + id + ", name=" + name + ", tasks=" + tasks + "]";
	}

}
