package com.fish.blog.entity;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Integer> {

}
