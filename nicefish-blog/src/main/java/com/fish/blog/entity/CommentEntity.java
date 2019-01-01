package com.fish.blog.entity;

import javax.persistence.*;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="blog_comment")
public class CommentEntity {
    @Id
    @Column(name="CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
}
