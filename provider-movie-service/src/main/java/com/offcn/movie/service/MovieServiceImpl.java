package com.offcn.movie.service;

import com.offcn.movie.pojo.Movie;
import com.offcn.movie.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieDao movieDao;

    @Override
    public Movie getNewMovie() {
        return movieDao.getNewMovie();
    }
}
