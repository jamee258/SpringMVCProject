package com.programming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.programming.springservice.domain.*;
import com.programming.springservice.utility.DBUtility;

public class TaskManagerService {
	private Connection connection;

	public TaskManagerService() {
		connection = DBUtility.getConnection();
	}

	public void addTask(Task task) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into task_list(project_name,project_description,estimated_cost,project_priority,project_status,start_date,estimated_completion_date, project_archived) values (?, ?, ?,?,?,?,?.?)");
			System.out.println("Task:" + task.getProject_name());
			preparedStatement.setString(1, task.getProject_name());
			preparedStatement.setString(2, task.getProject_description());
			preparedStatement.setInt(3, task.getEstimated_cost());
			preparedStatement.setString(4, task.getProject_priority());
			preparedStatement.setString(5, task.getProject_status());
			preparedStatement.setString(6, task.getStart_date());
			preparedStatement.setString(7, task.getEstimated_completion_date());
			preparedStatement.setInt(8, 0);
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			preparedStatement.setString(9, currentTime);
			preparedStatement.setString(10, currentTime);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void archiveTask(int projectId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update task_list set project_archived=true where project_id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, projectId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTask(Task task) throws ParseException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update task_list set project_name=?, project_description=?, estimated_cost=?, project_priority=?, project_status=?"
							+ "where task_id=?");
			preparedStatement.setString(1, task.getProject_name());
			preparedStatement.setString(2, task.getProject_description());
			preparedStatement.setInt(3, task.getEstimated_cost());
			preparedStatement.setString(4, task.getProject_priority());
			preparedStatement.setString(5, task.getProject_status());
			preparedStatement.setInt(7, task.getProject_id());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeTaskStatus(int projectId, String status) throws ParseException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update task_list set project_status=? where project_id=?");
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, projectId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<Task>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from task_list where project_archived=0");
			while (rs.next()) {
				Task task = new Task();
				task.setProject_id(rs.getInt("project_id"));
				task.setProject_name(rs.getString("project_name"));
				task.setProject_description(rs.getString("project_description"));
				task.setEstimated_cost(rs.getInt("estimated_cost"));
				task.setProject_priority(rs.getString("project_priority"));
				task.setProject_status(rs.getString("project_status"));
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public Task getTaskById(int projectId) {
		Task task = new Task();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from task_list where project_id=?");
			preparedStatement.setInt(1, projectId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				task.setProject_id(rs.getInt("project_id"));
				task.setProject_name(rs.getString("project_name"));
				task.setProject_description(rs.getString("project_description"));
				task.setProject_priority(rs.getString("project_priority"));
				task.setProject_status(rs.getString("project_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}
}