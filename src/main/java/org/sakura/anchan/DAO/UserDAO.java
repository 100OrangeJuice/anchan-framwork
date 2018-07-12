package org.sakura.anchan.DAO;

import org.sakura.anchan.POJO.User;

/**
 * Created by Anchan on 2018/7/12.
 */
public interface UserDAO {
    int deleteByPrimaryKey(Integer id);
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
}
