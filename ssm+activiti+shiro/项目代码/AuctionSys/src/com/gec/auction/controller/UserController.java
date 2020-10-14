package com.gec.auction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gec.auction.pojo.AuctionUser;
import com.gec.auction.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/doLogin")
	public String doLogin(String username,String userpassword,String inputCode,
			HttpSession session,Model model) {
		//1.校验验证码：
		String numrand =(String) session.getAttribute("numrand");
		if(!numrand.equals(numrand)) {
			model.addAttribute("msg","验证码错误");
			return "login";
		}
		//2.查询数据库
		AuctionUser loginUser = userService.login(username, userpassword);
		if(loginUser!=null) {
			session.setAttribute("user", loginUser);
			System.out.println(session);
			System.out.println("................");
			return "redirect:/auction/queryAuctions";
		}else {
			model.addAttribute("msg", "账号或密码错误");
			return "login";
		}
	}
	@RequestMapping("/layout")
	public String layout(HttpSession session){
		session.invalidate();
		return "login";
	}
	@RequestMapping("/register")
	public String register(Model model,
			@ModelAttribute("registerBean")@Validated AuctionUser auctionUser,BindingResult bingdingResult){
		//判断用户填写的信息是否有问题
		if(bingdingResult.hasErrors()){
			List<FieldError> errors = bingdingResult.getFieldErrors();
			//把错误信息带给前端
			for(FieldError field:errors){
				model.addAttribute(field.getField(), field.getDefaultMessage());
			}
			return "register";
		}
		userService.insert(auctionUser);
		return "login";
	}
	
	
	@RequestMapping("/checkUserExit")
	@ResponseBody
	public Map<String,String> checkUserExit(String username){
		System.out.println(username);
		boolean flag = userService.checkUserExit(username);
		Map<String,String> map = new HashMap<>();
		if(flag){
			map.put("msg", "用户已存在");
		}else{
			map.put("msg", "用户可用");
		}
		return map;
	}
	
	

}
