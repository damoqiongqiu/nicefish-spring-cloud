package com.fish.blog.controller;

import com.fish.blog.entity.PostEntity;
import com.fish.blog.entity.PostRepository;
import com.fish.user.util.AjaxResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * @author 大漠穷秋
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class PostController {
	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private PostRepository postRepository;

    //TODO:每页显示的条数改为系统配置项
	private Integer pageSize=10;

	@RequestMapping(value = "/post-list/{page}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPostList(@PathVariable(value="page",required = false) Integer page) {
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;
		Page<PostEntity> postEntities=postRepository.findAll(new PageRequest(page,pageSize));
		return new ResponseEntity<>(postEntities, HttpStatus.OK);
	}

	@RequestMapping(value = "/post-detail/{id}",method = RequestMethod.GET)
	public ResponseEntity<Object> getPostDetail(@PathVariable(value = "id",required = true) Integer id){
		return new ResponseEntity<>(postRepository.findOne(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('add_post')")
	@RequestMapping(value = "/write-post",method = RequestMethod.POST)
	public ResponseEntity<Object> writePost(@RequestBody PostEntity postEntity){
	    //用户相关的服务都在nicefish-user-center项目中实现，这里调用nicefish-user-center提供的微服务，获取用户昵称等资料
		ServiceInstance serviceInstance = loadBalancer.choose("nicefish-user-center");
		HashMap<String,Object> serviceResult = new RestTemplate().getForObject(
				serviceInstance.getUri().toString() + "/users/"+postEntity.getUserId(),
				HashMap.class);

		log.debug(serviceResult.toString());
		//TODO:如果没有查到用户资料，报错返回

		postEntity.setUserId((int)serviceResult.get("id"));
		postEntity.setEmail((String)serviceResult.get("email"));
		postEntity.setNickName(serviceResult.get("nickName")==null?"":serviceResult.get("nickName").toString());
		//TODO:返回的数据里面没有id，事务问题？
		postEntity=postRepository.save(postEntity);
		return new ResponseEntity<>(postEntity, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('edit_post')")
	@RequestMapping(value = "/edit-post",method = RequestMethod.POST)
	public ResponseEntity<Object> editPost(@RequestBody PostEntity postEntity){
		//用户相关的服务都在nicefish-user-center项目中实现，这里调用nicefish-user-center提供的微服务，获取用户昵称等资料
		ServiceInstance serviceInstance = loadBalancer.choose("nicefish-user-center");
		HashMap<String,Object> serviceResult = new RestTemplate().getForObject(
				serviceInstance.getUri().toString() + "/users/"+postEntity.getUserId(),
				HashMap.class);

		log.debug(serviceResult.toString());
		//TODO:如果没有查到用户资料，报错返回

		postEntity.setUserId((int)serviceResult.get("id"));
		postEntity.setEmail((String)serviceResult.get("email"));
		postEntity.setNickName(serviceResult.get("nickName")==null?"":serviceResult.get("nickName").toString());
		//TODO:返回的数据里面没有id，事务问题？
		postEntity=postRepository.save(postEntity);
		return new ResponseEntity<>(postEntity, HttpStatus.OK);
	}

	@RequestMapping(value = "/manage/post-table", method = RequestMethod.POST)
	public ResponseEntity<Object> getPostListByUserId(@RequestBody HashMap<String,String> params) {
		Integer page=Integer.valueOf(params.get("page"));
		Integer userId=Integer.valueOf(params.get("userId"));
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;

		List<PostEntity> postEntities=postRepository.findByUserIdAndPageing(userId,page*pageSize,pageSize);
		Long totalElements=postRepository.countByUserId(userId);
		Integer totalPages=(int)(totalElements/pageSize+1);

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("totalElements",totalElements);
		resultMap.put("totalPages",totalPages);
		resultMap.put("size",pageSize);
		resultMap.put("content",postEntities);

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('del_post')")
	@RequestMapping(value = "/manage/del-post/{postId}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> delPostById(@PathVariable(value="postId",required = true) Integer postId){
		postRepository.delete(postId);
		return new ResponseEntity<>(new AjaxResponseEntity(true,"删除成功"),HttpStatus.OK);
	}
}
