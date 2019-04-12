package com.fish.blog.service.impl;

import com.fish.blog.entity.PostRepository;
import com.fish.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@CacheConfig(cacheNames = "userService")
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    @Cacheable(value="posts",key ="T(String).valueOf(#var1.pageNumber).concat('-').concat(#var1.pageSize)", unless = "#result==null")
    public Page getPostsPaging(Pageable var1) {
        return postRepository.findAll(var1);
    }
}
