package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.MessageDao;
import com.stusystem.entity.Class;
import com.stusystem.entity.Message;

public class MessageService {
     private MessageDao dao = new MessageDao();
     
     public int addMessage(Message message){
    	 int result=0;
    	 Message mes=dao.selMesByTile(message.getTitle());
 		if (mes==null ) {
 			result=dao.insertMessage(message);
 		}else {
 			result=-1;
 		}
 		return result;
     }

     public int dropMessage(int id){
    	 return dao.delMessage(id);
     }
     
     public int editMessage(Message message){
    	 int result=0;
    	 Message mes=dao.selMesByTile(message.getTitle());
 		if (mes==null ) {
 			result=dao.updateMessage(message);
 		}else {
 			if (mes.getId()==message.getId()) {
				result=dao.updateMessage(message);
			}
 			else {
 				result=-1;
			}
 		}
 		return result;
     }
     
     public Message getMessageById(int id){
    	 return dao.selMesById(id);
     }
     
     public List<Message> getAllMessageByPage(int pageindex,int pagesize){
    	 return dao.selAllMesByPage(pageindex, pagesize);
     }
     
     public int getCount(){
    	 return dao.selCount();
     }
     
     public int getPageCount(int pagesize) {
 		int pagecount = 0;
 		int rscount = dao.selCount();
 		if (rscount % pagesize == 0) {
 			pagecount = rscount / pagesize;
 		} else {
 			pagecount = rscount / pagesize + 1;
 		}
 		return pagecount;
 	}
}
