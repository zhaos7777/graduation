package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.News;
import com.stusystem.entity.Topic;

public class NewsDao extends BaseDao {

	public int insertNews(News news) {
		String sql = "insert into stunews(title,topicid,author,content,addtime)values(?,?,?,?,now())";
		Object[] values = { news.getTitle(), news.getTopic().getTopicid(),
				news.getAuthor(), news.getContent() };
		int result = super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update newstopic set newsnum=newsnum+1 where topicid=?";
			Object[] values2={news.getTopic().getTopicid()};
			result=super.exeUpdate(sql, values2);
		}
		return result;
	}

	public int delNewsById(int id,int tid) {
		String sql = "delete from stunews where newsid=?";
		Object[] values = { id };
		int result = super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update newstopic set newsnum=newsnum-1 where topicid=?";
			Object[] values2={tid};
			result=super.exeUpdate(sql, values2);
		}
		return result;
	}

	public int updateNews(News news,int tid) {
		String sql = "update stunews set title=?,topicid=?,author=?,content=? where newsid=?";
		Object[] values = { news.getTitle(), news.getTopic().getTopicid(),
				news.getAuthor(), news.getContent(), news.getNewsid() };
		int result = super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update newstopic set newsnum=newsnum+1 where topicid=?";
			Object[] values2={news.getTopic().getTopicid()};
			result=super.exeUpdate(sql, values2);
			if (result!=0) {
				sql="update newstopic set newsnum=newsnum-1 where topicid=?";
				Object[] values3={tid};
				result=super.exeUpdate(sql, values3);
			}
		}
		return result;
	}

	public News selNewsByName(String title) {
		News news = null;
		String sql = "select * from stunews where title=?";
		Object[] values = { title };
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				news = new News();
				news.setTitle(rs.getString("title"));
				news.setNewsid(rs.getInt("newsid"));
				news.setAuthor(rs.getString("author"));
				news.setContent(rs.getString("content"));
				news.setAddtime(rs.getDate("addtime"));
				int tid = rs.getInt("topicid");
				TopicDao tdao = new TopicDao();
				Topic t = tdao.selTopicById(tid);
				news.setTopic(t);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return news;
	}

	public News selNewsById(int id) {
		String sql = "select * from stunews where newsid=?";
		News news = null;
		Object[] values = { id };
		ResultSet rs = super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				news = new News();
				news.setTitle(rs.getString("title"));
				news.setNewsid(rs.getInt("newsid"));
				news.setAuthor(rs.getString("author"));
				news.setContent(rs.getString("content"));
				news.setAddtime(rs.getDate("addtime"));
				int tid = rs.getInt("topicid");
				TopicDao tdao = new TopicDao();
				Topic t = tdao.selTopicById(tid);
				news.setTopic(t);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return news;
	}

	public int selNewsCountByTopic(int tid) {
		String sql = "select count(*) as num from stunews where topicid=?";
		Object[] values = { tid };
		ResultSet rs = super.exeQuery(sql, values);
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
	
	public int selNewsCount() {
		String sql = "select count(*) as num from stunews";
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

	
	
	public int selNewsCount(String key) {
		String sql = "select count(*) as num from stunews";
		List values=new ArrayList();
		if (key!=null &&!"".equals(key)) {
			sql=sql+" where title like ?";
			values.add("%"+key+"%");
		}
		ResultSet rs = super.exeQuery(sql, values.toArray());
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
	
	/**
	 * 数据库分页查询的方法
	 * @param pageindex 当前页数
	 * @param pagesize 每一页显示的数目
	 * @return  返回结果集合
	 */
	public List<News> selNewsByPage(int pageindex, int pagesize) {
		String sql = "select * from stunews order by newsid desc limit ?,?";
		Object[] values = { (pageindex - 1) * pagesize, pagesize };
		List<News> list = new ArrayList<News>();
		ResultSet rs = super.exeQuery(sql, values);
		try {
			while (rs.next()) {
				News news = new News();
				news.setAddtime(rs.getDate("addtime"));
				news.setAuthor(rs.getString("author"));
				news.setContent(rs.getString("content"));
				news.setNewsid(rs.getInt("newsid"));
				news.setTitle(rs.getString("title"));
				int tid = rs.getInt("topicid");
				TopicDao tdao = new TopicDao();
				Topic t = tdao.selTopicById(tid);
				news.setTopic(t);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}
	
	public List<News> selNewsByPage(int pageindex, int pagesize,String key) {
		String sql = "select * from stunews";
		List<Object> values=new ArrayList<Object>();
		if (key!=null && !"".equals(key)) {
			sql=sql+" where title like ?";
			values.add("%"+key+"%");
		}
		sql=sql+" order by newsid desc limit ?,?";
		values.add((pageindex-1) * pagesize);
		values.add(pagesize);
		List<News> list = new ArrayList<News>();
		ResultSet rs = super.exeQuery(sql, values.toArray());
		try {
			while (rs.next()) {
				News news = new News();
				news.setAddtime(rs.getDate("addtime"));
				news.setAuthor(rs.getString("author"));
				news.setContent(rs.getString("content"));
				news.setNewsid(rs.getInt("newsid"));
				news.setTitle(rs.getString("title"));
				int tid = rs.getInt("topicid");
				TopicDao tdao = new TopicDao();
				Topic t = tdao.selTopicById(tid);
				news.setTopic(t);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}
	
	public List<News> selNewsByPage(int pageindex, int pagesize,int tid) {
		String sql = "select * from stunews";
		List<Object> values=new ArrayList<Object>();
		if (tid!=0) {
			sql=sql+" where topicid= ?";
			values.add(tid);
		}
		sql=sql+" order by newsid desc limit ?,?";
		values.add((pageindex-1) * pagesize);
		values.add(pagesize);
		List<News> list = new ArrayList<News>();
		ResultSet rs = super.exeQuery(sql, values.toArray());
		try {
			while (rs.next()) {
				News news = new News();
				news.setAddtime(rs.getTimestamp("addtime"));
				news.setAuthor(rs.getString("author"));
				news.setContent(rs.getString("content"));
				news.setNewsid(rs.getInt("newsid"));
				news.setTitle(rs.getString("title"));
				int topicid = rs.getInt("topicid");
				TopicDao tdao = new TopicDao();
				Topic t = tdao.selTopicById(topicid);
				news.setTopic(t);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}

	public int selNewsCount(int tid) {
		String sql = "select count(*) as num from stunews";
		List values=new ArrayList();
		if (tid !=0) {
			sql=sql+" where topicid=?";
			values.add(tid);
		}
		ResultSet rs = super.exeQuery(sql, values.toArray());
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

	public List<News> selAllNews() {
		String sql = "select * from stunews order by newsid desc";
		List<News> list = new ArrayList<News>();
		ResultSet rs = super.exeQuery(sql, null);
		try {
			while (rs.next()) {
				News news = new News();
				news.setAddtime(rs.getDate("addtime"));
				news.setAuthor(rs.getString("author"));
				news.setContent(rs.getString("content"));
				news.setNewsid(rs.getInt("newsid"));
				news.setTitle(rs.getString("title"));
				int tid = rs.getInt("topicid");
				TopicDao tdao = new TopicDao();
				Topic t = tdao.selTopicById(tid);
				news.setTopic(t);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return list;
	}
}
