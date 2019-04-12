package com.fish.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService<T> {
    Page<T> getPostsPaging(Pageable var1);
}
