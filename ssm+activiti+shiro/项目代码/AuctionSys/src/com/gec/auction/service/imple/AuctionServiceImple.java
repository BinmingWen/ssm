package com.gec.auction.service.imple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.auction.exception.AuctionPriceException;
import com.gec.auction.mapper.AuctionCustomMapper;
import com.gec.auction.mapper.AuctionMapper;
import com.gec.auction.mapper.AuctionRecordMapper;
import com.gec.auction.pojo.Auction;
import com.gec.auction.pojo.AuctionCustom;
import com.gec.auction.pojo.AuctionExample;
import com.gec.auction.pojo.AuctionExample.Criteria;
import com.gec.auction.pojo.AuctionRecord;
import com.gec.auction.service.AuctionService;
@Service
public class AuctionServiceImple implements AuctionService {

	@Autowired
	private AuctionMapper auctionMapper;
	@Autowired
	private AuctionCustomMapper auctionCustomMapper;
	@Autowired
	private AuctionRecordMapper auctionRecordMapper;
	@Override
	public List<Auction> queryAuctions(Auction condition) {
		// TODO Auto-generated method stub
		AuctionExample example = new AuctionExample();
		Criteria criteria = example.createCriteria();
		if(condition!=null) {
			//商品名称，模糊查询
			if(condition.getAuctionname()!=null && !"".equals(condition.getAuctionname())) {
				criteria.andAuctionnameLike("%"+condition.getAuctionname()+"%");
			}
			//商品描述，模糊查询
			if(condition.getAuctiondesc()!=null && !"".equals(condition.getAuctiondesc())) {
				criteria.andAuctiondescLike("%"+condition.getAuctiondesc()+"%");
			}
			//开始时间>
			if(condition.getAuctionstarttime()!=null && !"".equals(condition.getAuctionstarttime())) {
				criteria.andAuctionstarttimeGreaterThan(condition.getAuctionstarttime());
			}
			//结束时间>
			if(condition.getAuctionendtime()!=null && !"".equals(condition.getAuctionendtime())) {
				criteria.andAuctionendtimeLessThan(condition.getAuctionendtime());
			}
			//竞拍价格
			if(condition.getAuctionstartprice()!=null && !"".equals(condition.getAuctionstartprice())) {
				criteria.andAuctionstartpriceGreaterThan(condition.getAuctionstartprice());
			}
		}
		//按开始时间进行排序
		example.setOrderByClause("auctionstarttime desc");
		List<Auction> list = auctionMapper.selectByExample(example);
		return list;
	}
	@Override
	public void addAuction(Auction auction) {
		auctionMapper.insert(auction);
		
	}
	@Override
	public Auction findAuctionById(int id) {
		// TODO Auto-generated method stub
		return auctionMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateAuction(Auction auction) {
		auctionMapper.updateByPrimaryKey(auction);
		
	}
	@Override
	public Auction findAuctionAndRecordAndUserById(int id) {
		Auction auction = auctionCustomMapper.findAuctionAndRecordAndUserById(id);
		return auction;
	}
	@Override
	public void addAuctionRecord(AuctionRecord record) throws Exception {
		//竞拍时间不能过期，首次竞价,不能低于起拍价，后续竞价，必须是所有价格最高价
		Auction auction = auctionCustomMapper.findAuctionAndRecordAndUserById(record.getAuctionid());
		//竞拍时间不能过期
		if(auction.getAuctionendtime().after(new Date())==false){   //过期
			throw new AuctionPriceException("商品的拍卖时间已过期");
			
		}else{
			//首次拍价，不能低于竞拍价
			if(auction.getAuctionRecordList().size()==0){
				if(record.getAuctionprice().doubleValue() < auction.getAuctionstartprice().doubleValue()){
					throw new AuctionPriceException("价格不能低于起拍价");
				}
			//后续竞价，必须是所有价格最高价
			}else{
				AuctionRecord maxRecord = auction.getAuctionRecordList().get(0);
				if(record.getAuctionprice().doubleValue() < maxRecord.getAuctionprice().doubleValue()){
					throw new AuctionPriceException("价格不能低于拍卖最高价");
				}
			}
		}
		
		//插入拍卖记录
		auctionRecordMapper.insert(record);
	}
	@Override
	public List<AuctionCustom> findAuctionResult() {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findEndAuction();
	}
	@Override
	public Auction findAuctionBuying() {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findBuyingAuction();
	}

}
