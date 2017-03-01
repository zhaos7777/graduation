package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.NewsDao;
import com.stusystem.dao.TopicDao;
import com.stusystem.entity.Topic;
/**
 * 新闻主题的业务层
 * @author Think
 *
 */
public class TopicService {
	private TopicDao dao =new TopicDao();

	public int addTopic(Topic t){
		int result=0;
		Topic tt=dao.selTopicByName(t.getTopicname());
		if (tt==null) {
			result=dao.insertTopic(t);
		}else {
			result=-1;
		}
		return result;
	}
	
	public int editTopic(Topic t){
		int result=0;
		Topic tt=dao.selTopicByName(t.getTopicname());
		if (tt==null) {
			result=dao.updateTopic(t);
		}else {
			if (tt.getTopicid()==t.getTopicid()) {
				result=dao.updateTopic(tt);
			}else {
				result=-1;
			}
		}
		return result;
	}
	
	public List<Topic> getAllTopics(){
		return dao.selAllTopics();
	}
	
	public List<Topic> getTopicByPage(int pageindex,int pagesize){
		return dao.selTopicByPage(pageindex, pagesize);
	}
	
	public int dropTopicById(int id){
		NewsDao ndao=new NewsDao();
		int count=ndao.selNewsCountByTopic(id);
		if (count==0) {
			return dao.delTopicById(id);
		}else{
			return -1;
		}
		
	}
	
	public Topic getTopicsById(int id){
		return dao.selTopicById(id);
	}
	
	public int getTopicCount(){
		int count=dao.selTopicCount();
		return count;
	}
	
	public int getPageCount(int pagesize){
		int pagecount=0;
		int rscount=dao.selTopicCount();
		if (rscount%pagesize==0) {
			pagecount=rscount/pagesize;
		}else {
			pagecount=rscount/pagesize+1;
		}
		return pagecount;
	}
}
