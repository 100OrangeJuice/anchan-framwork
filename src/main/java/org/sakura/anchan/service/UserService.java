package org.sakura.anchan.service;

/**
 * Created by Anchan on 2018/7/12.
 */
import org.sakura.anchan.POJO.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);

}
