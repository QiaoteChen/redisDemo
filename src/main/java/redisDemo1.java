import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class redisDemo1 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);


        String value = jedis.ping();
        System.out.println(value);
    }


    @Test
    public void demo1(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Set<String> keys = jedis.keys("*");
        for(String s :keys){
            System.out.println(s);
        }




    }


}
