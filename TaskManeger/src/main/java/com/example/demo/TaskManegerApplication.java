package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.repository.TaskRepository;

@SpringBootApplication
public class TaskManegerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManegerApplication.class, args).getBean(TaskManegerApplication.class).execute();
	}
	
	@Autowired
	TaskRepository repository;
	
	private void execute() {
		//テスト用のデータ削除する
		addTask();
		showList();
	}	
	
	private void showList() {
		Iterable<TaskManeger> tasks = repository.findAll();
		for (TaskManeger task : tasks) {
			System.out.println(task);
		}
	}
	
	private void addTask() {
		TaskManeger task1 = new TaskManeger(null, "テストjava", "2022-08-29", "テスト用",false);
		task1 = repository.save(task1);
	}
}
