package adi.com.deloitte.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "taskinfo")
public class TaskInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String userName;
	
	private String taskName;

	@Size(min = 10, message = "Enter at least 10 Characters...")
	private String description;

	private Date targetDate;
	
	private String taskStatus;
	
	private String lastUpdatedDate;
	
	public TaskInfo(long id, String user, String taskName,
			String description, Date targetDate,
			String taskStatus, String lastUpdatedDate) {
		super();
		this.id = id;
		this.userName = user;
		this.taskName = taskName;
		this.description = description;
		this.targetDate = targetDate;
		this.taskStatus = taskStatus;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public TaskInfo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public String toString() {
		return "TaskInfo [id=" + id + ", userName=" + userName + ", taskName=" + taskName + ", description="
				+ description + ", targetDate=" + targetDate + ", taskStatus=" + taskStatus + ", lastUpdatedDate="
				+ lastUpdatedDate + "]";
	}
	
}