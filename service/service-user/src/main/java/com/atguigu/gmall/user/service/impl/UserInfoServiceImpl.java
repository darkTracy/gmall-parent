package com.atguigu.gmall.user.service.impl;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.common.util.MD5;
import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.user.service.UserInfoService;
import com.atguigu.gmall.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
* @author 86182
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2022-09-06 21:18:33
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
implements UserInfoService{
    @Autowired(required = false)
    UserInfoMapper userInfoMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public LoginSuccessVo login(UserInfo info) {
        LoginSuccessVo vo = new LoginSuccessVo();
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getName,info.getName()).eq(UserInfo::getPasswd, MD5.encrypt(info.getPasswd()));
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        //登录成功
        if (userInfo!=null){
            //生成令牌
            String token = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(SysRedisConst.LOGIN_USER+token, Jsons.toStr(userInfo),7, TimeUnit.DAYS);
            vo.setToken(token);
            vo.setNickName(userInfo.getNickName());
            return vo;
        }
            return null;
    }
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
