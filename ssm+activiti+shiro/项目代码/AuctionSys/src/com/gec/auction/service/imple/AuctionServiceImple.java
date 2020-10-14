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
			//��Ʒ���ƣ�ģ����ѯ
			if(condition.getAuctionname()!=null && !"".equals(condition.getAuctionname())) {
				criteria.andAuctionnameLike("%"+condition.getAuctionname()+"%");
			}
			//��Ʒ������ģ����ѯ
			if(condition.getAuctiondesc()!=null && !"".equals(condition.getAuctiondesc())) {
				criteria.andAuctiondescLike("%"+condition.getAuctiondesc()+"%");
			}
			//��ʼʱ��>
			if(condition.getAuctionstarttime()!=null && !"".equals(condition.getAuctionstarttime())) {
				criteria.andAuctionstarttimeGreaterThan(condition.getAuctionstarttime());
			}
			//����ʱ��>
			if(condition.getAuctionendtime()!=null && !"".equals(condition.getAuctionendtime())) {
				criteria.andAuctionendtimeLessThan(condition.getAuctionendtime());
			}
			//���ļ۸�
			if(condition.getAuctionstartprice()!=null && !"".equals(condition.getAuctionstartprice())) {
				criteria.andAuctionstartpriceGreaterThan(condition.getAuctionstartprice());
			}
		}
		//����ʼʱ���������
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
		//����ʱ�䲻�ܹ��ڣ��״ξ���,���ܵ������ļۣ��������ۣ����������м۸���߼�
		Auction auction = auctionCustomMapper.findAuctionAndRecordAndUserById(record.getAuctionid());
		//����ʱ�䲻�ܹ���
		if(auction.getAuctionendtime().after(new Date())==false){   //����
			throw new AuctionPriceException("��Ʒ������ʱ���ѹ���");
			
		}else{
			//�״��ļۣ����ܵ��ھ��ļ�
			if(auction.getAuctionRecordList().size()==0){
				if(record.getAuctionprice().doubleValue() < auction.getAuctionstartprice().doubleValue()){
					throw new AuctionPriceException("�۸��ܵ������ļ�");
				}
			//�������ۣ����������м۸���߼�
			}else{
				AuctionRecord maxRecord = auction.getAuctionRecordList().get(0);
				if(record.getAuctionprice().doubleValue() < maxRecord.getAuctionprice().doubleValue()){
					throw new AuctionPriceException("�۸��ܵ���������߼�");
				}
			}
		}
		
		//����������¼
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
