package org.sakura.anchan.controller;


import org.apache.commons.lang3.StringUtils;
import org.sakura.anchan.POJO.User;
import org.sakura.anchan.common.Const;
import org.sakura.anchan.common.ResponseCode;
import org.sakura.anchan.common.ServerResponse;
import org.sakura.anchan.service.IUserService;
import org.sakura.anchan.util.CookieUtil;
import org.sakura.anchan.util.JsonUtil;
import org.sakura.anchan.util.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Anchan on 2017/12/1.
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    /**
     * 用户登陆
     * @param username
     * @param password
     * @param session
     * @return
     */

    @Autowired
    private IUserService iUserService;

    /**
     * 登陆模块
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)

    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse){
        ServerResponse<User> response = iUserService.login(username,password);
        if(response.isSuccess()){
//            session.setAttribute(Const.CURRENT_USER,response.getData());
            CookieUtil.writeLoginToken(httpServletResponse, session.getId());
            RedisPoolUtil.setEx(session.getId(), Const.RedisCacheExtime.REDIS_SESSION_EXTIME , JsonUtil.obj2String(response.getData()));
        }
        return response;
    }

    /**
     * 登出模块
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)

    public ServerResponse<User> logout(HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        CookieUtil.deleteLoginToken(httpServletRequest,httpServletResponse);
        RedisPoolUtil.del(session.getId());
//        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册模块
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do",method = RequestMethod.POST)

    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    /**
     * 检查邮箱或用户名是否合法
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)

    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str, type);
    }

    /**
     * 检查用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)

    public ServerResponse<User> getUserInfo(HttpSession session, HttpServletRequest httpServletRequest){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    /**
     * 返回密码提示问题
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)

    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.slectQuestion(username);
    }

    /**
     * 检查问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)

    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }

    /**
     * 忘记密码的重置密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)

    public ServerResponse<String> forgetRestRassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetRestRassword(username,passwordNew,forgetToken);
    }

    /**
     * 登陆状态的重置密码
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)

    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew, HttpServletRequest httpServletRequest){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    /**
     * 更新用户信息
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)

    public ServerResponse<User> update_information(HttpSession session, User user, HttpServletRequest httpServletRequest){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr,User.class);
//        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if (response.isSuccess()){
//            session.setAttribute(Const.CURRENT_USER,response.getData());
            RedisPoolUtil.setEx(loginToken, Const.RedisCacheExtime.REDIS_SESSION_EXTIME ,JsonUtil.obj2String(response.getData()));
        }
        return response;
    }

    /**
     * 获取用户详细信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)

    public ServerResponse<User> get_information(HttpSession session, HttpServletRequest httpServletRequest){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr,User.class);
//        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登陆，需要强制登陆status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }
}
