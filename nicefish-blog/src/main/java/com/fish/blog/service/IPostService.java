package com.fish.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService<T> {
    Page<T> getPostsPaging(Pageable var1);
    T getOne(Integer id);
    T savePost(T var1);
    void delPost(Integer id);
    Long countByUserId(Integer userId);
    List<T> findByUserIdAndPaging(Integer userId, Integer start, Integer limit);
}
