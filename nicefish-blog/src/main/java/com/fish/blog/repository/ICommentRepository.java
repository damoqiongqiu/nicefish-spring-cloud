package com.fish.blog.repository;

import com.fish.blog.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
@Repository
public interface ICommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(value = "SELECT * FROM blog_comment WHERE post_id = ?1 ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM blog_comment WHERE post_id = ?1 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<CommentEntity> findByPostId(String postId,Pageable pageable);

    @Query(value=
            "select * from blog_comment where user_id=? order by comment_time desc limit ?,?"
            ,nativeQuery = true)
    List<CommentEntity> findCommentByUserIdAndPaging(Integer userId, Integer start, Integer limit);

    Long countByUserId(Integer userId);
}