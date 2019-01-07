package com.fish.user.controller;

import com.fish.user.entity.RoleRepository;
import com.fish.user.entity.UserEntity;
import com.fish.user.entity.UserRepository;
import com.fish.user.util.AjaxResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	//TODO:每页显示的条数改为系统配置项
	private Integer pageSize=10;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getListOfUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
	public ResponseEntity<Object> getUserDetail(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
		//TODO:加参数校验
		if(userRepository.findByEmail(userEntity.getEmail()).size()!=0){
			return new ResponseEntity<>(new AjaxResponseEntity(false,"邮箱已经被使用"), HttpStatus.OK);
		}
		//TODO:不允许创建大于或等于自己权限的用户
		//TODO:用户创建完成之后自动赋予查看和发帖权限
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		UserEntity result=userRepository.save(userEntity);
		result.setPassword("");
		logger.debug(result.getId().toString());
		return new ResponseEntity<>(new AjaxResponseEntity(true,"创建成功",result), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/edit/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> updateUser(@PathVariable("id") Integer id, @RequestBody UserEntity userEntity) {
		//TODO:数据和业务逻辑校验，不准修改邮箱，邮箱格式必须合法
		//TODO:数据保护，不允许编辑比自己权限高的用户
		userRepository.save(userEntity);
		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Integer id) {
		//TODO:数据保护，不允许删除比自己权限高的用户
		userRepository.delete(id);
		return new ResponseEntity<>(new AjaxResponseEntity(true,"删除成功"), HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.GET)
	public ResponseEntity<Object> viewUsersByRole(@PathVariable("id") Integer roleId) {
		return new ResponseEntity<>(roleRepository.findOne(roleId).getUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignUsers2Role(@PathVariable("id") Integer roleId, @RequestBody ArrayList<String> usersList) {
		//TODO:这里需要重新实现
		return new ResponseEntity<>("Users are assigned to role successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/users/user-table", method = RequestMethod.POST)
	public ResponseEntity<Object> getUserListByPaging(@RequestBody HashMap<String,String> params) {
		Integer page=Integer.valueOf(params.get("page"));
		if(page==null||page<=0){
			page=1;
		}
		page=page-1;

		List<UserEntity> postEntities=userRepository.findByPageing(page*pageSize,pageSize);
		Long totalElements=userRepository.countAllRows();
		Integer totalPages=(int)(totalElements/pageSize+1);

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("totalElements",totalElements);
		resultMap.put("totalPages",totalPages);
		resultMap.put("size",pageSize);
		resultMap.put("content",postEntities);

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}
