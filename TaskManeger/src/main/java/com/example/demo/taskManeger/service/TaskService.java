package com.example.demo.taskManeger.service;

import com.example.demo.taskManeger.entity.Task;

public interface TaskService {
	//タスクを登録
	void inertTask(Task task);
	//タスクを更新
	void updateTask(Task task);
	//タスクを削除
	void deleteTask(Integer id);
	//タスクの完了
	void completeTask(Task task);
}
