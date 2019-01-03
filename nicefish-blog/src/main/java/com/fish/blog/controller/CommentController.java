package com.fish.blog.controller;

import com.fish.blog.entity.CommentEntity;
import com.fish.blog.entity.CommentRepository;
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
public class CommentController {
	final static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentRepository commentRepository;

	//TODO:每页显示的条数改为系统配置项
	@RequestMapping(value = "/blog/comment/{postId}/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPostList(@PathVariable(value="postId") String postId,@PathVariable(value="page",required = false) Integer page) {
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;
		Page<CommentEntity> commentEntities=commentRepository.findByPostId(postId,new PageRequest(page,5));
		logger.debug(commentEntities.toString());
		return new ResponseEntity<>(commentEntities, HttpStatus.OK);
	}
}
