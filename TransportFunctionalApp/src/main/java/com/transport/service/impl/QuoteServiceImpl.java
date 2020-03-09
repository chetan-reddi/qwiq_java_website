package com.transport.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.bean.AcceptBid;
import com.transport.bean.CalculateReq;
import com.transport.bean.CancelOrderReq;
import com.transport.bean.CancellQuoteReq;
import com.transport.bean.CompleteOrder;
import com.transport.bean.DistanceReq;
import com.transport.bean.EditQuote;
import com.transport.bean.OrderDetails;
import com.transport.bean.PaymentSuccess;
import com.transport.bean.QuoteDetails;
import com.transport.constant.EnumBidStatus;
import com.transport.constant.EnumOrderStatus;
import com.transport.constant.EnumPaymentStatus;
import com.transport.constant.EnumQuoteStatus;
import com.transport.constant.EnumTicketStatus;
import com.transport.constant.EnumUserRole;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.constant.TransportSuccessMessages;
import com.transport.constant.TransprotSuccessCodes;
import com.transport.exception.AuthenticationFailed;
import com.transport.exception.BidAlreadyPlaced;
import com.transport.exception.CapacityExceeds;
import com.transport.exception.FailToUpdatePaymentStatus;
import com.transport.exception.FaileToAcceptBid;
import com.transport.exception.FailedToSaveDetails;
import com.transport.exception.FailedToSaveTicketDetails;
import com.transport.exception.FailedToSaveWalletDetails;
import com.transport.exception.FailedToUpdateBidStatus;
import com.transport.exception.InvalidItemId;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidQuoteId;
import com.transport.exception.InvalidRole;
import com.transport.exception.OrderCreationFailed;
import com.transport.exception.OrderUpdationFailed;
import com.transport.exception.QuoteCancell;
import com.transport.exception.QuoteUpdationFailed;
import com.transport.exception.UnableToCompleteOrder;
import com.transport.model.AddItem;
import com.transport.model.Bid;
import com.transport.model.ItemRepo;
import com.transport.model.PartnerWallet;
import com.transport.model.Payment;
import com.transport.model.ProfileDetails;
import com.transport.model.Quote;
import com.transport.model.RatingAndReviews;
import com.transport.model.Refund;
import com.transport.model.SaveTicket;
import com.transport.model.Settings;
import com.transport.model.VanDetails;
import com.transport.repository.AddDriverRepo;
import com.transport.repository.BidRepo;
import com.transport.repository.DriverRepo;
import com.transport.repository.OrderDetailsRepo;
import com.transport.repository.PartnerWalletRepo;
import com.transport.repository.PaymentRepo;
import com.transport.repository.ProfileRepo;
import com.transport.repository.QuoteRepo;
import com.transport.repository.RatingAndReviewRepo;
import com.transport.repository.RefundRepo;
import com.transport.repository.SettingsRepo;
import com.transport.repository.TicketRepo;
import com.transport.repository.VanDetailsRepo;
import com.transport.response.BidAcceptResponse;
import com.transport.response.CalculateResponse;
import com.transport.response.CustomerOrder;
import com.transport.response.DistanceAndHours;
import com.transport.response.DistanceResp;
import com.transport.response.DistanceResponse;
import com.transport.response.DriverBids;
import com.transport.response.DriverBidsResponse;
import com.transport.response.EstimatedQutoeResp;
import com.transport.response.ItemResponse;
import com.transport.response.OrderDetailsResp;
import com.transport.response.OrderResponse;
import com.transport.response.Orders;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;
import com.transport.response.TransportResult;
import com.transport.response.VanDetailsList;
import com.transport.response.VanDetailsResp;
import com.transport.response.VanSizeCalculateResp;
import com.transport.service.QuoteService;
import com.transport.service.exception.OrderCancell;
import com.transport.util.CommonUtil;
import com.transport.util.UUIDUtil;

@Service
public class QuoteServiceImpl implements QuoteService{
	
	public static final Logger LOG = LoggerFactory.getLogger(QuoteServiceImpl.class);
	
	@Autowired
	OrderDetailsRepo orderRepo;
	@Autowired
	RefundRepo refundRepo;
	@Autowired
	TicketRepo ticketRepo;
	@Autowired
	PartnerWalletRepo walletRepo;
	@Autowired
	DriverRepo driverRepo;
	@Autowired
	ProfileRepo profileRepo;
	@Autowired
	RatingAndReviewRepo ratingRepo;
	@Autowired
	AddDriverRepo addDriverRepo;
	@Autowired
	QuoteRepo quoteRepo;
	@Autowired
	SettingsRepo settingsRepo;
	@Autowired
	PaymentRepo paymentRepo;
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	VanDetailsRepo vanDetailsRepo;
	@Autowired
	BidRepo bidRepo;
	@Autowired
	DriverServiceImpl driverService;
	@Override
	public OrderResponse createNewOrder(OrderDetails orderDetails, HttpServletRequest httpReq) throws OrderCreationFailed, IOException, InvalidLocation {
		TransportResult result = new TransportResult();
		OrderResponse response=new OrderResponse();
//		String userRefId=commonUtil.getCustomerUserRefIdI(httpReq);
//		orderDetails.setUserRefId(userRefId);
//		VanDetails vanDetails=getVanDetails(orderDetails.getVanSize());
//		//double pricePerMile=vanDetails.getPricePerMile();
//		double totalPrice;
//		DistanceReq req=new DistanceReq();
//		req.setPickUplattitude(orderDetails.getPickupLatitude());
//		req.setPickUplongitude(orderDetails.getPickupLongitude());
//		req.setDeliveryLattitude(orderDetails.getDropLatitude());
//		req.setDeliveryLongitude(orderDetails.getDropLongitude());
//		DistanceResponse distanceResp=driverService.getDistance(req);
//	//	double totalDistanceprice=distanceResp.getDistance()*pricePerMile;
//		//totalPrice=totalDistanceprice;
//	//	orderDetails.setEstimateValue(totalPrice);
//		orderDetails.setStatus(EnumOrderStatus.INITIATED.getStatusId());
//		orderDetails.setOrderId(UUIDUtil.randomUUID());
//		orderDetails.setDistance(distanceResp.getDistance());
//		try
//		{
//			OrderDetails resp=orderRepo.save(orderDetails);
//			result.setCode(TransprotSuccessCodes.ORDER_CREATED_SUCCESSFULLY);
//			result.setMessage(TransportSuccessMessages.ORDER_CREATED_SUCCESSFULLY);
//			response.setOrderDetails(resp);
//			response.setResult(result);
//		}
//		catch(Exception ex)
//		{
//			throw new OrderCreationFailed(TransportErrorMessages.ORDER_CREATION_FAILED);
//		}
		return response;
	}
	private VanDetails getVanDetails(long vanType) {
		return vanDetailsRepo.findById(vanType);
	}
//	@Override
//	public orderDetailsResponse getorderDetailss(HttpServletRequest httpReq) {
//		orderDetailsResponse response=new orderDetailsResponse();
//		List<orderDetails> orderDetailsResponse=new ArrayList<orderDetails>();
//		String userRefId=commonUtil.getUserRefIdI(httpReq);
//		List<OrderDetails> orderDetailsList=orderRepo.findByUserRefId(userRefId);
//		for(OrderDetails order:orderDetailsList)
//		{
//			orderDetails orderDetails=new orderDetails();
//			orderDetails.setCollectionCity(order.getCollectionCity());
//			orderDetails.setCollectionPostCode(order.getCollectionPostCode());
//			orderDetails.setCollectionStreetAddres(order.getCollectionStreetAddres());
//			orderDetails.setDeliveryAddress(order.getDeliveryAddress());
//			orderDetails.setDeliveryCity(order.getDeliveryCity());
//			orderDetails.setDeliveryPostCode(order.getDeliveryPostCode());
//			orderDetails.setDeliveryStreetAddres(order.getDeliveryStreetAddres());
//			orderDetails.setDistance(order.getDistance());
//			orderDetails.setEstimateValue(order.getEstimateValue());
//			orderDetails.setFormAddress(order.getFormAddress());
//			orderDetails.setHelpersCount(order.getHelpersCount());
//			orderDetails.setHoursNeeded(order.getHoursNeeded());
//			orderDetails.setName(order.getName());
//			orderDetails.setPhoneNumber(order.getPhoneNumber());
//			orderDetails.setPickUpDate(order.getPickUpDate());
//			orderDetails.setPickUpTime(order.getPickUpDate());
//			orderDetails.setorderId(order.getorderId());
//			String vehicleTYpe=EnumVehicleType.getStatus(order.getVanSize());
//			orderDetails.setVanSize(vehicleTYpe);
//			orderDetails.setorderDetailsStatus(EnumorderDetailsStatus.getStatus(order.getStatus()));
//			orderDetailsResponse.add(orderDetails);
//		}
//		response.setResult(orderDetailsResponse);
//		return response;
//	}
////	@Override
////	public BidResponse getBidsByorder(String orderId, HttpServletRequest httpReq) throws BidsNotFound {
////		BidResponse response=new BidResponse();
////		checkorderId(orderId);
////		List<Bid> bidsList=bidRepo.findByorderId(orderId);
////		response.setResult(bidsList);
////		return response;
////	}
//	private void checkorderId(String orderId) throws BidsNotFound {
//		
//		boolean result=bidRepo.checkorderIdExists(orderId);
//		if(!result)
//		{
//			throw new BidsNotFound(TransportErrorMessages.BIDS_NOT_FOUND);
//		}
//	}
	@Override
	public BidAcceptResponse acceptBid(AcceptBid acceptBidReq, HttpServletRequest httpReq) throws FaileToAcceptBid, BidAlreadyPlaced, FailedToUpdateBidStatus {
		BidAcceptResponse response=new BidAcceptResponse();
		TransportResult result=new TransportResult();
		checkBidAccept(acceptBidReq.getBidId());
		updateQuoteStatus(acceptBidReq);
		updateBidStatus(acceptBidReq);
		String orderId=createNewOrder(acceptBidReq,httpReq);
		result.setCode(TransprotSuccessCodes.ORDER_CREATED_SUCCESSFULLY);
		result.setMessage(TransportSuccessMessages.ORDER_CREATED_SUCCESSFULLY);
		response.setResult(result);
		response.setOrderId(orderId);
		return response;
	}
	private void checkBidAccept(String bidId) throws BidAlreadyPlaced {
	boolean result=orderRepo.checkBidIdExists(bidId);
	if(result)
	{
		throw new BidAlreadyPlaced(TransportErrorMessages.BID_ALREADY_PLACED);
	}
}
	private String createNewOrder(AcceptBid acceptBidReq, HttpServletRequest httpReq) {
		OrderDetails details=new OrderDetails();
		details.setBidId(acceptBidReq.getBidId());
		details.setOrderId(UUIDUtil.randomTenDigitsUUID());
		Bid bidDetails=bidRepo.findByBidId(acceptBidReq.getBidId());
		details.setTotalAmount(bidDetails.getTotalCost());
		details.setUserRefId(commonUtil.getCustomerUserRefIdI(httpReq));
		details.setPartnerId(bidDetails.getDriverUserId());
		details.setStatus(EnumOrderStatus.INITIATED.getStatusId());
		String orderId = null;
		try
		{
			OrderDetails orderDetails=orderRepo.save(details);
			orderId=orderDetails.getOrderId();
		}
		catch(Exception ex)
		{
			LOG.debug("Order creation failed : "+ex.getMessage());
		}
		return orderId;
	}
	private void updateQuoteStatus(AcceptBid acceptBidReq) {
		
		Quote quoteDetails=quoteRepo.findByQuoteId(acceptBidReq.getQuoteId());
		quoteDetails.setStatus(EnumQuoteStatus.ASSIGNED.getStatusId());
		try
		{
			quoteRepo.save(quoteDetails);
		}
		catch(Exception ex)
		{
			LOG.debug("Error occured : "+ex.getMessage());
		}
}
	private void updateBidStatus(AcceptBid acceptBidReq) throws FailedToUpdateBidStatus  {
		Bid bidDetails=bidRepo.findByBidId(acceptBidReq.getBidId());
		bidDetails.setStatus(EnumBidStatus.ACCEPT.getStatusId());
		try
		{
			bidRepo.save(bidDetails);
			updateDriverBidsStatus(acceptBidReq);
		}
		catch(Exception ex)
		{
			throw new FailedToUpdateBidStatus(TransportErrorMessages.FAILED_TO_UPDATE_BID_STATUS);
		}
		
	}
private void updateDriverBidsStatus(AcceptBid acceptBidReq) throws FailedToUpdateBidStatus {
		List<Bid> bidDetails=bidRepo.findByQuoteId(acceptBidReq.getQuoteId());
		for(Bid bid:bidDetails)
		{
			if(!(bid.getBidId().equals(acceptBidReq.getBidId())))
			{
				bid.setStatus(EnumBidStatus.COMPLETED.getStatusId());
				try
				{
					bidRepo.save(bidDetails);
				}
				catch(Exception ex)
				{
					throw new FailedToUpdateBidStatus(TransportErrorMessages.FAILED_TO_UPDATE_BID_STATUS);
				}	
			}
			
		}
	}
//	private void createOrder(AcceptBid acceptBidReq) throws OrderCreationFailed {
////		OrderDetails orderDetails=new OrderDetails();
////		OrderDetails orderDetails=orderRepo.findByorderId(acceptBidReq.getorderId());
////		Bid bidDetails=bidRepo.findByBidId(acceptBidReq.getBidId());
////		orderDetails.setOrderId(UUIDUtil.randomUUID());
////		orderDetails.setOrderAmount(Double.parseDouble(bidDetails.getBidAmount()));
////		orderDetails.setFromAddress(orderDetails.getFormAddress());
////		orderDetails.setToAddress(orderDetails.getDeliveryAddress());
////		orderDetails.setMoveDate(orderDetails.getPickUpDate());
////		orderDetails.setStatus(EnumOrderStatus.PLACED.getStatusId());
////		orderDetails.setCustomerId(orderDetails.getUserRefId());
////		orderDetails.setDriverId(bidDetails.getDriverUserId());
////		try {
////			orderRepo.save(orderDetails);
////		}
////		catch(Exception ex)
////		{
////			throw new OrderCreationFailed(TransportErrorMessages.ORDER_CREATION_FAILED);
////		}
////		
////		
//	}
	private void updateorderDetailsStatus(AcceptBid acceptBidReq) throws FaileToAcceptBid {
		
//		//OrderDetails orderDetails=orderRepo.findByOrderId(acceptBidReq.getOrderId());
//	//	orderDetails.setStatus(EnumOrderStatus.ACCEPT.getStatusId());
//		
//		try
//		{
//			Bid bidDetails=bidRepo.findByBidId(acceptBidReq.getBidId());
//			orderDetails.setDriverId(bidDetails.getDriverUserId());
//			orderRepo.save(orderDetails);
//			updateBidStatus(acceptBidReq);
//		}
//		catch(Exception ex)
//		{
//			throw new FaileToAcceptBid(TransportErrorMessages.FAILED_TO_ACCEPT_BID);
//		}
	}
	@Override
	public OrderDetailsResp getAllCustomerOrders(HttpServletRequest httpReq) {
		OrderDetailsResp response=new OrderDetailsResp();
		List<Orders> ordersList=new ArrayList<Orders>();
		String userRefId=commonUtil.getCustomerUserRefIdI(httpReq);
		List<OrderDetails> orderList=orderRepo.findByUserRefId(userRefId);
		for(OrderDetails order:orderList)
		{
			Orders orderDetails=new Orders();
			Bid bidDetails=bidRepo.findByBidId(order.getBidId());
			Quote quoteDetails=quoteRepo.findByQuoteId(bidDetails.getQuoteId());
			orderDetails.setCollectionPostCode(quoteDetails.getCollectionPostCode());
			orderDetails.setCollectionStreetAddres(quoteDetails.getCollectionStreetAddres());
			orderDetails.setDeliveryAddress(quoteDetails.getDeliveryAddress());
			orderDetails.setDeliveryCity(quoteDetails.getDeliveryCity());
			orderDetails.setDeliveryPostCode(quoteDetails.getDeliveryPostCode());
			orderDetails.setDeliveryStreetAddres(quoteDetails.getDeliveryStreetAddres());
			orderDetails.setDistance(quoteDetails.getDistance());
			orderDetails.setFormAddress(quoteDetails.getFormAddress());
			orderDetails.setHelpersCount(quoteDetails.getHelpersCount());
			orderDetails.setHoursNeeded(quoteDetails.getHoursNeeded());
			orderDetails.setName(quoteDetails.getName());
			orderDetails.setPhoneNumber(quoteDetails.getPhoneNumber());
			orderDetails.setPickUpDate(quoteDetails.getPickUpDate());
			orderDetails.setPickUpTime(quoteDetails.getPickUpDate());
			orderDetails.setOrderId(order.getOrderId());
			orderDetails.setVanSize(vanDetailsRepo.findById(quoteDetails.getVanSize()).getVanTypeName());
			orderDetails.setOrderStatus(EnumOrderStatus.getStatus(order.getStatus()));
			orderDetails.setVanId(quoteDetails.getVanSize());
			orderDetails.setAdditionalCharges(bidDetails.getAdditionalCharges());
			orderDetails.setAdditionalChargesDesc(bidDetails.getAdditionalChargesDesc());
			orderDetails.setAdditionalStopCharges(bidDetails.getAdditionalStopCharges());
			orderDetails.setAdditonalTimeChargesPerHour(bidDetails.getAdditonalTimeChargesPerHour());
			orderDetails.setBookingFee(bidDetails.getBookingFee());
			orderDetails.setMileageCharges(bidDetails.getMileageCharges());
			orderDetails.setOutOfHourCharges(bidDetails.getOutOfHourCharges());
			orderDetails.setPromotionalDiscount(bidDetails.getPromotionalDiscount());
			orderDetails.setTotalCost(bidDetails.getTotalCost());
			orderDetails.setVehicleId(bidDetails.getVehicleId());
			ordersList.add(orderDetails);
		}
		response.setResult(ordersList);
		return response;
}
	@Override
	public DriverBidsResponse getBidsByQuoteId(String quoteId) throws InvalidQuoteId {
		DriverBidsResponse response=new DriverBidsResponse();
		checkQuoteId(quoteId);
		List<DriverBids> driverBidsList=new ArrayList<DriverBids>();
		List<Bid> bidsList=bidRepo.findByQuoteId(quoteId);
		for(Bid bid:bidsList)
		{
			DriverBids driverBid=new DriverBids();
			driverBid.setAdditionalCharges(bid.getAdditionalCharges());
			driverBid.setAdditionalChargesDesc(bid.getAdditionalChargesDesc());
			driverBid.setAdditionalStopCharges(bid.getAdditionalStopCharges());
			driverBid.setAdditonalTimeChargesPerHour(bid.getAdditonalTimeChargesPerHour());
			driverBid.setBidAmount(bid.getBidAmount());
			driverBid.setBidId(bid.getBidId());
			driverBid.setBookingFee(bid.getBookingFee());
			driverBid.setComment(bid.getComment());
			driverBid.setCreatedDate(bid.getCreated_date());
			driverBid.setDriverCharges(bid.getDriverCharges());
			driverBid.setDriverUserId(bid.getDriverUserId());
			driverBid.setMileageCharges(bid.getMileageCharges());
			driverBid.setOutOfHourCharges(bid.getOutOfHourCharges());
			driverBid.setPromotionalDiscount(bid.getPromotionalDiscount());
			driverBid.setQuoteId(bid.getQuoteId());
			driverBid.setStatus(EnumBidStatus.getStatus(bid.getStatus()));
			driverBid.setTotalCost(bid.getTotalCost());
			driverBid.setVehicleId(bid.getVehicleId());
			if(checkProfileExists(bid.getDriverUserId()))
			{
				ProfileDetails driverProfile=profileRepo.findByUserId(bid.getDriverUserId());
				if(driverProfile.getRating()==null)
				{
					driverBid.setRating("0");
				}
				else
				{
					driverBid.setRating(driverProfile.getRating());
				}
				driverBid.setDriverORCompanyName(driverProfile.getFirstName());
			}
			//driverBid.setRating(driverProfile.getRating());
			driverBidsList.add(driverBid);
		}
		response.setResult(driverBidsList);
		return response;
		
	}
private boolean checkProfileExists(String driverUserId) {
	return profileRepo.checkDriverExists(driverUserId);
	}
private void checkQuoteId(String quoteId) throws InvalidQuoteId {
	boolean result=bidRepo.checkQuoteIdExists(quoteId);
	if(!result)
	{
		throw new InvalidQuoteId(TransportErrorMessages.INVALID_QUOTE_ID);
	}
		
	}
//	private void checkorderId(String orderId) throws InvalidOrderId {
//		boolean result=bidRepo.checkOrderIdExists(orderId);
//		if(!result)
//		{
//			throw new InvalidQuoteId(TransportErrorMessages.INVALID_ORDER_ID);
//		}
//		
//	}
	@Override
	public CustomerOrder getOrderDetails(String orderId) throws InvalidOrderId {
		CustomerOrder response=new CustomerOrder();
//		checkorderIdExists(orderId);
//		OrderDetails order=orderRepo.findByOrderId(orderId);
//		Orders orderDetails=new Orders();
//		orderDetails.setCollectionCity(order.getCollectionCity());
//		orderDetails.setCollectionPostCode(order.getCollectionPostCode());
//		orderDetails.setCollectionStreetAddres(order.getCollectionStreetAddres());
//		orderDetails.setDeliveryAddress(order.getDeliveryAddress());
//		orderDetails.setDeliveryCity(order.getDeliveryCity());
//		orderDetails.setDeliveryPostCode(order.getDeliveryPostCode());
//		orderDetails.setDeliveryStreetAddres(order.getDeliveryStreetAddres());
//		orderDetails.setDistance(order.getDistance());
//		orderDetails.setEstimateValue(order.getEstimateValue());
//		orderDetails.setFormAddress(order.getFormAddress());
//		orderDetails.setHelpersCount(order.getHelpersCount());
//		orderDetails.setHoursNeeded(order.getHoursNeeded());
//		orderDetails.setName(order.getName());
//		orderDetails.setPhoneNumber(order.getPhoneNumber());
//		orderDetails.setPickUpDate(order.getPickUpDate());
//		orderDetails.setPickUpTime(order.getPickUpDate());
//		orderDetails.setOrderId(order.getOrderId());
//		String vehicleTYpe=EnumVehicleType.getStatus(order.getVanSize());
//		orderDetails.setVanSize(vehicleTYpe);
//		orderDetails.setOrderStatus(EnumOrderStatus.getStatus(order.getStatus()));
//		response.setResult(orderDetails);
		return response;
	}
	private void checkorderIdExists(String orderId) throws InvalidOrderId {
		boolean result=orderRepo.checkOrderIdExists(orderId);
		if(!result)
		{
			throw new InvalidOrderId(TransportErrorMessages.INVALID_ORDER_ID);
		}
	}
	@Override
	public EstimatedQutoeResp getEstimatedQuote(QuoteDetails quoteReq) throws IOException, InvalidLocation { 
		EstimatedQutoeResp resp=new EstimatedQutoeResp();
		DistanceResponse price=calculateEsitmatedPrice(quoteReq);
		DecimalFormat dec = new DecimalFormat("#0.00");
		resp.setEstimatedPrice(dec.format(price.getPrice()));
		resp.setPickUpDate(quoteReq.getPickUpDate());
		resp.setPickUpTime(quoteReq.getPickUpTime());
		resp.setDistance(dec.format(price.getDistance()));
		resp.setBookingFee(dec.format(price.getBookingFee()));
		resp.setDiscount(dec.format(price.getDiscount()));
		resp.setTotalPrice(dec.format(price.getTotalPrice()));
		resp.setDriverCharges(dec.format(price.getDriverCharges()));
		resp.setHelpersCharges(dec.format(price.getHelpersCharges()));
		resp.setHoursNeededVehicle(price.getHoursNeededVehicle());
		resp.setMileageCharges(dec.format(price.getMileageCharges()));
		return resp;
	}
	private DistanceResponse calculateEsitmatedPrice(QuoteDetails quoteReq) throws IOException, InvalidLocation {
		
		DistanceReq req=new DistanceReq();
		req.setPickUplatitude(quoteReq.getPickupLatitude());
		req.setPickUplongitude(quoteReq.getPickupLongitude());
		req.setDeliveryLatitude(quoteReq.getDropLatitude());
		req.setDeliveryLongitude(quoteReq.getDropLongitude());
		DistanceResponse response=getTotalEstimatedPrice(quoteReq,req);
		return response;
	}
	
	private DistanceResponse getTotalEstimatedPrice(QuoteDetails quoteReq,DistanceReq req) throws IOException, InvalidLocation {
	DistanceResponse distanceResp=driverService.getDistance(req);
	double totalDistancePrice=getTotalDistancePrice(distanceResp,quoteReq);
	double totalDriverHoursPrice=getTotalDriverHoursPrice(distanceResp,quoteReq);
	double totalHelpersPrice=getTotalHelpersPrice(quoteReq.getHelpersCount());
	double bookingFee=getTotalBookingFee();
	double totalDiscountPrice=getTotalDiscountprice(totalDistancePrice,totalDriverHoursPrice,totalHelpersPrice);
	double estimatedQuotePrice=getTotalEstimatedQuotePrice(totalDistancePrice,totalDriverHoursPrice,totalHelpersPrice);
	double finalQuotePrice=discountQuotePrice(totalDiscountPrice,estimatedQuotePrice,bookingFee);
	distanceResp.setPrice(estimatedQuotePrice);
	distanceResp.setBookingFee(bookingFee);
	distanceResp.setDiscount(totalDiscountPrice);
	distanceResp.setTotalPrice(finalQuotePrice);
	distanceResp.setHelpersCharges(totalHelpersPrice);
	int minutesNeeded=quoteReq.getHoursNeeded();
	int hours=minutesNeeded/60;
	int minutes=minutesNeeded%60;
	distanceResp.setHoursNeededVehicle(hours+"hrs"+minutes+"minutes");
	distanceResp.setDriverCharges(totalDriverHoursPrice);
	distanceResp.setMileageCharges(totalDistancePrice);
	return distanceResp;
	}
	private double discountQuotePrice(double totalDiscountPrice, double estimatedQuotePrice,double bookingFee) {
		double discountQuotePrice=estimatedQuotePrice-totalDiscountPrice+bookingFee;
		return discountQuotePrice;
	}
	private double getTotalEstimatedQuotePrice(double totalDistancePrice, double totalDriverHoursPrice,
			double totalHelpersPrice) {
		double totalEstimatedPrice=totalDistancePrice+totalDriverHoursPrice+totalHelpersPrice;
		return totalEstimatedPrice;
	}
	private double getTotalHelpersPrice(int helpersCount) {
		Settings settingsDetails=settingsRepo.findByType("HelperPrice");
		double totalHelpersPrice=settingsDetails.getPricePerDay()+helpersCount;
		return totalHelpersPrice;
	}
	private double getTotalDiscountprice(double totalDistancePrice, double totalDriverHoursPrice, double totalHelpersPrice) {
		double totalPrice=totalDistancePrice+totalDriverHoursPrice+totalHelpersPrice;
		Settings settingsDetails=settingsRepo.findByType("HelperPrice");
		double discountPricePercent=settingsDetails.getPromotionalDiscount();
		double discountPrice=totalPrice*discountPricePercent*0.01;
		return discountPrice;
	}
	private double getTotalBookingFee() {
		Settings settingsDetails=settingsRepo.findByType("HelperPrice");
		double bookingFee=settingsDetails.getBookingFee();
		return bookingFee;
	}
	private double getTotalDriverHoursPrice(DistanceResponse distanceResp, QuoteDetails quoteReq) {
		double driverCharges=getDriverCharges(quoteReq);
		int hoursNeeded=quoteReq.getHoursNeeded();
		int hours=hoursNeeded/60;
		int minutes=hoursNeeded%60;
		Double travelTimeInhours=new Double(hours);
		Double travelTimeInMinutes=new Double(minutes);
		double totalDriverHourCharges=travelTimeInhours*driverCharges;
		double totalDriverMinuteCharges=(travelTimeInMinutes/60)*driverCharges;
		double totaldriverCharges=totalDriverHourCharges+totalDriverMinuteCharges;
		return totaldriverCharges;
	}
	private double getTotalDistancePrice(DistanceResponse distanceResp, QuoteDetails quoteReq) {
		double pricePerMile=getMileCharges(quoteReq);
		double totalDistanceprice=distanceResp.getDistance()*pricePerMile;
		return totalDistanceprice;
	}
	private double getDriverCharges(QuoteDetails quoteReq) {
		VanDetails vanDetails=vanDetailsRepo.findById(quoteReq.getVanSize());
		if(getDayOfWeek(quoteReq).equals("Saturday") || getDayOfWeek(quoteReq).equals("Sunday"))
		{
			double driverCharges=vanDetails.getDriverChargePerHourWeekDays();
			return driverCharges;
		}
		else
		{
			double driverCharges=vanDetails.getDriverChargePerHourNormalDays();
			return driverCharges;
		}
		
	}
	private String getDayOfWeek(QuoteDetails quoteReq) {
		 SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week abbreviated
	     return simpleDateformat.format(quoteReq.getPickUpDate());
	}
	private double getMileCharges(QuoteDetails quoteReq) {
		VanDetails vanDetails=vanDetailsRepo.findById(quoteReq.getVanSize());
		if(getDayOfWeek(quoteReq).equals("Saturday") || getDayOfWeek(quoteReq).equals("Sunday"))
		{
			double pricePerMile=vanDetails.getPricePerMileWeekDays();
			return pricePerMile;
		}
		else
		{
			double pricePerMile=vanDetails.getPricePerMileNormalDays();
			return pricePerMile;
		}
		
	}
	private double pricePerHelper() {
		return 10;
		
	}
	@Override
	public OrderResponse editOrder(OrderDetails orderDetailsReq, HttpServletRequest httpReq) throws IOException, InvalidLocation, OrderUpdationFailed, AuthenticationFailed, InvalidOrderId {
		
		TransportResult result = new TransportResult();
		OrderResponse response=new OrderResponse();
//		checkorderIdExists(orderDetailsReq.getOrderId());
//		OrderDetails orderDetails=orderRepo.findByOrderId(orderDetailsReq.getOrderId());
//		checkOrderStatus(orderDetails.getStatus());
//		String userRefId=commonUtil.getCustomerUserRefIdI(httpReq);
//		checkValidUser(orderDetails.getUserRefId(),userRefId);
//		orderDetails.setUserRefId(orderDetails.getUserRefId());
//		VanDetails vanDetails=getVanDetails(orderDetailsReq.getVanSize());
//		//double pricePerMile=vanDetails.getPricePerMile();
//		double totalPrice;
//		DistanceReq req=new DistanceReq();
//		req.setPickUplattitude(orderDetailsReq.getPickupLatitude());
//		req.setPickUplongitude(orderDetailsReq.getPickupLongitude());
//		req.setDeliveryLattitude(orderDetailsReq.getDropLatitude());
//		req.setDeliveryLongitude(orderDetailsReq.getDropLongitude());
//		DistanceResponse distanceResp=driverService.getDistance(req);
//	//	double totalDistanceprice=distanceResp.getDistance()*pricePerMile;
//	//	totalPrice=totalDistanceprice;
//	//	orderDetails.setEstimateValue(totalPrice);
//		orderDetails.setStatus(EnumOrderStatus.PLACED.getStatusId());
//		orderDetails.setOrderId(orderDetailsReq.getOrderId());
//		orderDetails.setCollectionCity(orderDetailsReq.getCollectionCity());
//		orderDetails.setCollectionPostCode(orderDetailsReq.getCollectionPostCode());
//		orderDetails.setCollectionStreetAddres(orderDetailsReq.getCollectionStreetAddres());
//		orderDetails.setDeliveryAddress(orderDetailsReq.getDeliveryAddress());
//		orderDetails.setDeliveryCity(orderDetailsReq.getDeliveryCity());
//		orderDetails.setDeliveryPostCode(orderDetailsReq.getDeliveryPostCode());
//		orderDetails.setDeliveryStreetAddres(orderDetailsReq.getDeliveryStreetAddres());
//		orderDetails.setDropLatitude(orderDetailsReq.getDropLatitude());
//		orderDetails.setDropLongitude(orderDetailsReq.getDropLongitude());
//		orderDetails.setPickupLatitude(orderDetailsReq.getPickupLatitude());
//		orderDetails.setPickupLongitude(orderDetailsReq.getPickupLongitude());
//		orderDetails.setPickUpDate(orderDetailsReq.getPickUpDate());
//		orderDetails.setPickUpTime(orderDetailsReq.getPickUpTime());
//		orderDetails.setDistance(orderDetailsReq.getDistance());
//		orderDetails.setFormAddress(orderDetailsReq.getFormAddress());
//		orderDetails.setVanSize(orderDetailsReq.getVanSize());
//		orderDetails.setHelpersCount(orderDetailsReq.getHelpersCount());
//		orderDetails.setHoursNeeded(orderDetailsReq.getHoursNeeded());
//		orderDetails.setEmail(orderDetailsReq.getEmail());
//		orderDetails.setName(orderDetailsReq.getName());
//		orderDetails.setPhoneNumber(orderDetailsReq.getPhoneNumber());
//		orderDetails.setComment(orderDetailsReq.getComment());
//		try
//		{
//			OrderDetails resp=orderRepo.save(orderDetails);
//			result.setCode(TransprotSuccessCodes.ORDER_UPDATED_SUCCESSFULLY);
//			result.setMessage(TransportSuccessMessages.ORDER_UPDATED_SUCCESSFULLY);
//			response.setOrderDetails(resp);
//			response.setResult(result);
//		}
//		catch(Exception ex)
//		{
//			throw new OrderUpdationFailed(TransportErrorMessages.ORDER_UPDATION_FAILED);
//		}
		return response;
	}
	private void checkOrderStatus(int status) throws OrderUpdationFailed {
//		if(!(status==EnumOrderStatus.PLACED.getStatusId()))
//		{
//			throw new OrderUpdationFailed(TransportErrorMessages.UNABLE_TO_EDIT_ORDER);
//		}
		
	}
	private void checkValidUser(String userRefId, String userRefId2) throws AuthenticationFailed {
		if(!(userRefId.equals(userRefId2)))
		{
			throw new AuthenticationFailed(TransportErrorMessages.UNAUTHENTICATED_USER);
		}
		
	}
	@Override
	public TransportResponse cancellOrder(CancelOrderReq cancelReq, HttpServletRequest httpReq) throws InvalidOrderId, OrderCancell {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkorderIdExists(cancelReq.getOrderId());
		OrderDetails orderDetails=orderRepo.findByOrderId(cancelReq.getOrderId());
		if((orderDetails.getStatus()==EnumOrderStatus.INITIATED.getStatusId()) || (orderDetails.getStatus()==EnumOrderStatus.CONFIRMED.getStatusId()))
		{
			if(cancelReq.getStatus()==EnumOrderStatus.CANCELLED_BY_USER.getStatusId())
			{
					int status=orderDetails.getStatus();
					orderDetails.setStatus(cancelReq.getStatus());
					orderDetails.setReason(cancelReq.getReason());
					orderDetails.setCancellationId(generateQuoteId());
					try
					{
						
						orderRepo.save(orderDetails);
						if(status==EnumOrderStatus.CONFIRMED.getStatusId())
						{
							checkHours(orderDetails);
						}
						result.setCode(TransportErrorCodes.CANCELLED_ORDER_SUCCESSFULLY);
						result.setMessage(TransportErrorMessages.CANCELLED_ORDER_SUCCESSFULLY);
						response.setResult(result);
					}
					catch(Exception ex)
					{
						throw new OrderCancell(TransportErrorMessages.UNABLE_TO_CANCELL_ORDER);
					}
			}
			else
			{
				throw new OrderCancell(TransportErrorMessages.CANCELLATION_FAILED);
			}
		}
		else if(orderDetails.getStatus()==EnumOrderStatus.CANCELLED_BY_USER.getStatusId())
		{
			throw new OrderCancell(TransportErrorMessages.ORDER_ALREADY_CANCELLED);
		}
		else
		{
			throw new OrderCancell(TransportErrorMessages.UNABLE_TO_CANCELL_ORDER);
		}
		return response;
	}
	private void checkHours(OrderDetails orderId) throws ParseException, FailedToSaveDetails {
		String bidId=orderId.getBidId();
		Bid bidDetails=bidRepo.findByBidId(bidId);
		Quote quoteDetails=quoteRepo.findByQuoteId(bidDetails.getQuoteId());
		Date pickUpDate=quoteDetails.getPickUpDate();
		DateFormat dateFormateer = new SimpleDateFormat("yyyy-mm-dd");
		String pickUpDay=dateFormateer.format(pickUpDate);
		String pickUpTime=quoteDetails.getPickUpTime();
		String pickUpdayTime=pickUpDay+" "+pickUpTime;
		Date currentDate=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		String strDate = dateFormat.format(currentDate);
		Date d1 = null;
		Date d2 = null;
		d1=dateFormat.parse(strDate);
		d2=dateFormat.parse(pickUpdayTime);
		long diff = d2.getTime() - d1.getTime();
		//long diffHours = diff / (60 * 60 * 1000);
		DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");
		int diffhours = (int) (diff / (60 * 60 * 1000));
		if(diffhours>=48)
		{
			refundAmount(orderId);
		}
		
	}
	private void refundAmount(OrderDetails orderDetails) throws FailedToSaveDetails {
		Refund refund=new Refund();
		refund.setOrderId(orderDetails.getOrderId());
		refund.setAmount(orderDetails.getAdvanceAmount());
		refund.setCancellationId(orderDetails.getCancellationId());
		try
		{
			refundRepo.save(refund);
		}
		catch(Exception ex)
		{
			throw new FailedToSaveDetails(TransportErrorMessages.FAILED_TO_SAVE_DETAILS);
		}
	}
	@Override
	public VanSizeCalculateResp vanSizeCalculator(CalculateReq calculateReq) throws InvalidItemId, CapacityExceeds {
		
		VanSizeCalculateResp  response=new VanSizeCalculateResp();
		CalculateResponse calculate=new CalculateResponse();
		double totalVolume = 0;
		for(String itemId:calculateReq.getItemIds())
		{
			checkItemExists(itemId);
			AddItem itemDetails=itemRepo.findByItemId(itemId);
			totalVolume=totalVolume+itemDetails.getVolume();
		}
		Iterable<VanDetails> vanDetails=vanDetailsRepo.findAll();
		List<VanDetails> list=new ArrayList<VanDetails>();
		for(VanDetails details:vanDetails)
		{
			list.add(details);
		}
		Collections.sort(list, new Comparator<VanDetails>() {
		    @Override
		    public int compare(VanDetails c1, VanDetails c2) {
		        return Double.compare(c1.getVolume(), c2.getVolume());
		    }
		});
		for(VanDetails v:list)
		{
			boolean flag = false;
			if(totalVolume<v.getVolume())
			{
				calculate.setEstimatedVanSize(v.getVanTypeName());
				calculate.setVanCapacity(v.getVolume());
				calculate.setTotalItemsVolume(totalVolume);
				response.setResult(calculate);
				flag = true;
			}
			if(flag)
			{
				break;
			}
		}
		TransportErrorResp error=new TransportErrorResp();
		error.setErrorCode(TransportErrorCodes.CAPACITY_EXCEEDS);
		error.setErrorMessage(TransportErrorMessages.CAPACITY_EXCEEDS);
		response.setError(error);
		return response;
		
	}
	private void checkItemExists(String itemId) throws InvalidItemId {
		boolean result=itemRepo.checkItemIdExists(itemId);
		if(!result)
		{
			throw new InvalidItemId(TransportErrorMessages.INVALID_ITEM_ID);
		}
	}
	@Override
	public ItemResponse getAllItems() {
		ItemResponse response=new ItemResponse();
		Iterable<AddItem> items=itemRepo.findAll();
		response.setResult(items);
		return response;
	}
	@Override
	public VanDetailsResp getVanDetails() {
		VanDetailsResp response=new VanDetailsResp();
		List<VanDetailsList> vansList=new ArrayList<VanDetailsList>();
		Iterable<VanDetails> vanDetailsList=vanDetailsRepo.findAll();
		for(VanDetails vanDetails:vanDetailsList)
		{
			VanDetailsList vanList=new VanDetailsList();
			vanList.setVanId(vanDetails.getId());
			vanList.setVanTypeName(vanDetails.getVanTypeName());
			vanList.setHeight(vanDetails.getHeight());
			vanList.setLength(vanDetails.getLength());
			vanList.setWidth(vanDetails.getWidth());
			vanList.setMaximumWeight(vanDetails.getMaximumWeight());
			vanList.setMinimumWeight(vanDetails.getMinimumWeight());
			vanList.setSeats(vanDetails.getNumberOfSeats());
			vansList.add(vanList);
		}
		response.setResult(vansList);
		return response;
	}
	@Override
	public void placeOrder(OrderDetails quoteDetails, HttpServletRequest httpReq) {
		
		
	}
	@Override
	public TransportResponse paymentSuccess(PaymentSuccess req) throws FailToUpdatePaymentStatus, InvalidOrderId {
		TransportResponse response=new TransportResponse();
		checkorderIdExists(req.getOrderId());
		OrderDetails orderDetails=orderRepo.findByOrderId(req.getOrderId());
		orderDetails.setStatus(EnumOrderStatus.CONFIRMED.getStatusId());
		orderDetails.setAdvanceAmount(req.getAmount());
		try
		{
			orderRepo.save(orderDetails);
			Payment payment=new Payment();
			payment.setOrderId(req.getOrderId());
			payment.setTransactionId(req.getTransactionId());
			payment.setAmount(req.getAmount());
			paymentRepo.save(payment);
			TransportResult result=new TransportResult();
			result.setCode(TransprotSuccessCodes.PAYMENT_UPDATED_SCUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.PAYMENT_UPDATED_SCUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			LOG.debug(ex.getMessage());
		throw new FailToUpdatePaymentStatus(TransportErrorMessages.FAILED_TO_UPDATE_PAYMENT_STATUS);
		}
		return response;
	}
	@Override
	public TransportResponse saveQuote(Quote quoteReq,HttpServletRequest httpReq) throws FailedToSaveDetails {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		String quoteId=generateQuoteId();
		String userRefId=commonUtil.getCustomerUserRefIdI(httpReq);
		try
		{
			quoteReq.setStatus(EnumQuoteStatus.PLACED.getStatusId());
			quoteReq.setUserRefId(userRefId);
			quoteReq.setQuoteId(quoteId);
			quoteRepo.save(quoteReq);
			result.setCode(TransprotSuccessCodes.QUOTE_SAVED_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.QUOTE_SAVED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new FailedToSaveDetails(TransportErrorMessages.FAILED_TO_SAVE_DETAILS);
		}
		return response;
	}
	private String generateQuoteId() {
	  
	        // chose a Character random from this String 
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz"; 
	  
	        // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder("10"); 
	  
	        for (int i = 0; i <10; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(AlphaNumericString.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(AlphaNumericString 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 
		
	}
	@Override
	public List<QuoteDetails> getQuote(HttpServletRequest httpReq) {
		String userRefId=commonUtil.getCustomerUserRefIdI(httpReq);
		List<QuoteDetails> quotesList=new ArrayList<QuoteDetails>();
		List<Quote> quoteDetails=quoteRepo.getQuotesByUser(userRefId);
		for(Quote quote:quoteDetails)
		{
			QuoteDetails details=new QuoteDetails();
			details.setCollectionCity(quote.getCollectionCity());
			details.setCollectionPostCode(quote.getCollectionPostCode());
			details.setCollectionStreetAddres(quote.getCollectionStreetAddres());
			details.setComment(quote.getComment());
			details.setDeliveryAddress(quote.getDeliveryAddress());
			details.setDeliveryCity(quote.getDeliveryCity());
			details.setDeliveryPostCode(quote.getDeliveryPostCode());
			details.setDeliveryStreetAddres(quote.getDeliveryStreetAddres());
			details.setDistance(quote.getDistance());
			details.setDropLatitude(quote.getDropLatitude());
			details.setDropLongitude(quote.getDropLongitude());
			details.setEmail(quote.getEmail());
			details.setFormAddress(quote.getFormAddress());
			details.setHelpersCount(quote.getHelpersCount());
			details.setHoursNeeded(quote.getHoursNeeded());
			details.setName(quote.getName());
			details.setPhoneNumber(quote.getPhoneNumber());
			details.setPickUpDate(quote.getPickUpDate());
			details.setPickupLatitude(quote.getPickupLatitude());
			details.setPickupLongitude(quote.getPickupLongitude());
			details.setPickUpTime(quote.getPickUpTime());
			details.setVanSize(quote.getVanSize());
			details.setPrice(quote.getPrice());
			details.setBookingFee(quote.getBookingFee());
			details.setDiscountFee(quote.getDiscountAmount());
			details.setTotalPrice(quote.getTotalPrice());
			details.setQuoteStatus(EnumQuoteStatus.getStatus(quote.getStatus()));
			details.setQuoteId(quote.getQuoteId());
			VanDetails vandetails=vanDetailsRepo.findById(quote.getVanSize());
			details.setVanType(vandetails.getVanTypeName());
			quotesList.add(details);
		}
		return quotesList;
	}
	@Override
	public void getAllQutoes(HttpServletRequest httpReq) {
		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
		
	}
	@Override
	public TransportResponse editQuote(Quote quoteReq, HttpServletRequest httpReq) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TransportResponse editQuote(EditQuote editQuote, HttpServletRequest httpReq) throws InvalidQuoteId, QuoteUpdationFailed {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkQuoteExists(editQuote.getQuoteId());
		Quote quoteDetails=quoteRepo.findByQuoteId(editQuote.getQuoteId());
		//checkValidUser(orderDetails.getUserRefId(),userRefId);
		quoteDetails.setStatus(EnumQuoteStatus.PLACED.getStatusId());
		quoteDetails.setCollectionCity(editQuote.getCollectionCity());
		quoteDetails.setCollectionPostCode(editQuote.getCollectionPostCode());
		quoteDetails.setCollectionStreetAddres(editQuote.getCollectionStreetAddres());
		quoteDetails.setDeliveryAddress(editQuote.getDeliveryAddress());
		quoteDetails.setDeliveryCity(editQuote.getDeliveryCity());
		quoteDetails.setDeliveryPostCode(editQuote.getDeliveryPostCode());
		quoteDetails.setDeliveryStreetAddres(editQuote.getDeliveryStreetAddres());
		quoteDetails.setDropLatitude(editQuote.getDropLatitude());
		quoteDetails.setDropLongitude(editQuote.getDropLongitude());
		quoteDetails.setPickupLatitude(editQuote.getPickupLatitude());
		quoteDetails.setPickupLongitude(editQuote.getPickupLongitude());
		quoteDetails.setPickUpDate(editQuote.getPickUpDate());
		quoteDetails.setPickUpTime(editQuote.getPickUpTime());
		quoteDetails.setDistance(editQuote.getDistance());
		quoteDetails.setFormAddress(editQuote.getFormAddress());
		quoteDetails.setVanSize(editQuote.getVanSize());
		quoteDetails.setHelpersCount(editQuote.getHelpersCount());
		quoteDetails.setHoursNeeded(editQuote.getHoursNeeded());
		quoteDetails.setEmail(editQuote.getEmail());
		quoteDetails.setName(editQuote.getName());
		quoteDetails.setPhoneNumber(editQuote.getPhoneNumber());
		quoteDetails.setComment(editQuote.getComment());
		quoteDetails.setBookingFee(editQuote.getBookingFee());
		quoteDetails.setPrice(editQuote.getPrice());
		quoteDetails.setTotalPrice(editQuote.getTotalPrice());
		quoteDetails.setDiscountAmount(editQuote.getDiscountFee());
		try
		{
			quoteRepo.save(quoteDetails);
			result.setCode(TransprotSuccessCodes.QUOTE_UPDATED_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.QUOTE_UPDATED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new QuoteUpdationFailed(TransportErrorMessages.QUOTE_UPDATION_FAILED);
		}
		return response;
	}
	private void checkQuoteExists(String quoteId) throws InvalidQuoteId {
		boolean result=quoteRepo.checkQuoteIdExists(quoteId);
		if(!result)
		{
			throw new InvalidQuoteId(TransportErrorMessages.INVALID_QUOTE_ID);
		}
	}
	@Override
	public TransportResponse cancellQuote(CancellQuoteReq cancelReq, HttpServletRequest httpReq) throws InvalidQuoteId, QuoteCancell {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkQuoteExists(cancelReq.getQuoteId());
		Quote quoteDetails=quoteRepo.findByQuoteId(cancelReq.getQuoteId());
		if(quoteDetails.getStatus()==EnumQuoteStatus.PLACED.getStatusId())
		{
			if(cancelReq.getStatus()==EnumQuoteStatus.CANCELLED_BY_CUSTOMER.getStatusId())
			{
				quoteDetails.setStatus(cancelReq.getStatus());
				quoteDetails.setReason(cancelReq.getReason());
				try
			{
					
					quoteRepo.save(quoteDetails);
					result.setCode(TransportErrorCodes.CANCELLED_QUOTE_SUCCESSFULLY);
					result.setMessage(TransportErrorMessages.CANCELLED_QUOTE_SUCCESSFULLY);
					response.setResult(result);
			}
			catch(Exception ex)
			{
				throw new QuoteCancell(TransportErrorMessages.UNABLE_TO_CANCELL_QUOTE);
			}
				
			}
			else
			{
				throw new QuoteCancell(TransportErrorMessages.CANCELLATION_FAILED);
			}
		}
		else
		{
			throw new QuoteCancell(TransportErrorMessages.UNABLE_TO_CANCELL_QUOTE);
		}
		return response;
	}
	@Override
	public TransportResponse completeOrder(CompleteOrder req) throws InvalidOrderId, UnableToCompleteOrder {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkorderIdExists(req.getOrderId());
		OrderDetails orderDetails=orderRepo.findByOrderId(req.getOrderId());
		orderDetails.setStatus(EnumOrderStatus.COMPLETED.getStatusId());
		try
		{
			orderRepo.save(orderDetails);
			//transferFundsToPartner(req.getOrderId());
			RatingAndReviews reviews=new RatingAndReviews();
			reviews.setOrderId(req.getOrderId());
			reviews.setPartnerId(orderDetails.getPartnerId());
			reviews.setRating(req.getRating());
			reviews.setReviews(req.getReview());
			ratingRepo.save(reviews);
			double rating=calculateOverallRating(req.getOrderId());
			ProfileDetails profileDetails=profileRepo.findByUserId(orderDetails.getPartnerId());
			profileDetails.setRating(String.valueOf(rating));
			profileRepo.save(profileDetails);
			result.setCode(TransportErrorCodes.ORDER_COMPLETED_SUCCESSFULLY);
			result.setMessage(TransportErrorMessages.ORDER_COMPLETED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new UnableToCompleteOrder(TransportErrorMessages.UNABLE_TO_COMPLETE_ORDER);
		}
		return response;
	}
	private void transferFundsToPartner(String orderId) throws FailedToSaveWalletDetails {
		OrderDetails orderDetails=orderRepo.findByOrderId(orderId);
		double advanceAmount=orderDetails.getAdvanceAmount();
		Settings settingsDetails=settingsRepo.findByType("HelperPrice");
		double bookingCommission=settingsDetails.getBookingCommission();
		double commission=advanceAmount*bookingCommission*0.01;
		double remainingAmount=advanceAmount-commission;
		PartnerWallet wallet=new PartnerWallet();
		wallet.setAmount(remainingAmount);
		wallet.setOrderId(orderId);
		wallet.setPartnerId(orderDetails.getPartnerId());
		wallet.setStatus(EnumPaymentStatus.PENDING.getStatusId());
		try
		{
			walletRepo.save(wallet);
		}
		catch(Exception ex)
		{
			throw new FailedToSaveWalletDetails(TransportErrorMessages.FAILED_TO_SAVE_WALLET_DETAILS);
		}
		
	}
	private double calculateOverallRating(String orderId) {
		OrderDetails orderDetails=orderRepo.findByOrderId(orderId);
		String partnerId=orderDetails.getPartnerId();
		double rating= ratingRepo.ratingAndReview(partnerId);
		return rating;
	}
	@Override
	public DistanceResp getDistance(DistanceReq distanceReq) throws IOException, InvalidLocation {
	
		DistanceResp response=new DistanceResp();
		DistanceAndHours result=new DistanceAndHours();
		DistanceResponse distanceResp=driverService.getDistance(distanceReq);
		DecimalFormat dec = new DecimalFormat("#0.00");
		result.setDistance(dec.format(distanceResp.getDistance()));
		result.setTime(distanceResp.getTimeInseconds());
		response.setResult(result);
		return response;
	}
	@Override
	public TransportResponse saveTicket(SaveTicket ticketReq, HttpServletRequest httpReq) throws FailedToSaveTicketDetails {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		try
		{
			ticketReq.setTicketId(UUIDUtil.randomTenDigitsUUID());
			ticketReq.setTicketStatus(EnumTicketStatus.PLACED.getStatusId());
			if(EnumUserRole.CUSTOMER.getStatusId()==ticketReq.getRaisedBy())
			{
				ticketReq.setUserId(commonUtil.getCustomerUserRefIdI(httpReq));
				
			}
			else if(EnumUserRole.DRIVER.getStatusId()==ticketReq.getRaisedBy())
			{
				ticketReq.setUserId(commonUtil.getDrivrUserRefIdI(httpReq));

			}
			else
			{
				throw new InvalidRole(TransportErrorMessages.INVALID_ROLE);
			}
			ticketRepo.save(ticketReq);
			result.setCode(TransportErrorCodes.TICKET_SAVE_SUCCESSFULLY);
			result.setMessage(TransportErrorMessages.TICKET_SAVE_SUCCESSFULLY);
			response.setResult(result);

		}
		catch(Exception ex)
		{
			throw new FailedToSaveTicketDetails(TransportErrorMessages.FAILED_TO_SAVED_TICKET_DETAILS);
		}
		return response;
	}
	@Override
	public void getTickets(HttpServletRequest httpReq) {
		String role=commonUtil.getUserRole(httpReq);
		if(role.equals(EnumUserRole.CUSTOMER.getStatus()))
		{
			String customerRefId=commonUtil.getCustomerUserRefIdI(httpReq);
			
		}
		else if(role.equals(EnumUserRole.DRIVER.getStatus()))
		{
			commonUtil.getDrivrUserRefIdI(httpReq);
		}
	}
}
