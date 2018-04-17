package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.People;
import com.example.demo.service.PeopleService;
import com.example.demo.util.AESUtil;
import com.example.demo.util.HttpUtil;

import net.sf.json.JSONObject;

@RestController 
public class TestController {
	@Autowired
	private PeopleService peopleService;
	private String appId = "wx1186923b849d1bd8";
	private String appsecret = "b92b8eef347c332f55d9050f743b4c9b";
	private String get_openid_url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	
	@RequestMapping("/list")
	public Map<String, List<People>> getList() {
		Map<String,List<People>> map = new HashMap<String,List<People>>();
		List<People> list = peopleService.findUserInfo();
		map.put("list", list);
		return map;
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/login")
	public Map<String,String> login(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		String code = request.getParameter("code");
		String encryptedData = request.getParameter("encryptedData");
		String iv = request.getParameter("iv");
		String url = get_openid_url.replace("APPID", appId).replace("SECRET",appsecret).replace("JSCODE", code);
		String result = HttpUtil.SendPost(url);	
		JSONObject json = JSONObject.fromObject(result);
		String openid = json.get("openid").toString();
		String sessionKey = json.get("session_key").toString();
		try {
			String data = AESUtil.decrypt(encryptedData, sessionKey, iv, "utf-8");
			JSONObject jsonObject = JSONObject.fromObject(data);
			map.put("nickName", jsonObject.getString("nickName"));
			map.put("avatarUrl", jsonObject.getString("avatarUrl"));
			map.put("gender", jsonObject.getString("gender"));
			map.put("city", jsonObject.getString("city"));
			map.put("province", jsonObject.getString("province"));
			map.put("country", jsonObject.getString("country"));
			map.put("language", jsonObject.getString("language"));
			map.put("openid", openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
