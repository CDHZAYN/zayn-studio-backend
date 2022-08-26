package com.zaynStudio.forum.util;

import com.zaynStudio.forum.model.po.UserPO;
import com.zaynStudio.forum.model.vo.UserVO;

import java.math.BigDecimal;

public class TransformUtil {

    public static UserVO userPOtoVO(UserPO userPO){
        UserVO userVO = new UserVO();
        userVO.setUserName(userPO.getUserName());
        userVO.setEmail(userPO.getEmail());
        userVO.setAvatar(userPO.getAvatar());
        userVO.setIntroduction(userPO.getIntroduction());
        userVO.setBlogNum(userPO.getBlogNum());
        String job="player";
        switch(userPO.getJob()){
            case 0:
                job="Player";
                break;
            case 1:
                job="Zayn";
                break;
            default:
                job="Aybm";
        }
        userVO.setJob(job);
        return userVO;
    }

    public static UserPO userVOtoPO(UserVO userVO, String userPSW){
        UserPO userPO = new UserPO();
        userPO.setUserName(userVO.getUserName());
        userPO.setEmail(userVO.getEmail());
        userPO.setPassword(userPSW);
        userPO.setAvatar(userVO.getAvatar());
        userPO.setIntroduction(userVO.getIntroduction());
        userPO.setBlogNum(BigDecimal.valueOf(0));
        userPO.setJob(0);
        return userPO;
    }
}
