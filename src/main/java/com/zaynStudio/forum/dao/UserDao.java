package com.zaynStudio.forum.dao;

import com.zaynStudio.forum.model.po.UserPO;
import com.zaynStudio.forum.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Mapper
public interface UserDao {

    public BigDecimal selectUserIDByEmail(String userEmail);

    public BigDecimal selectUserIDByName(String userName);

    public UserPO selectUserProfile(BigDecimal userID);

    public void insertUser(UserPO userPO);

    public BigDecimal userLogin(BigDecimal userID, String userPSW);
}
