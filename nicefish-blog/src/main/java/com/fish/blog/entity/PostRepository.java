package com.fish.blog.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Integer> {
    @Query(value=
            "select * from blog_post where user_id=? order by post_time desc limit ?,?"
            ,nativeQuery = true)
    List<PostEntity> findByUserIdAndPageing(Integer userId,Integer start,Integer limit);

    Long countByUserId(Integer userId);
}
