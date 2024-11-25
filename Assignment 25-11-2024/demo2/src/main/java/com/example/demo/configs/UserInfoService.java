package com.example.demo.configs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService {

	@Autowired
	UserInfoRepository repository;

	public UserInfoService(UserInfoRepository userInfoRepository) {
		this.repository = userInfoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	}

}
