package com.offcn.user.feign.service.feign.impl;

import com.offcn.user.feign.pojo.Movie;
import com.offcn.user.feign.service.feign.MovieServiceFeign;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MovieServiceFeignNative implements MovieServiceFeign{
    @Override
    public Movie adf() {
        Movie movie = new Movie();
        movie.setId(10000);
        movie.setMovieName("请稍后再试");

        return movie;
    }
}
