package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.SexDao;
import com.stusystem.entity.Sex;

public class SexService {
	private SexDao dao = new SexDao();

	public List<Sex> getAllSex() {
		return dao.selAllSex();
	}
}
