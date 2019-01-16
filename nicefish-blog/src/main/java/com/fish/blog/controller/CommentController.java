package com.fish.blog.controller;

import com.fish.blog.entity.CommentEntity;
import com.fish.blog.entity.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author 大漠穷秋
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;

	//TODO:每页显示的条数改为系统配置项
	private Integer pageSize=10;

	@RequestMapping(value = "/comment/{postId}/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCommentList(@PathVariable(value="postId") String postId,@PathVariable(value="page",required = false) Integer page) {
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;
		Page<CommentEntity> commentEntities=commentRepository.findByPostId(postId,new PageRequest(page,pageSize));
		log.debug(commentEntities.toString());
		return new ResponseEntity<>(commentEntities, HttpStatus.OK);
	}

	@RequestMapping(value="/comment/write-comment",method = RequestMethod.POST)
	public ResponseEntity<Object> writeComment(@RequestBody CommentEntity commentEntity){
        commentEntity=commentRepository.save(commentEntity);
        return new ResponseEntity<>(commentEntity, HttpStatus.OK);
    }

	@RequestMapping(value = "/manage/comment-table", method = RequestMethod.POST)
	public ResponseEntity<Object> getPostListByUserId(@RequestBody HashMap<String,String> params) {
		Integer page=Integer.valueOf(params.get("page"));
		Integer userId=Integer.valueOf(params.get("userId"));
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;

		List<CommentEntity> commentEntities=commentRepository.findCommentByUserIdAndPaging(userId,page*pageSize,pageSize);
		Long totalElements=commentRepository.countByUserId(userId);
		Integer totalPages=(int)(totalElements/pageSize+1);

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("totalElements",totalElements);
		resultMap.put("totalPages",totalPages);
		resultMap.put("size",pageSize);
		resultMap.put("content",commentEntities);

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}