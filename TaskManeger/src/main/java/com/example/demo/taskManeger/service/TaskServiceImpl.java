package com.example.demo.taskManeger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
