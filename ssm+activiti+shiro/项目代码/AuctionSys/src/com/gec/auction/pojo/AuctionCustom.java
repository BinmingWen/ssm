package com.gec.auction.pojo;

public class AuctionCustom extends Auction{
	private Double auctionPrice;
	private String userName;
	public Double getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(Double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    
}