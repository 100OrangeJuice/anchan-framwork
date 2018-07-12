package org.sakura.anchan.service;

import org.sakura.anchan.POJO.User;
import org.sakura.anchan.common.ServerResponse;

/**
 * 用户接口
 * Created by Anchan on 2017/12/1.
 */
public interface IUserService {
    /**登陆*/
    ServerResponse<User> login(String username, String password);
    /**注册*/
    ServerResponse<String> register(User user);
    /**校验*/
    ServerResponse<String> checkValid(String str, String type);
    /**找回密码的问题*/
    ServerResponse<String> slectQuestion(String username);
    /**检查答案*/
    ServerResponse<String> checkAnswer(String username, String question, String answer);
    /**重置密码*/
    ServerResponse<String> forgetRestRassword(String username, String passwordNew, String forgetToken);
    /**修改密码*/
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);
    /**更新用户信息*/
    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);
}
