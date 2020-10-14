package com.gec.auction.service;

import java.util.List;

import com.gec.auction.pojo.Auction;
import com.gec.auction.pojo.AuctionCustom;
import com.gec.auction.pojo.AuctionRecord;

public interface AuctionService {
	public List<Auction> queryAuctions(Auction auction);
	public void addAuction(Auction auction);
	public Auction findAuctionById(int id);
	public void updateAuction(Auction auction);
	public Auction findAuctionAndRecordAndUserById(int id);
	public void addAuctionRecord(AuctionRecord record) throws Exception;
	public List<AuctionCustom> findAuctionResult();
	public Auction findAuctionBuying();

}
