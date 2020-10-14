package com.gec.auction.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.auction.mapper.AuctionUserMapper;
import com.gec.auction.pojo.AuctionUser;
import com.gec.auction.pojo.AuctionUserExample;
import com.gec.auction.pojo.AuctionUserExample.Criteria;
import com.gec.auction.service.UserService;
@Service
public class UserServiceImple implements UserService {

	@Autowired
	private AuctionUserMapper auctionUserMapper;
	@Override
	public AuctionUser login(String name, String password) {
		AuctionUserExample example = new AuctionUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(name);
		criteria.andUserpasswordEqualTo(password);
		List<AuctionUser> list = auctionUserMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public void insert(AuctionUser auctionUser) {
		// TODO Auto-generated method stub
		auctionUserMapper.insert(auctionUser);
		
	}
	@Override
	public boolean checkUserExit(String username) {
		// TODO Auto-generated method stub
		AuctionUserExample example = new AuctionUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<AuctionUser> list = auctionUserMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			return true;
		}
		return false;
	}

}
