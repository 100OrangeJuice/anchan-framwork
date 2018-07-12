package org.sakura.anchan.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Anchan on 2018/5/5.
 */
@Slf4j
public class JsonUtil {
//    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static ObjectMapper objectMapper = new ObjectMapper();
    //一些配置的初始化
    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        //所有的日期格式统一
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.FORMAT_STR));
        //忽略 在json字符串中存在，在Java字符串中不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * 对象转Json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj){
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    /**
     * 对象转Json美化输出
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj){
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String)obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    /**
     *  Json转对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class clazz){
        if (clazz == null || StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T)str : (T) objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.warn("Parse string to object error", e);
            return null;
        }
    }

    /**
     * 字符串转换集合对象
     * 传new TypeReference<List<User>>() {}
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static<T> T string2Obj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("Parse string to object error", e);
            return null;
        }
    }

    /**
     * 字符串转换集合对象
     * 传多个类类型
     * @param str
     * @param collectionClassType
     * @param classes
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClassType, Class<?>...classes){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClassType, classes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse string to object error", e);
            return null;
        }
    }

/*
    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("ancahn@outlook.com");
        //测试将对象转换为Json
        String user1Json = JsonUtil.obj2String(u1);
        String user1Pretty = JsonUtil.obj2StringPretty(u1);
        log.info("userJson:{}", user1Json);
        log.info("userJsonPretty:{}",user1Pretty);
        //测试将字符串转换为对象
        User user2 = JsonUtil.string2Obj(user1Pretty, User.class);
        System.out.println(user2);

        //测试将集合对象转换为字符串
        List<User> userList = new ArrayList<User>();
        userList.add(u1);
        user2.setUsername("anchan");
        userList.add(user2);

        String userListStr = JsonUtil.obj2String(userList);

        //测试将字符串转换为集合对象。
        List<User> userListObj = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {});

        //测试字符串转换集合
        List<User> userList2 = JsonUtil.string2Obj(userListStr, List.class, User.class);
        System.out.println("end");
    }
*/

}
