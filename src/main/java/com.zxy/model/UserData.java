package com.zxy.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 15:36 2018/9/7
 */
@Data
public class UserData {
    private int id;
    //销售单号
    private String fphm_;
    //旧数据库魔法数字
    private int xh_;
    //所属公司
    private String fgs_;
    //日期
    private Date fhrq_;
    //客户单位
    private String dwmc_3;
    //品名
    private String pm_;
    //规格
    private String gg_;
    //数量
    private BigDecimal sl_1;
    //重量
    private BigDecimal sl_2;
    //含税单价
    private BigDecimal dj_;
    //销售金额
    private BigDecimal je_;
    //业务员
    private String gfdb_;
    //开票单号
    private String invoice;

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", fphm_='" + fphm_ + '\'' +
                ", xh_=" + xh_ +
                ", fgs_='" + fgs_ + '\'' +
                ", fhrq_='" + fhrq_ + '\'' +
                ", dwmc_3='" + dwmc_3 + '\'' +
                ", pm_='" + pm_ + '\'' +
                ", gg_='" + gg_ + '\'' +
                ", sl_1=" + sl_1 +
                ", sl_2=" + sl_2 +
                ", dj_=" + dj_ +
                ", je_=" + je_ +
                ", gfdb_='" + gfdb_ + '\'' +
                ", invoice='" + invoice + '\'' +
                '}';
    }
}
