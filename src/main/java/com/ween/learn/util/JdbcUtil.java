package com.ween.learn.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/3/13.
 */
public class JdbcUtil {
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";// oracle.jdbc.driver.OracleDriver,com.mysql.jdbc.Driver
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";// jdbc:oracle:thin:@localhost:1521:dbname,jdbc:mysql://localhost:3306/demo

    private static Connection connection;
    private static PreparedStatement pstmt;
    private static ResultSet resultSet;

    public JdbcUtil() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //增删改
    public boolean updateByPreparedStatement(String sql,List<Object> params) throws SQLException{
        boolean flag=false;
        int result=-1;
        connection.setAutoCommit(false);
        pstmt=connection.prepareStatement(sql);
        int index=1;
        if(!params.isEmpty()&&params!=null){
            for(int i=0;i<params.size();i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        result=pstmt.executeUpdate();
        connection.commit();
        flag=result>0?true:false;
        return flag;
    }
    //查询单条记录
    public Map<String, Object> findSingleResult(String sql, List<Object> params) throws SQLException{
        Map<String, Object> map = new HashMap<String, Object>();
        int index  = 1;
        pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i=0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();//返回查询结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while(resultSet.next()){
            for(int i=0; i<col_len; i++ ){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = resultSet.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
        }
        return map;
    }
    //查询多条记录
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while(resultSet.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            for(int i=0; i<cols_len; i++){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = resultSet.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }
    public static void main(String[] args) {
        JdbcUtil jdbcUtil=new JdbcUtil();
        String sql="select * from emp";
        try {
//            pstmt=jdbcUtil.getConnection().prepareStatement(sql);
//            pstmt.setInt(1, 7361);
//            resultSet=pstmt.executeQuery();
//            double sal=0;
//            while(resultSet.next()){
//                System.out.println(resultSet.getDouble(1));
//                sal=resultSet.getDouble(1);
//            }
//            System.out.println(sal);
            QueryRunner queryRunner=new QueryRunner();
            ResultSetHandler<Object> handler=new ScalarHandler<Object>();
            Object count=queryRunner.query(jdbcUtil.getConnection(),sql,handler);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            jdbcUtil.closeConnection();
        }

    }
}
