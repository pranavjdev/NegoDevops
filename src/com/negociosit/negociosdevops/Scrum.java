package com.negociosit.negociosdevops;

import java.io.Serializable;

public class Scrum  implements Serializable{

	
	/** The serialVersionUID is declared for keeping .*/
	private static final long serialVersionUID = 1L;

	private String taskName = null;
	
	@Override
	public String toString() {
		return "Scrum [taskName=" + taskName + ", status=" + status
				+ ", comment=" + comment + ", effortLabel=" + effortLabel
				+ ", index=" + index + "]";
	}

	private String status = null;
	
	private String comment  = null;
	
	private String effortLabel = null;
	
	private String index = null;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEffortLabel() {
		return effortLabel;
	}

	public void setEffortLabel(String effortLabel) {
		this.effortLabel = effortLabel;
	}
}
