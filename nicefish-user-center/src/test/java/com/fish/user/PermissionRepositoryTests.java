package com.fish.user;

import com.fish.user.entity.PermissionEntity;
import com.fish.user.entity.PermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionRepositoryTests {
	@Autowired
	private PermissionRepository permissionRepository;

	@Test
	public void contextLoads() {
		List<PermissionEntity> permissionEntities=permissionRepository.findAll();
		System.out.println(permissionEntities);
	}
}
