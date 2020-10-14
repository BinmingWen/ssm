package com.gec.auction.service;

import com.gec.auction.pojo.AuctionUser;

public interface UserService {
	public AuctionUser login(String name,String password);

	public void insert(AuctionUser auctionUser);

	public boolean checkUserExit(String username);
	

}
