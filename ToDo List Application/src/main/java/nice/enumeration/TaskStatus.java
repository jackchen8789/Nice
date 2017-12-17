/**
 * 
 */
package nice.enumeration;

/**
 * @author jackc
 *
 */
public enum TaskStatus {
	NOT_STARTED ("Not Started"),
	IN_PROGRESS ("In Progress"),
	COMPLETE ("Complete");

	private final String displayStr;
	
	TaskStatus(String displayStr) {
		this.displayStr = displayStr;
	}
	
	public String toString() {
		return (displayStr != null) ? displayStr : name();
	}
}
