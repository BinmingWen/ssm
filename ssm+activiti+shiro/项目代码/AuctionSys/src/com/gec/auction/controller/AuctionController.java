package com.gec.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gec.auction.pojo.Auction;
import com.gec.auction.pojo.AuctionCustom;
import com.gec.auction.pojo.AuctionRecord;
import com.gec.auction.pojo.AuctionUser;
import com.gec.auction.service.AuctionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	private AuctionService auctionService;
	private final int PAGE_SIZE = 10;

	@RequestMapping("/queryAuctions")
	public ModelAndView queryAuctions(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Auction auction) {
		PageHelper.startPage(pageNum, PAGE_SIZE);
		List<Auction> list = auctionService.queryAuctions(auction);
		ModelAndView mv = new ModelAndView();
		PageInfo pageInfo = new PageInfo(list);
		mv.addObject("auctionList", list);
		mv.addObject("pageInfo", pageInfo);
		mv.setViewName("auctionList");
		return mv;
	}
	@RequestMapping("/submitUpdateAuction")
	public String submitUpdateAuction(Auction auction, MultipartFile pic, HttpSession session){
		try {
			if(pic.getSize()>0){
				String path = session.getServletContext().getRealPath("pic");
				
				File oldFile = new File(path,auction.getAuctionpic());
				if(oldFile.exists()){
					oldFile.delete();
				}
				File targetFile = new File(path, pic.getOriginalFilename());
				pic.transferTo(targetFile);
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.把商品数据保存到数据库中
		
		auctionService.updateAuction(auction);;
		return "redirect:/auction/queryAuctions";
	}
	@RequestMapping("/publishAuctions")
	public String publishAuctions(Auction auction, MultipartFile pic, HttpSession session) {
		try {
			//1.把二进制文件保存到目标的文件夹中
			//获取pic文件服务器路径
			String path = session.getServletContext().getRealPath("pic");
			File targetFile = new File(path, pic.getOriginalFilename());
			pic.transferTo(targetFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.把商品数据保存到数据库中
		auction.setAuctionpic(pic.getOriginalFilename());
		auction.setAuctionpictype(pic.getContentType());
		auctionService.addAuction(auction);
		return "redirect:/auction/queryAuctions";
	}
	@RequestMapping("/toDetail/{id}")
	public ModelAndView toDetail(@PathVariable int id){
		ModelAndView mv = new ModelAndView();
		Auction auction = auctionService.findAuctionById(id);
		mv.addObject("auction", auction);
		mv.setViewName("updateAuction");
		return mv;
	}
	@RequestMapping("/completePrice/{id}")
	public ModelAndView completePrice(@PathVariable int id){
		ModelAndView mv = new ModelAndView();
		Auction auction = auctionService.findAuctionAndRecordAndUserById(id);
		mv.addObject("auctionDetail", auction);
		mv.setViewName("auctionDetail");
		return mv;
		
	}
	@RequestMapping("/saveAuctionRecord")
	public String saveAuctionRecord(AuctionRecord record,HttpSession session) throws Exception{
		AuctionUser user = (AuctionUser)session.getAttribute("user");
		record.setUserid(user.getUserid());
		record.setAuctiontime(new Date());
		auctionService.addAuctionRecord(record);
		return "redirect:/completePrice/"+record.getAuctionid();
		
	}
	@RequestMapping("/findCompleteResult")
	public ModelAndView findCompleteResult(){
		System.out.println("........");
		ModelAndView mv = new ModelAndView();
		List<AuctionCustom> auctionList = auctionService.findAuctionResult();
		Auction auction = auctionService.findAuctionBuying();
		mv.addObject("auctionList", auctionList);
		mv.addObject("auction", auction);
		mv.setViewName("auctionResult");
		return mv;
	}

}
