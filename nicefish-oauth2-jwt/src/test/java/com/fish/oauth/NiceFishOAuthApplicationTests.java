package com.fish.oauth;

import com.fish.oauth.entity.UserEntity;
import com.fish.oauth.entity.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NiceFishOAuthApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void baseTest() throws Exception {
        UserEntity userEntity=userRepository.findByEmail("william_j@gmail.com");
        System.out.print(userEntity);

//        userEntity=userRepository.findByEmailAndPassword("william_j@gmail.com", new BCryptPasswordEncoder().encode("amaramar"));
//        System.out.print(userEntity);

        List<String> authorities=userRepository.findAuthoritiesByEmail(userEntity.getEmail());
        System.out.print(authorities);
    }
}