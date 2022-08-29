package com.example.demo.taskManeger.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.taskManeger.entity.TaskManeger;

public interface TaskRepository extends CrudRepository<TaskManeger, Integer>{

}
