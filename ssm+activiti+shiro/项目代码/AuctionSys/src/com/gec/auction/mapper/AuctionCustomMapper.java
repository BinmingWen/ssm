package com.gec.auction.mapper;

import com.gec.auction.pojo.Auction;
import com.gec.auction.pojo.AuctionCustom;
import com.gec.auction.pojo.AuctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionCustomMapper {
	public Auction findAuctionAndRecordAndUserById(Integer auctionId);
	public List<AuctionCustom> findEndAuction();
	public Auction findBuyingAuction();
    
}