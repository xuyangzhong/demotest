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
    public HashMap<String,ArrayList<UserData>> getAllUserInfo(HttpServletRequest request, HttpServletResponse response){
        ArrayList<UserData> userDatas;
        userDatas = messageDao.getAllUserData();
        HashMap<String,ArrayList<UserData>> answer = new HashMap<>();
        answer.put("users",userDatas);
        return answer;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        synchronizeSqlServerTask.synSqlServerRun();
        return "success";
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
