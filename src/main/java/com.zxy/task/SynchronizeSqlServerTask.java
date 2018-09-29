package com.zxy.task;

import com.zxy.dao.MessageDao;
import com.zxy.model.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 16:10 2018/9/25
 */

@Component
public class SynchronizeSqlServerTask {
    public static final Logger log = LoggerFactory.getLogger(SynchronizeSqlServerTask.class);

    private final static String URL = "jdbc:jtds:sqlserver://192.168.1.2:1433;DatabaseName=gderp30_new";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static Connection conn = null;
    private static Date preDate;

    static {
        try {
            preDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-09-20 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<UserData> allData = new ArrayList<>();
    ArrayList<UserData> addData = new ArrayList<>();

    @Resource
    private MessageDao messageDao;

    // 每天凌晨0:00执行一次

    //    @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public String synSqlServerRun() {
        String code = "";
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("update begin......");
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //2.获得数据库的连接
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
            ResultSet rs = stmt.executeQuery("select xs_dhd.dwmc_3,xs_dhd_a.fphm_,xs_dhd_a.xh_,xs_dhd_a.fgs_,xs_dhd_a.fhrq_ as rq_,xs_dhd_a.pm_,xs_dhd_a.gg_,xs_dhd_a.sl1_,xs_dhd_a.sl2_,xs_dhd_a.dj_,xs_dhd_a.je_,xs_dhd.gfdb_ from xs_dhd_a left join xs_dhd on xs_dhd_a.fphm_ = xs_dhd.fphm_ and xs_dhd_a.fgs_ = xs_dhd.fgs_ order by xs_dhd_a.fhrq_ desc");
            log.info("connect sqlserver successly...... classify begin......");
            classifyData(rs);
            log.info("classify successly......update begin......");
            update();
            log.info("update successly");
            code = "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update fail...",e);
            code  = "fail";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return code;
    }

    private void update() throws Exception {
        for (int i = addData.size() - 1; i >= 0; i--) {
            UserData data = addData.get(i);
            messageDao.saveUserData(data.getFphm_(), data.getXh_(), data.getFgs_(), data.getFhrq_(), data.getDwmc_3(), data.getPm_(), data.getGg_(), data.getSl_1(), data.getSl_2(), data.getDj_(), data.getJe_(), data.getGfdb_());
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getFhrq_());
            if (date != null) {
                log.info(String.format("update success! preDate changes from %s ti %s", preDate.getTime(), date.getTime()));
                preDate = date;
            }
        }
        allData.addAll(addData);
        addData.clear();
    }

    private void classifyData(ResultSet rs) throws Exception {
        boolean tag = false;
        Date date = null;
        while (rs.next()) {
            boolean addTag = true;
            UserData userData = new UserData();
            userData.setFphm_(rs.getString("fphm_"));
            userData.setXh_(rs.getInt("xh_"));
            userData.setFgs_(rs.getString("fgs_"));
            userData.setFhrq_(rs.getString("rq_"));
            userData.setDwmc_3(rs.getString("dwmc_3"));
            userData.setPm_(rs.getString("pm_"));
            userData.setGg_(rs.getString("gg_"));
            userData.setSl_1(rs.getBigDecimal("sl1_"));
            userData.setSl_2(rs.getBigDecimal("sl2_"));
            userData.setDj_(rs.getBigDecimal("dj_"));
            userData.setJe_(rs.getBigDecimal("je_"));
            userData.setGfdb_(rs.getString("gfdb_"));
            //记录第一个，即最新的时间（desc）
            if (tag == false) {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("rq_"));
                tag = true;
            }
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("rq_"));
            if (d.getTime() < preDate.getTime()) {
                break;
            }
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

//    public static void main(String[] args)throws Exception{
//        String time = "2017-10-19";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
//        Date utilDate = sdf.parse(time);
//        System.out.println(utilDate);//查看utilDate的值
//        Date date = new java.sql.Date(utilDate.getTime());
//        System.out.println(date);//查看date的值
//    }
}
