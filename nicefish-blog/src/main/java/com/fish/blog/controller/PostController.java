package com.fish.blog.controller;

import com.fish.blog.entity.PostEntity;
import com.fish.blog.entity.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	final static Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostRepository postRepository;

	//TODO:每页显示的条数改为系统配置项
	@RequestMapping(value = "/blog/post/{page}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPostList(@PathVariable(value="page",required = false) Integer page) {
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;
		Page<PostEntity> postEntities=postRepository.findAll(new PageRequest(page,5));
		return new ResponseEntity<>(postEntities, HttpStatus.OK);
	}

	@RequestMapping(value = "/blog/post-detail/{id}",method = RequestMethod.GET)
	public  ResponseEntity<Object> getPostDetail(@PathVariable(value = "id",required = true) Integer id){
		PostEntity postEntity=postRepository.findOne(id);
		return new ResponseEntity<>(postEntity, HttpStatus.OK);
	}
}
