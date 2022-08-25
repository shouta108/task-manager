package com.example.demo.taskManeger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{

}
