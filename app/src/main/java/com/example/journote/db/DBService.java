package com.example.journote.db;

import com.example.journote.bean.JournoteBean;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public static DBService dbService = null;
    private DBService(){
    }
    public static DBService getDbService(){
        if (dbService == null){
            dbService = new DBService();
        }
        return dbService;
    }
    public  List<JournoteBean> getJournal(int authorId){
        //new Thread(){
            List<JournoteBean> journallist=  new ArrayList<JournoteBean>();
            String sql = "select * from journote where isjournal = 1 and authorid="+authorId;
            JournoteDBOpenHelper mHelper = new JournoteDBOpenHelper();
            Connection conn = mHelper.getConn();
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                if (conn != null&&(!conn.isClosed())){
                    ps = (PreparedStatement) conn.prepareStatement(sql);
                    if (ps != null){
                        rs = ps.executeQuery();
                        if(rs != null){
                            while(rs.next()){
                                JournoteBean journoteBean = new JournoteBean();
                                journoteBean.setTitle(rs.getString("title"));
                                journoteBean.setContent(rs.getString("content"));
                                journoteBean.setDate(rs.getString("date"));
                                journoteBean.setHasnoti(rs.getInt("hasnoti"));
                                journoteBean.setIsjournal(rs.getInt("isjournal"));
                                journoteBean.setLabel(rs.getInt("label"));
                                journoteBean.setTag(rs.getString("tag"));
                            }
                        }
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            JournoteDBOpenHelper.closeAll(conn, ps, rs);
            return journallist;
        //}.start();
    }
    public  List<JournoteBean> getNote(int authorid){
        //new Thread(){
        List<JournoteBean> journallist=  new ArrayList<JournoteBean>();
        String sql = "select * from journote where isjournal = 0 and authorid = "+authorid;
        JournoteDBOpenHelper mHelper = new JournoteDBOpenHelper();
        Connection conn = mHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (conn != null&&(!conn.isClosed())){
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null){
                    rs = ps.executeQuery();
                    if(rs != null){
                        while(rs.next()){
                            JournoteBean journoteBean = new JournoteBean();
                            journoteBean.setTitle(rs.getString("title"));
                            journoteBean.setContent(rs.getString("content"));
                            journoteBean.setDate(rs.getString("date"));
                            journoteBean.setHasnoti(rs.getInt("hasnoti"));
                            journoteBean.setIsjournal(rs.getInt("isjournal"));
                            journoteBean.setLabel(rs.getInt("label"));
                            journoteBean.setTag(rs.getString("tag"));
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        JournoteDBOpenHelper.closeAll(conn, ps, rs);
        return journallist;
        //}.start();
    }
    public  List<JournoteBean> getJournalByLabel(int labelSelect, int authorid){
        //new Thread(){
        List<JournoteBean> journallist=  new ArrayList<JournoteBean>();
        String sql = "select * from journote where isjournal = 1 and label ="+labelSelect+"and authorid="+authorid;
        JournoteDBOpenHelper mHelper = new JournoteDBOpenHelper();
        Connection conn = mHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (conn != null&&(!conn.isClosed())){
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null){
                    rs = ps.executeQuery();
                    if(rs != null){
                        while(rs.next()){
                            JournoteBean journoteBean = new JournoteBean();
                            journoteBean.setTitle(rs.getString("title"));
                            journoteBean.setContent(rs.getString("content"));
                            journoteBean.setDate(rs.getString("date"));
                            journoteBean.setHasnoti(rs.getInt("hasnoti"));
                            journoteBean.setIsjournal(rs.getInt("isjournal"));
                            journoteBean.setLabel(rs.getInt("label"));
                            journoteBean.setTag(rs.getString("tag"));
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        JournoteDBOpenHelper.closeAll(conn, ps, rs);
        return journallist;
        //}.start();
    }
    public  List<JournoteBean> getNoteByLabel(String labelSelect, int authorid){
        //new Thread(){
        List<JournoteBean> journallist=  new ArrayList<JournoteBean>();
        String sql = "select * from journote where isjournal = 0 and label ="+labelSelect+"and authorid="+authorid;
        JournoteDBOpenHelper mHelper = new JournoteDBOpenHelper();
        Connection conn = mHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (conn != null&&(!conn.isClosed())){
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null){
                    rs = ps.executeQuery();
                    if(rs != null){
                        while(rs.next()){
                            JournoteBean journoteBean = new JournoteBean();
                            journoteBean.setTitle(rs.getString("title"));
                            journoteBean.setContent(rs.getString("content"));
                            journoteBean.setDate(rs.getString("date"));
                            journoteBean.setHasnoti(rs.getInt("hasnoti"));
                            journoteBean.setIsjournal(rs.getInt("isjournal"));
                            journoteBean.setLabel(rs.getInt("label"));
                            journoteBean.setTag(rs.getString("tag"));
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        JournoteDBOpenHelper.closeAll(conn, ps, rs);
        return journallist;
        //}.start();
    }
    public  List<JournoteBean> getJournalHasNoti(int authorid){
        //new Thread(){
        List<JournoteBean> journallist=  new ArrayList<JournoteBean>();
        String sql = "select * from journote where isjournal = 1 and hasnoti = 1 and authorid="+authorid;
        JournoteDBOpenHelper mHelper = new JournoteDBOpenHelper();
        Connection conn = mHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (conn != null&&(!conn.isClosed())){
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null){
                    rs = ps.executeQuery();
                    if(rs != null){
                        while(rs.next()){
                            JournoteBean journoteBean = new JournoteBean();
                            journoteBean.setTitle(rs.getString("title"));
                            journoteBean.setContent(rs.getString("content"));
                            journoteBean.setDate(rs.getString("date"));
                            journoteBean.setHasnoti(rs.getInt("hasnoti"));
                            journoteBean.setIsjournal(rs.getInt("isjournal"));
                            journoteBean.setLabel(rs.getInt("label"));
                            journoteBean.setTag(rs.getString("tag"));
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        JournoteDBOpenHelper.closeAll(conn, ps, rs);
        return journallist;
        //}.start();
    }
    public  List<JournoteBean> getNoteHasNoti(int authorid){
        //new Thread(){
        List<JournoteBean> journallist=  new ArrayList<JournoteBean>();
        String sql = "select * from journote where isjournal = 0 and hasnoti = 1 and authorid ="+authorid;
        JournoteDBOpenHelper mHelper = new JournoteDBOpenHelper();
        Connection conn = mHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (conn != null&&(!conn.isClosed())){
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null){
                    rs = ps.executeQuery();
                    if(rs != null){
                        while(rs.next()){
                            JournoteBean journoteBean = new JournoteBean();
                            journoteBean.setTitle(rs.getString("title"));
                            journoteBean.setContent(rs.getString("content"));
                            journoteBean.setDate(rs.getString("date"));
                            journoteBean.setHasnoti(rs.getInt("hasnoti"));
                            journoteBean.setIsjournal(rs.getInt("isjournal"));
                            journoteBean.setLabel(rs.getInt("label"));
                            journoteBean.setTag(rs.getString("tag"));
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        JournoteDBOpenHelper.closeAll(conn, ps, rs);
        return journallist;
        //}.start();
    }



}
