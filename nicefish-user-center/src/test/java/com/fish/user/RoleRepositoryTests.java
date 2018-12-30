package com.fish.user;

import com.fish.user.entity.RoleEntity;
import com.fish.user.entity.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTests {
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void contextLoads() {
		//test find all
		List<RoleEntity> roleEntities=roleRepository.findAll();
		System.out.println(roleEntities);
	}
}
