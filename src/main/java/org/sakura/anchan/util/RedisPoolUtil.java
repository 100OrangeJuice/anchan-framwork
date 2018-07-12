package org.sakura.anchan.util;

import lombok.extern.slf4j.Slf4j;
import org.sakura.anchan.common.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * Created by Anchan on 2018/5/5.
 */
@Slf4j
public class RedisPoolUtil {

//    Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 设置key的有效期，单位是秒
     */
    public static Long expire(String key, int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            log.error("expire  key:{}  error:{}", key, e);
            RedisPool.returnBrokenJedis(jedis);
            return result;
        }
        RedisPool.returnBrokenJedis(jedis);
        return result;
    }

    /**ex单位时间是秒
     * 封装setEx方法
     * @param key
     * @param exTime
     * @param value
     * @return
     */
     public static String setEx(String key, int exTime, String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex  key:{} value:{} error{}", key, value, e);
            RedisPool.returnBrokenJedis(jedis);
            return result;
        }
        RedisPool.returnBrokenJedis(jedis);
        return result;
    }

    /**
     * 封装set方法
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error{}", key, value, e);
            RedisPool.returnBrokenJedis(jedis);
            return result;
        }
        RedisPool.returnBrokenJedis(jedis);
        return result;
    }

    /**
     * 封装get的方法
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("set key:{} error{}", key, e);
            RedisPool.returnBrokenJedis(jedis);
            return result;
        }
        RedisPool.returnBrokenJedis(jedis);
        return result;
    }

    /**
     * 封装的删除的方法
     * @param key
     * @return
     */
    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("set key:{} error{}", key, e);
            RedisPool.returnBrokenJedis(jedis);
            return result;
        }
        RedisPool.returnBrokenJedis(jedis);
        return result;
    }

    public static void main(String[] args) {
            Jedis jedis = RedisPool.getJedis();
            RedisPoolUtil.set("keytest","value");

            String value = RedisPoolUtil.get("keytest");
            RedisPoolUtil.setEx("keyex",60*10,"valueex");
            RedisPoolUtil.expire("keytest",60*20);
            RedisPoolUtil.del("keytest");
        System.out.println("end");
    }
}
