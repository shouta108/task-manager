package com.example.demo.taskManeger.entity;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="task")
public class TaskManeger {
	@Id
	@Column(value ="id")
	private Integer id;
	//タスク名
	@Column(value ="task")
	private String task;
	//期日
	@Column(value ="date")
	private Date date;
	//分類
	@Column(value ="sort")
	private String sort;
	//完了
	@Column(value ="completion")
	private Boolean completion;
	//登録日
	@Column(value ="registrationDate")
	private Date registrationDate;
}
