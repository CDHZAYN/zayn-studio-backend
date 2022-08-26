package com.zaynStudio.forum.service;


import com.zaynStudio.forum.util.ErrorUtil;
import com.zaynStudio.forum.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaynStudio.forum.dao.UserDao;
import com.zaynStudio.forum.model.vo.UserVO;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {


    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Object getUserProfile(String userKey) {
        BigDecimal userID = hasUser(userKey);
        if (userID == null)
            return new ErrorUtil("00001", "No match user...");
        else if (userID.equals(-1))
            return new ErrorUtil("00002","please check format...");
        return userDao.selectUserProfile(userID);
    }

    public Object userLogin(String userKey, String userPSW) {
        BigDecimal userID = hasUser(userKey);
        if (userID == null)
            return new ErrorUtil("00001", "No match user...");
        else if (userID.equals(-1))
            return new ErrorUtil("00002","Format unacceptable...");
        userDao.userLogin(userID, userPSW);
        return "login success!";
    }

    public Object userRegister(UserVO userVO, String userPSW) {
        BigDecimal has = hasUser(userVO.getUserName());
        if(has!=null&&!has.equals(-1)){
            return new ErrorUtil("00003","User name exists...");
        }
        has=hasUser(userVO.getEmail());
        if(has!=null&&!has.equals(-1)){
            return new ErrorUtil("00003","User name exists...");
        }
        userDao.insertUser(TransformUtil.userVOtoPO(userVO, userPSW));
        return "Register success!";
    }

    private BigDecimal hasUser(String userKey) {
        if (verifyEmail(userKey)) {
            return userDao.selectUserIDByEmail(userKey);
        } else if (verifyName(userKey)) {
            return userDao.selectUserIDByName(userKey);
        }
        return BigDecimal.valueOf(-1);
    }

    private boolean verifyEmail(String userKey) {
        if (userKey == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(userKey);
        return m.matches();
    }

    private boolean verifyName(String userKey) {
        if (userKey.length() > 10)
            return false;
        String tmp = userKey;
        tmp = tmp.replaceAll("\\p{P}", "");
        if (userKey.length() != tmp.length())
            return false;
        return true;
    }
}
