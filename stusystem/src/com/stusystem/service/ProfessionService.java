package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.ClassDao;
import com.stusystem.dao.ProfessionDao;
import com.stusystem.entity.Profession;

public class ProfessionService {
	private ProfessionDao dao = new ProfessionDao();

	public int addProfession(Profession p) {
		int result = 0;
		if (dao.selProfessionByName(p.getPro_name()) == null
				&& dao.selProfessionByAbbName(p.getPro_abb()) == null) {
			result = dao.insertProfession(p);
		} else {
			result = -1;
		}
		return result;
	}

	public int editProfession(Profession p) {
		int result = 0;
		Profession pp = dao.selProfessionByAllName(p.getPro_name(), p.getPro_abb());
		if (pp==null) {
			result = dao.updateProfession(p);
		} else {
			if (pp.getPro_id()==p.getPro_id()) {
				result = dao.updateProfession(p);
			}
			else {
				result = -1;
			}
		}
		return result;
	}

	public int dropProfessionById(int id) {
		int result=0;
		ClassDao cdao=new ClassDao();
		int count=cdao.selClassCountByPro(id);
		if (count==0) {
			result= dao.delProfessionById(id);
		}else {
			result=-1;
		}
		return result;
	}

	public int getAllProfessionNum() {
		int rscount = dao.selProfessionCount();
		return rscount;
	}
	
	public int getAllClassNumByPro(int pid) {
		ClassDao cdao=new ClassDao();
		int rscount=cdao.selClassCountByPro(pid);
		return rscount;
	}

	public Profession getProfessionByid(int id) {
		return dao.selProfessionById(id);
	}

	public List<Profession> getAllProfession() {
		return dao.selAllProfession();
	}

	public List<Profession> getProfessionByPage(int pageindex, int pagesize) {
		return dao.selProfessionByPage(pageindex, pagesize);
	}

	public int getPageCount(int pagesize) {
		int pagecount = 0;
		int rscount = dao.selProfessionCount();
		if (rscount % pagesize == 0) {
			pagecount = rscount / pagesize;
		} else {
			pagecount = rscount / pagesize + 1;
		}
		return pagecount;
	}
	
}
