package com.gec.auction.junit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gec.auction.mapper.AuctionCustomMapper;
import com.gec.auction.pojo.Auction;
import com.gec.auction.pojo.AuctionCustom;
import com.gec.auction.pojo.AuctionRecord;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml","classpath:spring/springmvc.xml"})
public class TestAuctionCust {
	@Autowired
	private AuctionCustomMapper auctionCustomMapper;
	@Test
	public void test(){
		Auction auction = auctionCustomMapper.findAuctionAndRecordAndUserById(20);
		for(AuctionRecord record:auction.getAuctionRecordList()){
			System.out.println(record.getAuctionid()+","+record.getAuctionprice()+","+
		    record.getAuctionUser().getUsername());
		}
		
	}
	@Test
	public void testAuctionCustom(){
		List<AuctionCustom> list = auctionCustomMapper.findEndAuction();
		for(AuctionCustom auc:list){
			System.out.println(auc.getAuctionname()+","+auc.getAuctionPrice());
		}
	}

}
