package com.fish.blog.entity;

import javax.persistence.*;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="blog_post")
public class PostEntity {
    @Id
    @Column(name="PostId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
