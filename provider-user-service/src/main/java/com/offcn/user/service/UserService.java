package com.offcn.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.user.dao.UserDao;
import com.offcn.user.pojo.Movie;
import com.offcn.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据ID得到用户对象
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    /**
     * 购买最新的电影票
     * @param id 用户ID
     * @return
     */

    @HystrixCommand(fallbackMethod = "defaultBuyMovie")
    //当buyMovie方法进行服务调用时，如果发现服务不可用，执行fallbackMethod的本地方法 断路器

    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);

        //2.查到最新电影票
        //TODO 暂时为null
        //稍后，让用户服务调用电影服务
        //暂且设置成null

        ResponseEntity<Movie> responseEntity = restTemplate.getForEntity("http://PROVIDER-MOVIE-SERVICE/movie", Movie.class);
        Movie movie = responseEntity.getBody();

        result.put("movie", movie);

        return result;
    }

    public Map<String,Object> defaultBuyMovie(Integer id){
        HashMap map = new HashMap();
        map.put("msg","请求失败，请稍后再试");
        map.put("code","119000");
        return map;
    }
}