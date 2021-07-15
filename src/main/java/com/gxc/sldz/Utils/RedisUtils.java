package com.gxc.sldz.Utils;

import com.gxc.sldz.dto.SldzCompanyCouponsDTO;
import com.gxc.sldz.entity.SldzCompanyCoupons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtils {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;



    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 读取缓存
     *
     * @param keys
     * @return
     */
    public Set<String> getByKeys(final String keys) {
        Set<String> strings = new HashSet<>();
        //获取到keys 遍历获取v值
        Set<String> keyss = redisTemplate.keys(keys);
        for (String s :keyss){
            strings.add(get(s));
        }
        return strings;
    }


    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value,long i) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key,value,i, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Boolean exists = redisTemplate.hasKey(key);
        return exists;
    }

}
