package org.sakura.anchan.DAO;

import org.apache.ibatis.annotations.Param;
import org.sakura.anchan.POJO.User;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Integer checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkEmail(String email);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

    int checkPassword(@Param("password") String password, @Param("userid") Integer userid);

    int checkEmailByUserId(@Param("email") String email, @Param("userid") Integer userid);
}