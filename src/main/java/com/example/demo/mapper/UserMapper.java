package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.People;

@Mapper
public interface UserMapper {
	public List<People> findUserInfo();
	
}
