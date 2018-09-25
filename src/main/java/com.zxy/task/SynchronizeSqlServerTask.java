package com.zxy.task;

import com.zxy.dao.MessageDao;
import com.zxy.model.UserData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

import java.sql.*;
import java.util.ArrayList;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 16:10 2018/9/25
 */

@Component
public class SynchronizeSqlServerTask {

    private final static String URL = "jdbc:jtds:sqlserver://192.168.1.2:1433;DatabaseName=gderp30";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static Connection conn = null;

    ArrayList<UserData> allData = new ArrayList<>();
    ArrayList<UserData> addData = new ArrayList<>();

    @Resource
    private MessageDao messageDao;

    // 每天凌晨0:00执行一次

    //    @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void synSqlServerRun() {
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            //2.获得数据库的连接
//            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
//            Statement stmt = conn.createStatement();
//            //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
//            ResultSet rs = stmt.executeQuery("select xs_dhd.dwmc_3,xs_dhd_a.fphm_,xs_dhd_a.xh_,xs_dhd_a.fgs_,convert(varchar(100),xs_dhd_a.fhrq_,20) as rq_,xs_dhd_a.pm_,xs_dhd_a.gg_,xs_dhd_a.sl_1,xs_dhd_a.sl_2,xs_dhd_a.dj_,xs_dhd_a.je_,xs_dhd_a.gfdb_,xs_dhd_a.invoice from sx_dhd_a left join xs_dhd on xs_dhd_a.fphm_ = xs_dhd.fphm_ and xs_dhd_a.fgs_ = xs_dhd.fgs_ order by xs_dhd_a.fhrq_");
//            classifyData(rs);
//            update();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private void update() {
        for (UserData data : addData) {
            messageDao.saveUserData(data.getFphm_(), data.getXh_(), data.getFgs_(), data.getFhrq_(), data.getDwmc_3(), data.getPm_(), data.getGg_(), data.getSl_1(), data.getSl_2(), data.getDj_(), data.getJe_(), data.getGfdb_());
        }
        allData.addAll(addData);
    }

    private void classifyData(ResultSet rs) throws Exception {
        if (rs.next()) {
            boolean addTag = true;
            UserData userData = new UserData();
            userData.setFphm_(rs.getString("fphm_"));
            userData.setXh_(rs.getInt("xh_"));
            userData.setFgs_(rs.getString("fgs_"));
            userData.setFhrq_(rs.getString("rq_"));
            userData.setDwmc_3(rs.getString("dwmc_3"));
            userData.setPm_(rs.getString("pm_"));
            userData.setGg_(rs.getString("gg_"));
            userData.setSl_1(rs.getBigDecimal("sl_1"));
            userData.setSl_2(rs.getBigDecimal("sl_2"));
            userData.setDj_(rs.getBigDecimal("dj_"));
            userData.setJe_(rs.getBigDecimal("je_"));
            userData.setGfdb_(rs.getString("gfdb_"));
            for (UserData data : allData) {
                if (data.getFphm_().equals(data.getFphm_())) {
                    if (userData.getXh_() == data.getXh_()) {
                        if (userData.getFgs_() == null) {
                            continue;
                        }
                        if (userData.getFgs_().equals(data.getFgs_())) {
                            addTag = false;
                            break;
                        }
                    }
                }
            }
            if (addTag == true) {
                addData.add(userData);
            }
        }
    }
}
