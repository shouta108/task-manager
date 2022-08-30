package com.example.demo.taskManeger.service;

import java.util.Optional;

import com.example.demo.taskManeger.entity.TaskManeger;

public interface TaskService {
	//タスクを全件取得
	Iterable<TaskManeger> selectAll();
	//タスクを、idをキーに1件取得
	Optional<TaskManeger> selectOneById(Integer id);
	//タスクを登録
	void inertTask(TaskManeger task);
	//タスクを更新
	void updateTask(TaskManeger task);
	//タスクを削除
	void deleteTask(Integer id);
	//タスクの完了
	void completeTask(TaskManeger task);
}
