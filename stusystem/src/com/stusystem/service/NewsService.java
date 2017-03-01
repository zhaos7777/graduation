package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.NewsDao;
import com.stusystem.entity.News;
import com.stusystem.entity.Topic;

public class NewsService {
	private NewsDao dao=new NewsDao();

	public int addNews(News ns){
		int result=0;
		News news=dao.selNewsByName(ns.getTitle());
		if (news==null) {
			result=dao.insertNews(ns);
		}else {
			result=-1;
		}
		return result;
	}

	public List<News> getAllNews(){
		return dao.selAllNews();
	}
	/**
	 * ��ҳ��ȡ������Ϣ
	 * @param pageindex �ڼ�ҳ
	 * @param pagesize  ÿҳ����������
	 * @return  �������ż���
	 */
	public List<News> getNewsByPage(int pageindex,int pagesize){
		return dao.selNewsByPage(pageindex, pagesize);
	}
	
	public List<News> search(int pageindex,int pagesize,String key){
		return dao.selNewsByPage(pageindex, pagesize,key);
	}
	
	public List<News> search(int pageindex,int pagesize,int tid){
		return dao.selNewsByPage(pageindex, pagesize,tid);
	}

	public int dropNewsById(int id,int tid){
		return dao.delNewsById(id,tid);
	}

	public News getNewsById(int id){
		return dao.selNewsById(id);
	}
	
	public int getNewsByTopic(int tid){
		return dao.selNewsCountByTopic(tid);
	}

	public int editNews(News ns,int tid){
		int result=0;
		News news=dao.selNewsByName(ns.getTitle());
		if (news==null) {
			result=dao.updateNews(ns,tid);
		}
		else { 
			if (ns.getNewsid()==news.getNewsid()) {
				result=dao.updateNews(ns,tid);
			}else {
				result=-1;
			}
		}
		return result;
		
	}
	public int getNewsCount(){
		int count=dao.selNewsCount();
		return count;
	}
	
	public int getPageCount(int pagesize){
		int pagecount=0;
		int rscount=dao.selNewsCount();
		if (rscount%pagesize==0) {
			pagecount=rscount/pagesize;
		}else {
			pagecount=rscount/pagesize+1;
		}
		return pagecount;
	}
	
	public int getPageCount(int pagesize,String key){
		int pagecount=0;
		int rscount=dao.selNewsCount(key);
		if (rscount%pagesize==0) {
			pagecount=rscount/pagesize;
		}else {
			pagecount=rscount/pagesize+1;
		}
		return pagecount;
	}
	
	public int getPageCount(int pagesize,int tid){
		int pagecount=0;
		int rscount=dao.selNewsCount(tid);
		if (rscount%pagesize==0) {
			pagecount=rscount/pagesize;
		}else {
			pagecount=rscount/pagesize+1;
		}
		return pagecount;
	}
}
