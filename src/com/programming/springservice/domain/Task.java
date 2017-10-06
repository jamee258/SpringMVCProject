package com.programming.springservice.domain;

public class Task {

	private int project_id;
	private String project_name;
	private String project_description;
	private int estimated_cost;
	private String project_priority;
	private String project_status;
	private String start_date;
	private String estimated_completion_date;

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int projectId) {
		this.project_id = projectId;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String projectName) {
		this.project_name = projectName;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String projectDescription) {
		this.project_description = projectDescription;
	}

	public int getEstimated_cost() {
		return estimated_cost;
	}

	public void setEstimated_cost(int estimatedCost) {
		this.estimated_cost = estimatedCost;
	}

	public String getProject_priority() {
		return project_priority;
	}

	public void setProject_priority(String projectPriority) {
		this.project_priority = projectPriority;
	}

	public String getProject_status() {
		return project_status;
	}

	public void setProject_status(String projectStatus) {
		this.project_status = projectStatus;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String startDate) {
		this.start_date = startDate;
	}

	public String getEstimated_completion_date() {
		return estimated_completion_date;
	}

	public void setEstimated_completion_date(String estimatedCompletionDate) {
		this.estimated_completion_date = estimatedCompletionDate;
	}

	@Override
	public String toString() {
		return "Task [project_id=" + project_id + ", project_name=" + project_name + ", project_description="
				+ project_description + ", project_priority=" + project_priority + ",project_status=" + project_status
				+ ", start_date=" + start_date + ", estimated_completion_date=" + estimated_completion_date + "]";
	}

}
