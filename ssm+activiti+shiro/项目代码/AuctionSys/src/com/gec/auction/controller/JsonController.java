package com.gec.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gec.auction.pojo.AuctionUser;

@Controller
public class JsonController {

	@RequestMapping("/sendNoJsonRequest")
	@ResponseBody
	public AuctionUser sendNoJsonRequest(@RequestBody AuctionUser auctionUser){
		System.out.println(auctionUser.getUsername());
		System.out.println(auctionUser.getUserpassword());
		return auctionUser;
	}
}
