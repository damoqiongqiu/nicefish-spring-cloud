package com.fish.blog.repository;

import com.fish.blog.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Integer> {
    @Query(value=
            "select * from blog_post where user_id=? order by post_time desc limit ?,?"
            ,nativeQuery = true)
    List<PostEntity> findByUserIdAndPaging(Integer userId,Integer start,Integer limit);

    Long countByUserId(Integer userId);
}
