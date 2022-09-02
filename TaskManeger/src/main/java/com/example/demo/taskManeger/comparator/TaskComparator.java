package com.example.demo.taskManeger.comparator;

import java.sql.Date;
import java.util.Comparator;

import com.example.demo.taskManeger.entity.TaskManeger;

public class TaskComparator implements Comparator<TaskManeger>{

	@Override
	public int compare(TaskManeger o1, TaskManeger o2) {
		// TODO 自動生成されたメソッド・スタブ
		Date no1 = o1.getDate();
		Date no2 = o2.getDate();
		
		if (no1.after(no2)) {
			return 1;
		} else if ( no1 == no2) {
			return 0;
		} else {
			return -1;
		}
	}

}
