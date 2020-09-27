package com.offcn.user.feign.service;

import com.offcn.user.feign.dao.UserDao;
import com.offcn.user.feign.pojo.Movie;
import com.offcn.user.feign.pojo.User;
import com.offcn.user.feign.service.feign.MovieServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    MovieServiceFeign movieServiceFeign;

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
    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);

        //2.查到最新电影票
        //TODO 暂时为null
        //稍后，让用户服务调用电影服务
        //暂且设置成null

//        ResponseEntity<Movie> responseEntity = restTemplate.getForEntity("http://PROVIDER-MOVIE-SERVICE/movie", Movie.class);
//        Movie movie = responseEntity.getBody();

        Movie movie = movieServiceFeign.adf();
        result.put("movie", movie);

        return result;
    }
}