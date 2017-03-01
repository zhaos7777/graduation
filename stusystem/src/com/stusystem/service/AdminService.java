package com.stusystem.service;

import com.stusystem.dao.AdminDao;
import com.stusystem.entity.AdminInfo;

public class AdminService {
	AdminDao dao = new AdminDao();

	public AdminInfo getAdminByUsername(String username) {
		return dao.selAdminInfoByUsername(username);
	}

	public boolean login(AdminInfo admin) {
		AdminInfo myadmin = dao.selAdminInfoByUsername(admin.getUsername());
		if (myadmin != null) {
			if (myadmin.getPassword().equals(admin.getPassword()) && myadmin.getType().equals(admin.getType())) {
				return true;
			}
		}
		return false;
	}
}
