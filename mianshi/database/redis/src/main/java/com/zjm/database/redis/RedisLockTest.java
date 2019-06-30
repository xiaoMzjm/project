package com.zjm.database.redis;

import com.sun.deploy.panel.AbstractRadioPropertyGroup;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RedisLockTest {

    static Jedis jedis = new Jedis();

    static class Test1{
        public static void main(String[] args) {

            // 先判断key是否存在
            String value = jedis.get("key");

            // 不存在则进行set操作，占有该资源
            if(value != null) {
                jedis.set("key" , "value");
            }
        }
    }

    static class Test2{
        public static void main(String[] args) {

            Long result = jedis.setnx("key" , "value");

            if(Objects.equals(result , 1L)) {

                // ...
            }
        }
    }

    static class Test3{
        public static void main(String[] args) {

            Long result = jedis.setnx("key" , "value");

            if(Objects.equals(result , 1L)) {

                // 设置超时时间
                jedis.expire("key" , 3);
            }
        }
    }

    static class Test4{
        public boolean test() {

            Long expireTime = System.currentTimeMillis() + 3000;
            String expireTimeStr = expireTime.toString();

            Long result = jedis.setnx("key" , expireTimeStr);

            if(Objects.equals(result , 1L)) {

                return true;
            }

            String oldExpireTime = jedis.get("key");

            // 锁已超时
            if(oldExpireTime != null && System.currentTimeMillis() > Long.parseLong(oldExpireTime)) {

                // （1）
                String temp = jedis.getSet("key" , expireTimeStr);

                // 确保被当前线程设值成功
                if(Objects.equals(temp , expireTimeStr)) {
                    return true;
                }
            }

            return false;
        }
    }

    static class Test5{
        public boolean test(){
            SetParams setParams = new SetParams();
            setParams.nx(); // 只有value不存在才进行赋值
            setParams.px(3000); // 超时时间
            String result = jedis.set("key" , "value" , setParams);
            if(Objects.equals(result , "OK")) {
                return true;
            }
            return false;
        }
    }

    static class Test6{
        public void test(){

            boolean result = lock();
            if(!result) {
                return ;
            }
            try {

            }finally {
                jedis.del("key");
            }
        }

        public boolean lock(){return true;}
    }

    static class Test7{
        public void test(){

            String key = "key";
            String value = "value";
            boolean result = lock(value);
            if(!result) {
                return ;
            }
            try {

            }finally {
                if(Objects.equals(value , jedis.get(key))) {

                    // (1)
                    jedis.del("key");
                }
            }
        }

        public boolean lock(String value){return true;}
    }

    static class Test8{
        public void test(){

            String key = "key";
            String value = "value";
            boolean result = lock(value);
            if(!result) {
                return ;
            }
            try {

            }finally {
                jedis.del("");
            }
        }

        public boolean lock(String value){return true;}
    }

    static class Test9{
        public void unLock(){

            String key = "key";
            String value = "value";
            boolean result = lock(value);
            if(result) {
                return ;
            }
            try {

            }finally {
                List keys = new ArrayList<String>();
                keys.add("key");

                List values = new ArrayList<String>();
                keys.add("value");


                Object evalResult = jedis.eval("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end" , keys , values);

                if(Objects.equals(evalResult , 1) || Objects.equals(0 , evalResult)) {
                    return ;
                }
                else {
                    throw new RuntimeException("释放锁异常");
                }

            }
        }

        public boolean lock(String value){return true;}
    }
}
