package com.zxy.controller;

import com.zxy.dao.MessageDao;
import com.zxy.model.UserData;
import com.zxy.task.SynchronizeSqlServerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

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

    @Resource
    private SynchronizeSqlServerTask synchronizeSqlServerTask;

    @RequestMapping(value = "/index")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value="/userinfo")
    @ResponseBody
    public HashMap<String,Object> getAllUserInfo(HttpServletRequest request, HttpServletResponse response){
        int currentpage = Integer.parseInt(request.getParameter("currentpage"));
        int beginpage = (currentpage-1)*20;
        int endpage = currentpage*20;
        ArrayList<UserData> userDatas;
        userDatas = messageDao.getAllUserData(beginpage,endpage);
        int totalsize = messageDao.getAllSize();
        HashMap<String,Object> answer = new HashMap<>();
        answer.put("users",userDatas);
        answer.put("totalsize",totalsize);
        return answer;
    }

    @RequestMapping(value="/userinfowithnoinvoice")
    @ResponseBody
    public HashMap<String,Object> getUserInfoWithNoInvoice(HttpServletRequest request, HttpServletResponse response){
        int currentpage = Integer.parseInt(request.getParameter("currentpage"));
        int beginpage = (currentpage-1)*20;
        int endpage = currentpage*20;
        ArrayList<UserData> userDatas;
        userDatas = messageDao.getUserInfoWithNoInvoice(beginpage,endpage);
        int totalsize = messageDao.getNoInvoiceSize();
        HashMap<String,Object> answer = new HashMap<>();
        answer.put("users",userDatas);
        answer.put("totalsize",totalsize);
        return answer;
    }

    @RequestMapping(value="/userinfowithinvoice")
    @ResponseBody
    public HashMap<String,Object> getUserInfoWithInvoice(HttpServletRequest request, HttpServletResponse response){
        int currentpage = Integer.parseInt(request.getParameter("currentpage"));
        int beginpage = (currentpage-1)*20;
        int endpage = currentpage*20;
        ArrayList<UserData> userDatas;
        userDatas = messageDao.getUserInfoWithInvoice(beginpage,endpage);
        int totalsize = messageDao.getInvoiceSize();
        HashMap<String,Object> answer = new HashMap<>();
        answer.put("users",userDatas);
        answer.put("totalsize",totalsize);
        return answer;
    }

    @RequestMapping(value="/userinfowithkey")
    @ResponseBody
    public HashMap<String,Object> getUserInfoWithKey(HttpServletRequest request, HttpServletResponse response){
        String key = request.getParameter("searchword");
        int currentpage = Integer.parseInt(request.getParameter("currentpage"));
        int beginpage = (currentpage-1)*20;
        int endpage = currentpage*20;
        ArrayList<UserData> userDatas;
        userDatas = messageDao.getUserInfoWithKey(key,beginpage,endpage);
        int totalsize = messageDao.getSearchSize(key);
        HashMap<String,Object> answer = new HashMap<>();
        answer.put("users",userDatas);
        answer.put("totalsize",totalsize);
        return answer;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = synchronizeSqlServerTask.synSqlServerRun();
        return code;
    }

    @RequestMapping(value = "/bill")
    @ResponseBody
    public String insertUserData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = java.net.URLDecoder.decode(request.getParameter("chosedId"),"UTF-8");
        String invoicenum = request.getParameter("invoicenum");
        String[] idStrs = idStr.substring(1,idStr.length()-1).split(",");
        ArrayList<Integer> ids = new ArrayList<>();
        for(String i : idStrs){
            ids.add(Integer.parseInt(i));
        }
        for(int id : ids){
            messageDao.updateInvoice(id,invoicenum);
            System.out.println(id);
        }
        return "";
    }
}
