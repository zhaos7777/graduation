package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.Message;
import com.stusystem.entity.News;

public class MessageDao extends BaseDao {

	public int insertMessage(Message message){
		String sql = "insert into message(title,content,addtime)values(?,?,now())";
		Object[] values = { message.getTitle(),  message.getContent() };
		int result = super.exeUpdate(sql, values);		
		return result;
	}
	
	public int delMessage(int id){
		String sql = "delete from message where id=?";
		Object[] values = { id };
		int result = super.exeUpdate(sql, values);
		return result;
	}
	
	public int updateMessage(Message message){
		String sql = "update message set title=?,content=? where id=?";
		Object[] values = { message.getTitle(),  message.getContent() ,message.getId()};
		int result = super.exeUpdate(sql, values);
		return result;
	}
	
	public Message selMesById(int id){
		Message message=null;
		String sql = "select * from message where id=?";
		Object[] values = {id};
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				message = new Message();
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				message.setAddtime(rs.getDate("addtime"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return message;
	}
	
	public Message selMesByTile(String title){
		Message message=null;
		String sql = "select * from message where title=?";
		Object[] values = {title};
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				message = new Message();
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				message.setAddtime(rs.getDate("addtime"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return message;
	}
	
	public List<Message> selAllMesByPage(int pageindex, int pagesize){
		Message message=null;
		String sql = "select * from message order by id desc limit ?,?";
		Object[] values = { (pageindex - 1) * pagesize, pagesize };
		List<Message> list = new ArrayList<Message>();
		ResultSet rs = super.exeQuery(sql, values);
		try {
			while (rs.next()) {
				message = new Message();
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				message.setAddtime(rs.getDate("addtime"));
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return list;
	}
	
	public int selCount(){
		int count=0;
		String sql = "select count(*) as num from message";
		ResultSet rs = super.exeQuery(sql, null);
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
