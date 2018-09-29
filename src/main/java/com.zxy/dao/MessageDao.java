package com.zxy.dao;

import com.zxy.model.UserData;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 15:52 2018/9/7
 */
@MapperScan
public interface MessageDao {
    int saveUserData(@Param("fphm_") String fphm_, @Param("xh_") int xh_, @Param("fgs_") String fgs_, @Param("fhrq_") String fhrq_, @Param("dwmc_3") String dwmc_3, @Param("pm_") String pm_, @Param("gg_") String gg_, @Param("sl_1") BigDecimal sl_1, @Param("sl_2") BigDecimal sl_2, @Param("dj_") BigDecimal dj_, @Param("je_") BigDecimal je_, @Param("gfdb_") String gfdb_);

    int updateInvoice(@Param("id") int id, @Param("invoice") String invoice);

    ArrayList<UserData> getAllUserData(@Param("beginpage") int beginpage, @Param("endpage") int endpage);

    int getAllSize();

    ArrayList<UserData> getUserInfoWithNoInvoice(@Param("beginpage") int beginpage, @Param("endpage") int endpage);

    int getNoInvoiceSize();

    ArrayList<UserData> getUserInfoWithInvoice(@Param("beginpage") int beginpage, @Param("endpage") int endpage);

    int getInvoiceSize();

    ArrayList<UserData> getUserInfoWithKey(@Param("key") String key, @Param("beginpage") int beginpage, @Param("endpage") int endpage);

    int getSearchSize(String key);
}

