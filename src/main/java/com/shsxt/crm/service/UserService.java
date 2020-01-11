package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import com.shsxt.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    UserMapper userMapper;

    public UserModel userLogin(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空");
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user == null, "用户不存在或已经注销");
        return checkPwd(userPwd, user);
    }

    private UserModel checkPwd(String userPwd, User user) {
        String pwd = user.getUserPwd();
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!pwd.equals(userPwd), "密码错误");
        return new UserModel(UserIDBase64.encoderUserID(user.getId()), user.getUserName(), user.getTrueName());
    }

    public void updatePwd(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        checkuserPwd(request, oldPassword, newPassword, confirmPassword);
        User user = new User();
        user.setId(LoginUserUtil.releaseUserIdFromCookie(request));
        user.setUserPwd(Md5Util.encode(newPassword));
        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "密码更新失败!");
    }



    private void checkuserPwd(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "原来密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空");
        AssertUtil.isTrue(oldPassword.equals(newPassword), "新旧密码不能一致");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "确认密码不能为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword), "两次密码输入的不一致");
    }


}
