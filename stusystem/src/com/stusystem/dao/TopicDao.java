package com.stusystem.dao;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stusystem.entity.News;
import com.stusystem.entity.Topic;
/**
 * ������������ݷ��ʲ�
 * @author Think
 *
 */
public class TopicDao extends BaseDao {
	/**
	 * ������������ӷ���
	 * @param t tΪTopic���ʵ��������name��id����
	 * @return ���ؽ��
	 */
	public int insertTopic(Topic t){
		int result=0;;
		String sql="insert into newstopic(topicname)values(?)";
		Object[] values={t.getTopicname()};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	/**
	 * ���������ɾ������
	 * @param id ����id����ɾ��
	 * @return ���ؽ��
	 */
	public int delTopicById(int id){
		int result=0;;
		String sql="delete from newstopic where topicid=?";
		Object[] values={id};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	/**
	 * ����������޸ķ���
	 * @param t Topic���ʵ�������� ��name��id����
	 * @return ���ؽ��
	 */
	public int updateTopic(Topic t){
		int result=0;;
		String sql="update newstopic set topicname=? where topicid=?";
		Object[] values={t.getTopicname(),t.getTopicid()};
		result=super.exeUpdate(sql, values);
		return result;
	}
	/**
	 * �������ֲ�
	 * @param name ���ݿ�topicname����
	 * @return �������ݿ⴫�ݵ�ֵ
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return t;
	}
	/**
	 *  ����ID��
	 * @param id ���ݿ�topicid����
	 * @return �������ݿ⴫�ݵ�ֵ
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return t;
	}
	/**
	 * �������ݿ������е�����
	 * @return
	 */
	public List<Topic> selAllTopics(){
		List<Topic> list = new ArrayList<Topic>();
		String sql="select * from newstopic order by topicid desc";
		ResultSet rs=super.exeQuery(sql, null);
		try {
			// while(rs.next()) ���ǽ�rsȫ�����ж�ȡ
			// if(rs.next())    ���ж�ȡһ�� �ж��Ƿ�������
			while(rs.next()) {
				Topic t= new Topic();
				t.setTopicid(rs.getInt("topicid"));
				t.setTopicname(rs.getString("topicname"));
				t.setNewsnum(rs.getInt("newsnum"));
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return count;
	}
}
