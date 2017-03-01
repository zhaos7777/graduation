package com.stusystem.dao;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stusystem.entity.News;
import com.stusystem.entity.Topic;
/**
 * 新闻主题的数据访问层
 * @author Think
 *
 */
public class TopicDao extends BaseDao {
	/**
	 * 新闻主题的增加方法
	 * @param t t为Topic类的实例对象有name和id属性
	 * @return 返回结果
	 */
	public int insertTopic(Topic t){
		int result=0;;
		String sql="insert into newstopic(topicname)values(?)";
		Object[] values={t.getTopicname()};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	/**
	 * 新闻主题的删除方法
	 * @param id 根据id进行删除
	 * @return 返回结果
	 */
	public int delTopicById(int id){
		int result=0;;
		String sql="delete from newstopic where topicid=?";
		Object[] values={id};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	/**
	 * 新闻主题的修改方法
	 * @param t Topic类的实例化对象 有name和id属性
	 * @return 返回结果
	 */
	public int updateTopic(Topic t){
		int result=0;;
		String sql="update newstopic set topicname=? where topicid=?";
		Object[] values={t.getTopicname(),t.getTopicid()};
		result=super.exeUpdate(sql, values);
		return result;
	}
	/**
	 * 根据名字查
	 * @param name 数据库topicname属性
	 * @return 返回数据库传递的值
	 */
	public Topic selTopicByName(String name){
		Topic t = null;
		String sql="select * from newstopic where topicname=?";
		Object[] values={name};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				t= new Topic();
				t.setTopicid(rs.getInt("topicid"));
				t.setTopicname(rs.getString("topicname"));
				t.setNewsnum(rs.getInt("newsnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return t;
	}
	/**
	 *  根据ID查
	 * @param id 数据库topicid属性
	 * @return 返回数据库传递的值
	 */
	public Topic selTopicById(int id){
		Topic t = null;
		String sql="select * from newstopic where topicid=?";
		Object[] values={id};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				t= new Topic();
				t.setTopicid(rs.getInt("topicid"));
				t.setTopicname(rs.getString("topicname"));
				t.setNewsnum(rs.getInt("newsnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return t;
	}
	/**
	 * 查找数据库里所有的数据
	 * @return
	 */
	public List<Topic> selAllTopics(){
		List<Topic> list = new ArrayList<Topic>();
		String sql="select * from newstopic order by topicid desc";
		ResultSet rs=super.exeQuery(sql, null);
		try {
			// while(rs.next()) 就是将rs全部进行读取
			// if(rs.next())    进行读取一次 判断是否有数据
			while(rs.next()) {
				Topic t= new Topic();
				t.setTopicid(rs.getInt("topicid"));
				t.setTopicname(rs.getString("topicname"));
				t.setNewsnum(rs.getInt("newsnum"));
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public List<Topic> selTopicByPage(int pageindex, int pagesize) {
		String sql = "select * from newstopic order by topicid desc limit ?,?";
		Object[] values = { (pageindex - 1) * pagesize, pagesize };
		List<Topic> list = new ArrayList<Topic>();
		ResultSet rs = super.exeQuery(sql, values);
		try {
			while (rs.next()) {
				Topic t= new Topic();
				t.setTopicid(rs.getInt("topicid"));
				t.setTopicname(rs.getString("topicname"));
				t.setNewsnum(rs.getInt("newsnum"));
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}
	
	public int selTopicCount(){
		String sql="select count(*) as num from newstopic ";
		ResultSet rs=super.exeQuery(sql, null);
		int count=0;
		try {
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return count;
	}
}
