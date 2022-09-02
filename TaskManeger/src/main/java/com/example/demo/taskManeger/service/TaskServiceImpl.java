package com.example.demo.taskManeger.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.taskManeger.comparator.TaskComparator;
import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.repository.TaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskRepository repository;
	
	@Override
	public void inertTask(TaskManeger task) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(task);
	}

	@Override
	public void updateTask(TaskManeger task) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(task);
	}

	@Override
	public void deleteTask(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(id);
	}

	@Override
	public void completeTask(TaskManeger task) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(task);
	}

	@Override
	public Iterable<TaskManeger> selectAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Optional<TaskManeger> selectOneById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findById(id);
	}

	@Override
	public Iterable<TaskManeger> sortDate() {
		// TODO 自動生成されたメソッド・スタブ
		Iterable<TaskManeger> tasks = selectAll();
		List<TaskManeger> sortTasks = new ArrayList<TaskManeger>();
		for (TaskManeger task : tasks) {
			sortTasks.add(task);
		}
		Collections.sort(sortTasks, new TaskComparator());
		tasks = sortTasks;
		return tasks;
	}
}
