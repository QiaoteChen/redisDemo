import redis.clients.jedis.Jedis;

import java.util.Random;

public class redisDemo2 {


    public static void main(String[] args) {

//        vertifyCode("17815936500");
        getRedisCode("17815936500","855704");

    }


    //生成6位验证码
    public static String getcode() {
        Random random = new Random();
        String code = "";

        for (int i = 0; i < 6; i++) {
            int j = random.nextInt(10);
            code += j;

        }
        return code;

    }

    //每天每个手机只能发送3次，验证码放到redis中，设置验证时间

    public static void vertifyCode(String phone) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        String countKey = "Vertify"+phone+":count";
        String codeKey = "Vertify"+phone+":code";

        String count = jedis.get(countKey);

        if (count == null){
            jedis.setex(countKey,24*3600,"1");
        }
        else if (Integer.valueOf(count) <= 2){
            jedis.incr(countKey);
        }
        else if (Integer.valueOf(count) > 2){
            System.out.println("一天只能发送3次");

        }

        //发送验证码到redis
        String vcode = getcode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();





    }

    public static void getRedisCode(String phone,String code){

        Jedis jedis = new Jedis("127.0.0.1",6379);

        String codeKey = "Vertify"+phone+":code";

        String redisCode = jedis.get(codeKey);
        System.out.println(redisCode);



        if(redisCode.equals(code)){
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        jedis.close();





    }


}
