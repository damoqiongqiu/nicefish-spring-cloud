package com.fish.blog.service.impl;

import com.fish.blog.repository.IPostRepository;
import com.fish.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl<T> implements IPostService<T> {
    @Autowired
    private IPostRepository<T,Integer> postRepository;

    @Override
    @Cacheable(value="posts",key ="T(String).valueOf(#var1.pageNumber).concat('-').concat(#var1.pageSize)", unless = "#result==null")
    public Page getPostsPaging(Pageable var1) {
        return postRepository.findAll(var1);
    }

    @Override
    @Cacheable(value = "post-detail",key = "T(String).valueOf(#id)")
    public T getOne(Integer id){
        return (T)postRepository.findOne(id);
    }

    //TODO:重新设计posts这个缓存的KV结构，避免清除整个缓存
    @Override
    @Caching(evict = {@CacheEvict("posts"),@CacheEvict(value="post-detail",key="T(String).valueOf(#var1.id)")})
    public T savePost(T var1) {
        return postRepository.save(var1);
    }

    @Override
    public void delPost(Integer id) {
        postRepository.delete(id);
    }

    @Override
    public Long countByUserId(Integer userId) {
        return postRepository.countByUserId(userId);
    }

    @Override
    public List<T> findByUserIdAndPaging(Integer userId, Integer start, Integer limit) {
        return postRepository.findByUserIdAndPageing(userId,start,limit);
    }
}
