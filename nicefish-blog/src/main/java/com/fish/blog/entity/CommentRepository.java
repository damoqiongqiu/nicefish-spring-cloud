package com.fish.blog.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(value = "SELECT * FROM blog_comment WHERE post_id = ?1 ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM blog_comment WHERE post_id = ?1 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<CommentEntity> findByPostId(String postId,Pageable pageable);
}