package com.fish.blog.util;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * PageImpl没有默认的无参构造，会导致Jackson反序列化失败，继承一个空的子类来支持Jackson的反序列化
 * ref:https://jira.spring.io/browse/DATACMNS-1061
 * @param <T>
 */
public class ResponsePageImpl <T> extends PageImpl<T> implements Serializable {

    public ResponsePageImpl () {
        super (new ArrayList<>());
    }

    public ResponsePageImpl (List<T> content) {
        super (content);
    }

    public ResponsePageImpl (List <T> content, Pageable pageable, long total) {
        super (content, pageable, total);
    }
}