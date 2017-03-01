package com.stusystem.dao;

import java.sql.*;


public class BaseDao {
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/stusys?useUnicode=true&characterEncoding=UTF-8";
    private String uid = "studb";
    private String pwd = "studb";
    private Connection conn=null;
    private PreparedStatement pstms=null;
    private ResultSet rs=null;
    
    public BaseDao(){
    	try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    
    public int exeUpdate(String sql,Object[] values){
    	int row = 0 ;
    	try {
			conn=DriverManager.getConnection(url,uid,pwd);
			pstms=conn.prepareStatement(sql);
			if (values!=null) {
				for (int i = 0; i < values.length; i++) {
					pstms.setObject(i+1, values[i]);
				}
			}
			row=pstms.executeUpdate();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally{
			closeAll();  //ʹ�ú�ر��ͷ���Դ
		}   	
    	return row;
    }
    
    public ResultSet exeQuery(String sql,Object[] values){
    	try {
    		conn=DriverManager.getConnection(url,uid,pwd);
			pstms=conn.prepareStatement(sql);
			if (values!=null) {
				for (int i = 0; i < values.length; i++) {
					pstms.setObject(i+1, values[i]);
				}
			}
			rs=pstms.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			// ���ر� �رվ��޷����ز�ѯ���  ʹ�������йر�
		}
       return rs;	
    }
    
    public void closeAll(){
    	if (pstms!=null) {
			try {
				pstms.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
    	
    	if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
    	
    	if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
    }
}
