package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.People;
import com.example.demo.mapper.UserMapper;

@Service
public class PeopleService {
	@Autowired
	private UserMapper userMapper;
	
	public List<People> findUserInfo(){
		return userMapper.findUserInfo();
	}
}
