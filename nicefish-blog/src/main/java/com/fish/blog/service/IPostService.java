package com.fish.blog.service;

import com.fish.blog.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    Page<PostEntity> getPostsPaging(Pageable var1);
    PostEntity getOne(Integer id);
    PostEntity savePost(PostEntity postEntity);
    void delPost(Integer id);
    Long countByUserId(Integer userId);
    List<PostEntity> findByUserIdAndPaging(Integer userId, Integer start, Integer limit);
}
