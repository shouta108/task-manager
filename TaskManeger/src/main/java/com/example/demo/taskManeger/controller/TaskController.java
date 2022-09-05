package com.example.demo.taskManeger.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.form.TaskForm;
import com.example.demo.taskManeger.repository.TaskRepository;
import com.example.demo.taskManeger.service.TaskServiceImpl;

@Controller
public class TaskController {
	
	@Autowired
	TaskRepository repository;
	@Autowired
	TaskServiceImpl service;
	
	@GetMapping("show")
	public String showview(@ModelAttribute TaskManeger task, Model model) {
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	@PostMapping("revise")
	public String showrevice(@ModelAttribute TaskManeger task, Model model, @RequestParam("id") String id) {
		model.addAttribute("reviseTask", service.selectOneById(Integer.parseInt(id)));
		return "revise";
	}
	
	@PostMapping("confirm")
	public String confirm(TaskForm f ,@ModelAttribute TaskManeger task, Model model) {
		TaskManeger revisedTask = new TaskManeger(f.getId(), f.getTask(), f.getDate(), f.getSort(), f.getCompletion(), f.getRegistrationDate());
		task = repository.save(revisedTask);
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	@PostMapping("register")
	public String register(TaskForm f,@ModelAttribute TaskManeger taskEntity, Model model) {
		Calendar cal = Calendar.getInstance();
		TaskManeger task = new TaskManeger(null, f.getTask(), f.getDate(), f.getSort(), false, new java.sql.Date(cal.getTimeInMillis()));
		task = repository.save(task);
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	@PostMapping("delete")
	public String deleteTask(@RequestParam("id") String id, Model model) {
		repository.deleteById(Integer.parseInt(id));
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	@PostMapping("completion")
	public String completion(TaskForm f, @RequestParam("id") String id, Model model) {
		Iterable<TaskManeger> tasks = service.sortDate();
		for (TaskManeger task : tasks) {
			int taskId = task.getId();
			if (taskId == Integer.parseInt(id) && task.getCompletion() == false) {
				task.setCompletion(true);
				repository.save(task);
				model.addAttribute("taskList", service.selectAll());
			} else if (taskId == Integer.parseInt(id) && task.getCompletion() == true) {
				task.setCompletion(false);
				repository.save(task);
				model.addAttribute("taskList", service.selectAll());
			}
		}
		return "view";
	}
	
	@PostMapping("config")
	public String config() {
		return "config";
	}
}
