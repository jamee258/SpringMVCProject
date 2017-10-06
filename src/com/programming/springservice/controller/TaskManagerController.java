package com.programming.springservice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programming.dao.TaskManagerService;
import com.programming.springservice.domain.Task;

@RestController
public class TaskManagerController {

	TaskManagerService taskmanagerservice = new TaskManagerService();

	@RequestMapping(value = "/tasks", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Task> getAllTasks() {
		List<Task> tasks = taskmanagerservice.getAllTasks();
		return tasks;

	}

	@RequestMapping(value = "/tasks/archive/{projectId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Task> archiveAllTasks(@PathVariable int[] taskIds) {
		for (int i = 0; i < taskIds.length; i++) {
			taskmanagerservice.archiveTask(taskIds[i]);

		}
		List<Task> tasks = taskmanagerservice.getAllTasks();
		return tasks;

	}

	@RequestMapping(value = "/tasks/{projectId}/{projectStatus}", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Task> changeTaskStatus(@PathVariable int taskId, @PathVariable String taskStatus)
			throws ParseException {
		taskmanagerservice.changeTaskStatus(taskId, taskStatus);
		return taskmanagerservice.getAllTasks();

	}

	@RequestMapping(value = "/tasks/insert/{projectName}/{projectDesc}/{projectPriority}/{projectStatus}", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Task> addTask(@PathVariable String projectName, @PathVariable String projectDesc,
			@PathVariable String projectPriority, @PathVariable String projectStatus) throws ParseException {
		Task task = new Task();
		task.setProject_name(projectName);
		task.setProject_description(projectDesc);
		task.setProject_priority(projectPriority);
		task.setProject_status(projectStatus);
		taskmanagerservice.addTask(task);
		return taskmanagerservice.getAllTasks();

	}
}