package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.Class;
import com.stusystem.entity.Profession;
import com.stusystem.entity.Sex;
import com.stusystem.entity.Student;

public class StudentDao extends BaseDao {

	public int insertStudent(Student student) {
		String sql = "insert into studentinfo(student_name,student_sexid,student_age,student_classid,student_proid,student_home,student_idcard)values(?,?,?,?,?,?,?)";
		Object[] values = { student.getStudent_name(),
				student.getSex().getSex_id(), student.getStudent_age(),
				student.getCla().getClass_id(),
				student.getProfession().getPro_id(), student.getStudent_home(),
				student.getStudent_idcard() };
		int result = super.exeUpdate(sql, values);
		if (result != 0) {
			sql = "update professioninfo set pro_studentnum=pro_studentnum+1 where pro_id=?";
			Object[] value2 = { student.getProfession().getPro_id() };
			result = super.exeUpdate(sql, value2);
			if (result != 0) {
				sql = "update classinfo set class_studentnum=class_studentnum+1 where class_id=?";
				Object[] value1 = { student.getCla().getClass_id() };
				result = super.exeUpdate(sql, value1);
			} else {
				result = 0;
			}
		} else {
			result = 0;
		}

		return result;
	}

	public int delstudent(int id, int pid, int cid) {
		String sql = "delete from studentinfo where student_id=?";
		Object[] values = { id };
		int result = super.exeUpdate(sql, values);
		if (result != 0) {
			sql = "update professioninfo set pro_studentnum=pro_studentnum-1 where pro_id=?";
			Object[] value2 = { pid };
			result = super.exeUpdate(sql, value2);
			if (result != 0) {
				sql = "update classinfo set class_studentnum=class_studentnum-1 where class_id=?";
				Object[] value1 = { cid };
				result = super.exeUpdate(sql, value1);
			} else {
				result = 0;
			}
		} else {
			result = 0;
		}
		return result;
	}

	public int updatestudent(Student student, int pid, int cid) {
		String sql = "update studentinfo set student_name=?,student_sexid=?,student_age=?,student_classid=?,student_proid=?,student_home=?,student_idcard=? where student_id=?";
		Object[] values = { student.getStudent_name(),
				student.getSex().getSex_id(), student.getStudent_age(),
				student.getCla().getClass_id(),
				student.getProfession().getPro_id(), student.getStudent_home(),
				student.getStudent_idcard(),student.getStudent_id() };
		int result = super.exeUpdate(sql, values);
		if (result != 0) {
			sql = "update professioninfo set pro_studentnum=pro_studentnum+1 where pro_id=?";
			Object[] value2 = { student.getProfession().getPro_id() };
			result = super.exeUpdate(sql, value2);
			if (result != 0) {
				sql = "update classinfo set class_studentnum=class_studentnum+1 where class_id=?";
				Object[] value1 = { student.getCla().getClass_id() };
				result = super.exeUpdate(sql, value1);
				if (result != 0) {
					sql = "update classinfo set class_studentnum=class_studentnum-1 where class_id=?";
					Object[] value3 = { cid };
					result = super.exeUpdate(sql, value3);
					if (result != 0) {
						sql = "update professioninfo set pro_studentnum=pro_studentnum-1 where pro_id=?";
						Object[] value4 = { pid };
						result = super.exeUpdate(sql, value4);
					} else {
						result = 0;
					}
				} else {
					result = 0;
				}
			} else {
				result = 0;
			}
		} else {
			result = 0;
		}
		return result;
	}

	public Student selStudentByName(String name) {
		Student student = null;
		String sql = "select * from studentinfo where student_name=?";
		Object[] values = { name };
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_age(rs.getInt("student_age"));
				student.setStudent_home(rs.getString("student_home"));
				student.setStudent_idcard(rs.getString("student_idcard"));
				int cid = rs.getInt("student_classid");
				ClassDao cdao = new ClassDao();
				Class c = cdao.selClassById(cid);
				student.setCla(c);
				int pid = rs.getInt("student_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				student.setProfession(p);
				int sid = rs.getInt("student_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				student.setSex(s);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return student;
	}

	public Student selStudentById(int id) {
		Student student = null;
		String sql = "select * from studentinfo where student_id=?";
		Object[] values = { id };
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_age(rs.getInt("student_age"));
				student.setStudent_home(rs.getString("student_home"));
				student.setStudent_idcard(rs.getString("student_idcard"));
				int cid = rs.getInt("student_classid");
				ClassDao cdao = new ClassDao();
				Class c = cdao.selClassById(cid);
				student.setCla(c);
				int pid = rs.getInt("student_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				student.setProfession(p);
				int sid = rs.getInt("student_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				student.setSex(s);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return student;
	}
	
	public Student selStudentByIdcard(String id) {
		Student student = null;
		String sql = "select * from studentinfo where student_idcard=?";
		Object[] values = { id };
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_age(rs.getInt("student_age"));
				student.setStudent_home(rs.getString("student_home"));
				student.setStudent_idcard(rs.getString("student_idcard"));
				int cid = rs.getInt("student_classid");
				ClassDao cdao = new ClassDao();
				Class c = cdao.selClassById(cid);
				student.setCla(c);
				int pid = rs.getInt("student_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				student.setProfession(p);
				int sid = rs.getInt("student_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				student.setSex(s);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return student;
	}

	public List<Student> selAllStudent() {
		String sql = "select * from studentinfo order by student_id desc";
		List<Student> list = new ArrayList<Student>();
		ResultSet rs = super.exeQuery(sql, null);
		try {
			while (rs.next()) {
				Student student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_age(rs.getInt("student_age"));
				student.setStudent_idcard(rs.getString("student_idcard"));
				student.setStudent_home(rs.getString("student_home"));
				int cid = rs.getInt("student_classid");
				ClassDao cdao = new ClassDao();
				Class c = cdao.selClassById(cid);
				student.setCla(c);
				int pid = rs.getInt("student_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				student.setProfession(p);
				int sid = rs.getInt("student_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				student.setSex(s);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}
	
	public int selStudentCountByClass(int classid){
		int count=0;
		String sql="select count(*) as num from studentinfo where student_classid=?";
		Object[] values={classid};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return count;
	}

	public List<Student> selStudentByPage(int pageindex, int pagesize) {
		String sql = "select * from studentinfo order by student_id desc limit ?,?";
		Object[] values = { (pageindex - 1) * pagesize, pagesize };
		List<Student> list = new ArrayList<Student>();
		ResultSet rs = super.exeQuery(sql, values);
		;
		try {
			while (rs.next()) {
				Student student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_age(rs.getInt("student_age"));
				student.setStudent_idcard(rs.getString("student_idcard"));
				student.setStudent_home(rs.getString("student_home"));
				int cid = rs.getInt("student_classid");
				ClassDao cdao = new ClassDao();
				Class c = cdao.selClassById(cid);
				student.setCla(c);
				int pid = rs.getInt("student_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				student.setProfession(p);
				int sid = rs.getInt("student_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				student.setSex(s);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}
	
	public List<Student> selStudentByClaPage(int pageindex, int pagesize,int classid) {
		String sql = "select * from studentinfo where student_classid=? order by student_id desc limit ?,?";
		Object[] values = {classid,(pageindex - 1) * pagesize, pagesize };
		List<Student> list = new ArrayList<Student>();
		ResultSet rs = super.exeQuery(sql, values);
		;
		try {
			while (rs.next()) {
				Student student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_age(rs.getInt("student_age"));
				student.setStudent_idcard(rs.getString("student_idcard"));
				student.setStudent_home(rs.getString("student_home"));
				int cid = rs.getInt("student_classid");
				ClassDao cdao = new ClassDao();
				Class c = cdao.selClassById(cid);
				student.setCla(c);
				int pid = rs.getInt("student_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				student.setProfession(p);
				int sid = rs.getInt("student_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				student.setSex(s);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}

	public int selStudentCount() {
		String sql = "select count(*) as num from studentinfo";
		ResultSet rs = super.exeQuery(sql, null);
		int count = 0;
		try {
			if (rs.next()) {
				count = rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return count;
	}

}
