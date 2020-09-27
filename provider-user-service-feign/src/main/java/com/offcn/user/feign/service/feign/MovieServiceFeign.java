package com.offcn.user.feign.service.feign;

import com.offcn.user.feign.pojo.Movie;
import com.offcn.user.feign.service.feign.impl.MovieServiceFeignNative;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "PROVIDER-MOVIE-SERVICE",fallback = MovieServiceFeignNative.class)
public interface MovieServiceFeign {
    @GetMapping("/movie")
    public Movie adf();     //与返回值类型有关，路径一致。与方法名无关
}
