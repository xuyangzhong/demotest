package com.zxy.controller;

import com.zxy.dao.MessageDao;
import com.zxy.model.UserData;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 13:53 2018/9/7
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    public static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Resource
    private MessageDao messageDao;

    @RequestMapping(value = "/index")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    //模拟数据录入
    @RequestMapping(value = "/collect")
    public ModelAndView collect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jsonText = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"time\": \"2007-06-16\",\n" +
                "      \"username\": \"Betty\",\n" +
                "      \"name\": \"rnpoxwqil\",\n" +
                "      \"size\": \"blbt\",\n" +
                "      \"num\": 78,\n" +
                "      \"weight\": 6,\n" +
                "      \"priceWithTax\": 66,\n" +
                "      \"price\": 43,\n" +
                "      \"salesman\": \"Eric\",\n" +
                "      \"invoice\": \"ypriwftoel\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"time\": \"1988-06-18\",\n" +
                "      \"username\": \"Timothy\",\n" +
                "      \"name\": \"stniendi\",\n" +
                "      \"size\": \"k\",\n" +
                "      \"num\": 82,\n" +
                "      \"weight\": 67,\n" +
                "      \"priceWithTax\": 34,\n" +
                "      \"price\": 49,\n" +
                "      \"salesman\": \"Larry\",\n" +
                "      \"invoice\": \"hnodbogncy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"time\": \"2003-03-10\",\n" +
                "      \"username\": \"Jennifer\",\n" +
                "      \"name\": \"ogiitnsts\",\n" +
                "      \"size\": \"cad\",\n" +
                "      \"num\": 95,\n" +
                "      \"weight\": 64,\n" +
                "      \"priceWithTax\": 87,\n" +
                "      \"price\": 75,\n" +
                "      \"salesman\": \"Margaret\",\n" +
                "      \"invoice\": \"vgdnpykqeh\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"time\": \"1976-08-13\",\n" +
                "      \"username\": \"Thomas\",\n" +
                "      \"name\": \"yafjhdy\",\n" +
                "      \"size\": \"dg\",\n" +
                "      \"num\": 68,\n" +
                "      \"weight\": 32,\n" +
                "      \"priceWithTax\": 51,\n" +
                "      \"price\": 41,\n" +
                "      \"salesman\": \"Jessica\",\n" +
                "      \"invoice\": \"xndiess\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"time\": \"1980-05-09\",\n" +
                "      \"username\": \"Steven\",\n" +
                "      \"name\": \"mlzidwgikl\",\n" +
                "      \"size\": \"oh\",\n" +
                "      \"num\": 40,\n" +
                "      \"weight\": 54,\n" +
                "      \"priceWithTax\": 56,\n" +
                "      \"price\": 72,\n" +
                "      \"salesman\": \"Dorothy\",\n" +
                "      \"invoice\": \"lamedoy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"time\": \"2002-08-01\",\n" +
                "      \"username\": \"Melissa\",\n" +
                "      \"name\": \"kckiaecfl\",\n" +
                "      \"size\": \"jn\",\n" +
                "      \"num\": 27,\n" +
                "      \"weight\": 38,\n" +
                "      \"priceWithTax\": 79,\n" +
                "      \"price\": 23,\n" +
                "      \"salesman\": \"Maria\",\n" +
                "      \"invoice\": \"jdtmghtyy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"time\": \"1979-03-10\",\n" +
                "      \"username\": \"Donna\",\n" +
                "      \"name\": \"eiotxlkp\",\n" +
                "      \"size\": \"ic\",\n" +
                "      \"num\": 71,\n" +
                "      \"weight\": 17,\n" +
                "      \"priceWithTax\": 86,\n" +
                "      \"price\": 63,\n" +
                "      \"salesman\": \"Laura\",\n" +
                "      \"invoice\": \"oeoqoacg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"time\": \"1997-07-28\",\n" +
                "      \"username\": \"Timothy\",\n" +
                "      \"name\": \"esqbnojpcy\",\n" +
                "      \"size\": \"cbgi\",\n" +
                "      \"num\": 21,\n" +
                "      \"weight\": 2,\n" +
                "      \"priceWithTax\": 66,\n" +
                "      \"price\": 69,\n" +
                "      \"salesman\": \"Helen\",\n" +
                "      \"invoice\": \"maljqyrgx\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"time\": \"1977-07-11\",\n" +
                "      \"username\": \"Shirley\",\n" +
                "      \"name\": \"bfdiulx\",\n" +
                "      \"size\": \"scsx\",\n" +
                "      \"num\": 91,\n" +
                "      \"weight\": 91,\n" +
                "      \"priceWithTax\": 27,\n" +
                "      \"price\": 60,\n" +
                "      \"salesman\": \"Joseph\",\n" +
                "      \"invoice\": \"dbghgyq\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"time\": \"1998-12-16\",\n" +
                "      \"username\": \"Maria\",\n" +
                "      \"name\": \"mcyjdbjvz\",\n" +
                "      \"size\": \"xemy\",\n" +
                "      \"num\": 93,\n" +
                "      \"weight\": 54,\n" +
                "      \"priceWithTax\": 44,\n" +
                "      \"price\": 22,\n" +
                "      \"salesman\": \"Kevin\",\n" +
                "      \"invoice\": \"mqcrqmd\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11,\n" +
                "      \"time\": \"2001-01-05\",\n" +
                "      \"username\": \"Steven\",\n" +
                "      \"name\": \"skhbyb\",\n" +
                "      \"size\": \"rwbw\",\n" +
                "      \"num\": 82,\n" +
                "      \"weight\": 76,\n" +
                "      \"priceWithTax\": 45,\n" +
                "      \"price\": 95,\n" +
                "      \"salesman\": \"Lisa\",\n" +
                "      \"invoice\": \"lhdhopt\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 12,\n" +
                "      \"time\": \"2000-01-28\",\n" +
                "      \"username\": \"Margaret\",\n" +
                "      \"name\": \"qvkxhkplj\",\n" +
                "      \"size\": \"eix\",\n" +
                "      \"num\": 57,\n" +
                "      \"weight\": 96,\n" +
                "      \"priceWithTax\": 52,\n" +
                "      \"price\": 12,\n" +
                "      \"salesman\": \"George\",\n" +
                "      \"invoice\": \"jcfchshv\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 13,\n" +
                "      \"time\": \"1972-08-18\",\n" +
                "      \"username\": \"Jessica\",\n" +
                "      \"name\": \"mxquvuusr\",\n" +
                "      \"size\": \"lqo\",\n" +
                "      \"num\": 35,\n" +
                "      \"weight\": 18,\n" +
                "      \"priceWithTax\": 85,\n" +
                "      \"price\": 98,\n" +
                "      \"salesman\": \"Karen\",\n" +
                "      \"invoice\": \"vgyutuw\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 14,\n" +
                "      \"time\": \"2005-02-13\",\n" +
                "      \"username\": \"Carol\",\n" +
                "      \"name\": \"jxnqiet\",\n" +
                "      \"size\": \"dwxu\",\n" +
                "      \"num\": 71,\n" +
                "      \"weight\": 18,\n" +
                "      \"priceWithTax\": 89,\n" +
                "      \"price\": 76,\n" +
                "      \"salesman\": \"Donna\",\n" +
                "      \"invoice\": \"chjwwagofb\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 15,\n" +
                "      \"time\": \"2014-01-19\",\n" +
                "      \"username\": \"Elizabeth\",\n" +
                "      \"name\": \"pmxwxfrec\",\n" +
                "      \"size\": \"eeah\",\n" +
                "      \"num\": 91,\n" +
                "      \"weight\": 80,\n" +
                "      \"priceWithTax\": 26,\n" +
                "      \"price\": 72,\n" +
                "      \"salesman\": \"Angela\",\n" +
                "      \"invoice\": \"axitfovnwg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 16,\n" +
                "      \"time\": \"1994-08-06\",\n" +
                "      \"username\": \"Laura\",\n" +
                "      \"name\": \"qstgndywiy\",\n" +
                "      \"size\": \"oji\",\n" +
                "      \"num\": 82,\n" +
                "      \"weight\": 1,\n" +
                "      \"priceWithTax\": 26,\n" +
                "      \"price\": 57,\n" +
                "      \"salesman\": \"Angela\",\n" +
                "      \"invoice\": \"fjuptgvgb\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 17,\n" +
                "      \"time\": \"1971-09-13\",\n" +
                "      \"username\": \"Ronald\",\n" +
                "      \"name\": \"byjrhk\",\n" +
                "      \"size\": \"hyuv\",\n" +
                "      \"num\": 50,\n" +
                "      \"weight\": 88,\n" +
                "      \"priceWithTax\": 56,\n" +
                "      \"price\": 23,\n" +
                "      \"salesman\": \"Margaret\",\n" +
                "      \"invoice\": \"uurshyra\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 18,\n" +
                "      \"time\": \"2014-07-28\",\n" +
                "      \"username\": \"Joseph\",\n" +
                "      \"name\": \"ceaiwtwdn\",\n" +
                "      \"size\": \"jljth\",\n" +
                "      \"num\": 46,\n" +
                "      \"weight\": 71,\n" +
                "      \"priceWithTax\": 35,\n" +
                "      \"price\": 88,\n" +
                "      \"salesman\": \"Matthew\",\n" +
                "      \"invoice\": \"nusfhj\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 19,\n" +
                "      \"time\": \"1973-09-06\",\n" +
                "      \"username\": \"Michael\",\n" +
                "      \"name\": \"bioekkkzz\",\n" +
                "      \"size\": \"pbl\",\n" +
                "      \"num\": 30,\n" +
                "      \"weight\": 7,\n" +
                "      \"priceWithTax\": 58,\n" +
                "      \"price\": 83,\n" +
                "      \"salesman\": \"Barbara\",\n" +
                "      \"invoice\": \"empzqghvx\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 20,\n" +
                "      \"time\": \"1977-12-21\",\n" +
                "      \"username\": \"John\",\n" +
                "      \"name\": \"iohbnleb\",\n" +
                "      \"size\": \"tw\",\n" +
                "      \"num\": 40,\n" +
                "      \"weight\": 19,\n" +
                "      \"priceWithTax\": 57,\n" +
                "      \"price\": 83,\n" +
                "      \"salesman\": \"Matthew\",\n" +
                "      \"invoice\": \"uzieaahsp\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONArray jsonArray = JSONObject.fromObject(jsonText).getJSONArray("users");
        System.out.println(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            UserData userData = new UserData();

            String str = jsonObject.getString("time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = sdf.parse(str);

            userData.setTime(new Date(d.getTime()));
            userData.setUsername(jsonObject.getString("username"));
            userData.setName(jsonObject.getString("name"));
            userData.setSize(jsonObject.getString("size"));
            userData.setNum(jsonObject.getInt("num"));
            userData.setWeight(jsonObject.getDouble("weight"));
            userData.setPriceWithTax(jsonObject.getDouble("priceWithTax"));
            userData.setPrice(jsonObject.getDouble("price"));
            userData.setSalesman(jsonObject.getString("salesman"));
            userData.setInvoice(jsonObject.getString("invoice"));
            System.out.println(userData);
            messageDao.saveOrUpdateUserData(
                    userData.getTime(),
                    userData.getUsername(),
                    userData.getName(),
                    userData.getSize(),
                    userData.getNum(),
                    userData.getWeight(),
                    userData.getPriceWithTax(),
                    userData.getPrice(),
                    userData.getSalesman(),
                    userData.getInvoice());
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("collect");
        return mav;
    }
}
