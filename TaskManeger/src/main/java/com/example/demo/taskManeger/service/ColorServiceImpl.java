package com.example.demo.taskManeger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.taskManeger.entity.ColorConfig;
import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.repository.ColorRepository;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	@Autowired
	ColorRepository repository;
	@Autowired
	TaskServiceImpl service;
	
	

	@Override
	public void updatePhase(ColorConfig conf) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(conf);
	}



	@Override
	public ColorConfig get() {
		// TODO 自動生成されたメソッド・スタブ
		Iterable<ColorConfig> config = repository.findAll();
		ColorConfig conf = null;
		for (ColorConfig con : config) {
			conf = con;
		}
		return conf;
	}



	@Override
	public List<String> setColor() {
		Iterable<TaskManeger> tasks = service.sortDate();
		List<String> colors = new ArrayList<String>();
		ColorConfig conf = get();
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
