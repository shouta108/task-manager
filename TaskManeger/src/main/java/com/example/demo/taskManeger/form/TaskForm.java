package com.example.demo.taskManeger.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TaskForm {
	private Integer id;
	//タスク名
	@NotBlank
	private String task;
	//期日
	private Date date;
	//分類
	private String sort;
	//完了
	private Boolean completion;
	//登録日
	private Date registrationDate;
}
