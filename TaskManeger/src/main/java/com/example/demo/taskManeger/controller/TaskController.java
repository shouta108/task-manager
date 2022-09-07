package com.example.demo.taskManeger.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.taskManeger.entity.ColorConfig;
import com.example.demo.taskManeger.entity.Sort;
import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.form.ConfigForm;
import com.example.demo.taskManeger.form.SortForm;
import com.example.demo.taskManeger.form.TaskForm;
import com.example.demo.taskManeger.repository.ColorRepository;
import com.example.demo.taskManeger.repository.SortRepository;
import com.example.demo.taskManeger.repository.TaskRepository;
import com.example.demo.taskManeger.service.ColorServiceImpl;
import com.example.demo.taskManeger.service.SortServiceImpl;
import com.example.demo.taskManeger.service.TaskServiceImpl;


@Controller
public class TaskController {
	
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ColorRepository colorConf;
	@Autowired
	SortRepository sortRepository;
	
	@Autowired
	TaskServiceImpl service;
	@Autowired
	ColorServiceImpl config;
	@Autowired
	SortServiceImpl sort;
	
	@GetMapping("show")
	public String showview(Model model) {
		model.addAttribute("taskList", service.sortDate());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("colorList", setColor());
		return "view";
	}
	
	@PostMapping("register")
	public String register(TaskForm f, @ModelAttribute TaskManeger task, Model model) {
		Calendar cal = Calendar.getInstance();
		TaskManeger registerTask = new TaskManeger(null, f.getTask(), f.getDate(), f.getSort(), false, new java.sql.Date(cal.getTimeInMillis()));
		task = taskRepository.save(registerTask);
		model.addAttribute("colorList", setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}

	@PostMapping("revise")
	public String showrevice(@ModelAttribute TaskManeger task, Model model, @RequestParam("id") String id) {
		model.addAttribute("colorList", setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("reviseTask", service.selectOneById(Integer.parseInt(id)));
		return "revise";
	}
	
	@PostMapping("confirm")
	public String confirm(TaskForm f ,@ModelAttribute TaskManeger task, Model model) {
		TaskManeger revisedTask = new TaskManeger(f.getId(), f.getTask(), f.getDate(), f.getSort(), f.getCompletion(), f.getRegistrationDate());
		revisedTask = taskRepository.save(revisedTask);
		model.addAttribute("colorList", setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	
	@PostMapping("delete")
	public String deleteTask(@RequestParam("id") String id, Model model) {
		taskRepository.deleteById(Integer.parseInt(id));
		model.addAttribute("colorList", setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	@PostMapping("completion")
	public String completion(@RequestParam("id") String id, Model model) {
		Iterable<TaskManeger> tasks = service.sortDate();
		for (TaskManeger task : tasks) {
			int taskId = task.getId();
			if (taskId == Integer.parseInt(id) && task.getCompletion() == false) {
				task.setCompletion(true);
				taskRepository.save(task);
				model.addAttribute("colorList", setColor());
				model.addAttribute("sort", sort.selectAll());
				model.addAttribute("taskList", service.sortDate());
			} else if (taskId == Integer.parseInt(id) && task.getCompletion() == true) {
				task.setCompletion(false);
				taskRepository.save(task);
				model.addAttribute("colorList", setColor());
				model.addAttribute("sort", sort.selectAll());
				model.addAttribute("taskList", service.sortDate());
			}
		}
		return "view";
	}
	
	@PostMapping("config")
	public String config(Model model) {
		model.addAttribute("sort", sort.selectAll());
		return "config";
	}
	
	@PostMapping("sortRevise")
	public String sortRevise(SortForm f, Model model) {
		sort.changeSort(f.getId(), f.getSort());
		model.addAttribute("colorList", setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	public List<String> setColor() {
		Iterable<TaskManeger> tasks = service.sortDate();
		List<String> colors = new ArrayList<String>();
		ColorConfig conf = config.get();
		for (TaskManeger task : tasks) {
			//登録日
			Date registrationDate =  task.getRegistrationDate();
			//期日
			Date deadLine = task.getDate();
			//現在日時
			Date nowDate = new Date();
			//合計日数
			long count = (deadLine.getTime() - registrationDate.getTime()) / (24 * 60 * 60 * 1000);
			//経過日数
			long elapsed = (nowDate.getTime() - registrationDate.getTime()) / (24 * 60 * 60 * 1000);
			//残り日数
			long left = (deadLine.getTime() - nowDate.getTime()) / (24 * 60 * 60 * 1000);
			//残りの割合
			double leftRatio = (double)left / count * 100.0;
			//割合
			double ratio = 0;
			if (count != 0 && left != 0) {
				ratio = 100.0 - leftRatio;
			} else {
				ratio = 100.0;
			}
			//phase1
			double phase1 = conf.getPhase1();
			//phase2
			double phase2 = conf.getPhase2();
			
//			System.out.println("登録日: " + registrationDate);
//			System.out.println("期日: " + deadLine);
//			System.out.println("現在日時: " + nowDate);
//			System.out.println("日数: " + count);
//			System.out.println("経過日数: " + elapsed);
//			System.out.println("残り日数: " + left);
//			System.out.println("割合: " + ratio);
//			System.out.println("phase1: " + phase1);
//			System.out.println("phase2: " + phase2);
			if (elapsed > count) {
				colors.add("background-color: black;");
			} else if (ratio >= phase2) {
				colors.add("background-color:" + conf.getEndColor() + ";");
			} else if (ratio >= phase1) {
				colors.add("background-color:" + conf.getMiddleColor() + ";");
			} else {
				colors.add("background-color:" + conf.getStartColor() + ";");
			}
		}
		return colors;
	}
}
