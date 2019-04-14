package com.fish.blog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
@Repository
public interface IPostRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    @Query(value=
            "select * from blog_post where user_id=? order by post_time desc limit ?,?"
            ,nativeQuery = true)
    List<T> findByUserIdAndPageing(Integer userId,Integer start,Integer limit);

    Long countByUserId(Integer userId);
}
