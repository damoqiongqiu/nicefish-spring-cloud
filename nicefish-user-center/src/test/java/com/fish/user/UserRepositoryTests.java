package com.fish.user;

import com.fish.user.entity.UserEntity;
import com.fish.user.entity.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
		//test find all
//		Gson gson=new Gson();
		List<UserEntity> users=userRepository.findAll();
//		System.out.println(gson.toJson(users));

		//单独Insert User
		UserEntity user1=new UserEntity();
//		user1.setEmail("test-"+ UUID.randomUUID().toString());
//		user1.setCountry("test");
//		user1.setFirstName("test");
//		user1.setLastName("test");
//		user1.setMobile("test");
//		user1.setPassword("test");
//		userRepository.save(user1);

		//User 和 Role 级联insert
		UserEntity user2=new UserEntity();
//		user.setEmail("test");
//		user.setCountry("test");
//		user.setFirstName("test");
//		user.setLastName("test");
//		user.setMobile("test");
//		user.setPassword("test");
//		RoleEntity roleEntity=new RoleEntity();
//		roleEntity.setRoleName("test-role-"+ UUID.randomUUID().toString());
//		Set<RoleEntity> roles=new HashSet<RoleEntity>();
//		roles.add(roleEntity);
//		user.setRoles(roles);
//		userRepository.save(user);

		//test delete
//		userRepository.delete(15);

		//test update
//		user=new UserEntity();
//		user.setId(17);
//		user.setEmail("test");
//		user.setCountry("test");
//		user.setFirstName("test");
//		user.setLastName("test");
//		user.setMobile("test");
//		user.setPassword("test");
//		RoleEntity roleEntit2=new RoleEntity();
//		roleEntit2.setId(4);
//		roleEntit2.setRoleName("test-role-64e696f5-5ea3-42cb-bd8c-a02977cdc9cc");
//		Set<RoleEntity> roles2=new HashSet<RoleEntity>();
//		roles2.add(roleEntit2);
//		user.setRoles(roles2);
//		userRepository.save(user);
	}
}
