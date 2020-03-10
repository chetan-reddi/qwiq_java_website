package com.transport.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.transport.bean.AddDriver;
import com.transport.bean.AssignDriver;
import com.transport.bean.CancelReq;
import com.transport.bean.Charges;
import com.transport.bean.ConfirmOrder;
import com.transport.bean.DistanceReq;
import com.transport.bean.DriverStatus;
import com.transport.bean.EditBid;
import com.transport.bean.KYCDetails;
import com.transport.bean.OrderDetails;
import com.transport.bean.RegisterVehicle;
import com.transport.bean.TransitRequest;
import com.transport.bean.VehicleStatus;
import com.transport.constant.EnumBidStatus;
import com.transport.constant.EnumDriverOperationStatus;
import com.transport.constant.EnumDriverStatus;
import com.transport.constant.EnumOrderStatus;
import com.transport.constant.EnumPaymentStatus;
import com.transport.constant.EnumQuoteStatus;
import com.transport.constant.EnumVehicleDocStatus;
import com.transport.constant.EnumVehicleOperationStatus;
import com.transport.constant.EnumVehicleStatus;
import com.transport.constant.EnumVehicleType;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.constant.TransportSuccessMessages;
import com.transport.constant.TransprotSuccessCodes;
import com.transport.email.service.EmailService;
import com.transport.exception.BidAlreadyPlaced;
import com.transport.exception.BidCancellFailed;
import com.transport.exception.BidCancellationStatus;
import com.transport.exception.BidException;
import com.transport.exception.BidIdNotFound;
import com.transport.exception.BidUpdationFailed;
import com.transport.exception.FaileToAssignDriver;
import com.transport.exception.FailedToChangeDriverOperationStatus;
import com.transport.exception.FailedToChangeVehicleStatus;
import com.transport.exception.FailedToRegDriver;
import com.transport.exception.FailedToSaveWalletDetails;
import com.transport.exception.FailedToUpdateDetails;
import com.transport.exception.InvalidDriverId;
import com.transport.exception.InvalidDriverOperationStatus;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidOTP;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidOwner;
import com.transport.exception.InvalidQuoteId;
import com.transport.exception.InvalidVehicleId;
import com.transport.exception.InvalidVehicleOperationStatus;
import com.transport.exception.SendOTPFailed;
import com.transport.exception.TransportException;
import com.transport.exception.VehicleRegFailed;
import com.transport.exception.VehicleUploadFailed;
import com.transport.model.Bid;
import com.transport.model.PartnerWallet;
import com.transport.model.ProfileDetails;
import com.transport.model.Quote;
import com.transport.model.Settings;
import com.transport.model.TransitOTP;
import com.transport.model.VanDetails;
import com.transport.model.VehicleRegistration;
import com.transport.repository.AddDriverRepo;
import com.transport.repository.BidRepo;
import com.transport.repository.KYCRepo;
import com.transport.repository.OrderDetailsRepo;
import com.transport.repository.PartnerWalletRepo;
import com.transport.repository.ProfileRepo;
import com.transport.repository.QuoteRepo;
import com.transport.repository.RegisterVehicleRepo;
import com.transport.repository.SettingsRepo;
import com.transport.repository.TransitOtpRepo;
import com.transport.repository.VanDetailsRepo;
import com.transport.response.ActiveBidsResponse;
import com.transport.response.CofirmOrdersResp;
import com.transport.response.DistanceResponse;
import com.transport.response.DriverDetails;
import com.transport.response.MyBid;
import com.transport.response.OrderDetailsResp;
import com.transport.response.Orders;
import com.transport.response.PartnerCharges;
import com.transport.response.PartnerVehResp;
import com.transport.response.PartnerVehicle;
import com.transport.response.QuoteDetails;
import com.transport.response.QuoteResponse;
import com.transport.response.TransportResponse;
import com.transport.response.TransportResult;
import com.transport.response.VehicleDetails;
import com.transport.response.VehicleRegResp;
import com.transport.response.VehiclesListResponse;
import com.transport.response.WalletResp;
import com.transport.service.CustomerService;
import com.transport.service.DriverService;
import com.transport.util.CommonUtil;
import com.transport.util.UUIDUtil;
@Service
public class DriverServiceImpl implements DriverService{
	public static final Logger LOG = LoggerFactory.getLogger(DriverServiceImpl.class);
	@Autowired
	CommonUtil commonUtil;
	@Value("${google.distance.matrix.URL}")
	private String distacneURl;
	@Value("${google.distance.matrix.API.key}")
	private String distanceAPIKey;
	@Autowired
	RegisterVehicleRepo vehicleRepo;
	@Autowired
	SettingsRepo settingsRepo;
	@Autowired
	PartnerWalletRepo walletRepo;
	@Autowired
	TransitOtpRepo transitRepo;
	@Autowired
	EmailService emailService;
	@Autowired
	VanDetailsRepo vanDetailsRepo;
	@Autowired
	ProfileRepo profileRepo;
	@Autowired
	QuoteRepo quoteRepo;
	@Autowired
	AddDriverRepo addDriverRepo;
	@Autowired
	BidRepo bidRepo;
	@Autowired
	CustomerService customerService;
	@Autowired
	KYCRepo kycRepo;
	@Autowired
	OrderDetailsRepo orderRepo;
	@Autowired
	TransitOtpRepo transitOtpRepo;
	@Override
	public VehicleRegResp addVehicle(RegisterVehicle addVehicle, HttpServletRequest httpReq) throws TransportException, VehicleRegFailed, VehicleUploadFailed {
		TransportResult result = new TransportResult();
		VehicleRegResp response=new VehicleRegResp();
		LOG.debug("vehicle id"+addVehicle.getVehicleId());
		if(addVehicle.getVehicleId()==null)
		{
			String vehicleId=generateVehicleId();
			addVehicle.setVehicleId(vehicleId);
			saveVehicleDetails(addVehicle,commonUtil.getDrivrUserRefIdI(httpReq));
			result.setCode(TransprotSuccessCodes.VEHICLE_REG_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.VEHICLE_REG_SUCCESSFULLY);
			response.setResult(result);
			response.setVehicleId(vehicleId);
		}
		else
		{
			saveVehicleDetails(addVehicle,commonUtil.getDrivrUserRefIdI(httpReq));
			result.setCode(TransprotSuccessCodes.VEHICLE_UPLOAD_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.VEHICLE_UPLOAD_SUCCESSFULLY);
			response.setResult(result);
			response.setVehicleId(addVehicle.getVehicleId());
		}
		
		
		
		return response;
	}
//	private void saveDocs(RegisterVehicle addVehicle,HttpServletRequest httpReq) {
//			KYCDetails kycDetails=new KYCDetails();
//			kycDetails.setGoodsTransitCover(addVehicle.getGoodsTransitCover());
//			kycDetails.setLiabilityInsurance(addVehicle.getLiabilityInsurance());
//			kycDetails.setMotorInsurance(addVehicle.getMotorInsurance());
//			kycDetails.setVanImage(addVehicle.getVanImage());
//			kycDetails.setVehicleId(addVehicle.getVehicleId());
//			customerService.uploadDocs(kycDetails,httpReq);
//		
//	}
	private void saveVehicleDetails(RegisterVehicle addVehicle, String userId) throws TransportException, VehicleRegFailed, VehicleUploadFailed {
		
		boolean result=checkVehicleIdExists(addVehicle.getVehicleId());
		if(!result)
		{
			VehicleRegistration details=new VehicleRegistration();
			details.setUserId(userId);
			details.setBaseRange(addVehicle.getBaseRange());
			details.setVanSize(addVehicle.getVanSize());
			details.setVehicleRegNo(addVehicle.getVehicleRegNo());
			details.setVehicleId(addVehicle.getVehicleId());
			details.setLatittude(addVehicle.getLatittude());
			details.setLongitude(addVehicle.getLongitude());
			details.setStatus(EnumVehicleStatus.NOT_APPROVED.getStatusId());
			details.setBaseArea(addVehicle.getBaseArea());
			details.setVehicleStatus(EnumVehicleOperationStatus.ACTIVE.getStatusId());
			details.setDriverChargesPerHour(addVehicle.getDriverChargePerHour());
			details.setChargePerMile(addVehicle.getChargePerMile());
			details.setNationalInsuranceNumber(addVehicle.getNationalInsuranceNumber());

			try
			{
				vehicleRepo.save(details);
				
			}
			catch(Exception ex)
			{
				throw new VehicleRegFailed(TransportErrorMessages.FAILED_TO_SAVE_VEHICLE_DETAILS);
			}
		}
		
		else
		{
			VehicleRegistration vehicleDetails=vehicleRepo.findByVehicleId(addVehicle.getVehicleId());
			vehicleDetails.setBaseRange(addVehicle.getBaseRange());
			vehicleDetails.setVanSize(addVehicle.getVanSize());
			vehicleDetails.setVehicleRegNo(addVehicle.getVehicleRegNo());
			vehicleDetails.setLatittude(addVehicle.getLatittude());
			vehicleDetails.setLongitude(addVehicle.getLongitude());
			vehicleDetails.setStatus(EnumVehicleStatus.NOT_APPROVED.getStatusId());
			vehicleDetails.setBaseArea(addVehicle.getBaseArea());
			vehicleDetails.setDriverChargesPerHour(addVehicle.getDriverChargePerHour());
			vehicleDetails.setChargePerMile(addVehicle.getChargePerMile());
			vehicleDetails.setNationalInsuranceNumber(addVehicle.getNationalInsuranceNumber());
			try
			{
				vehicleRepo.save(vehicleDetails);
			}
			catch(Exception ex)
			{
				LOG.debug("Error occured : "+ex.getMessage());
				throw new VehicleUploadFailed(TransportErrorMessages.FAILED_TO_UPLOAD_VEHICLE_DETAILS);
			}
			
		}
		
	}
	private boolean checkVehicleIdExists(String vehicleId) {
		return vehicleRepo.checkVehicleIdExists(vehicleId);
		
	}
	private String generateVehicleId() {
		UUID uuid = UUID.randomUUID();
       String vehicleId="VH"+uuid;
       return vehicleId;
	}
	private String generateDriverId() {
		UUID uuid = UUID.randomUUID();
       String driverId="DR"+uuid;
       return driverId;
	}
	@Override
	public VehiclesListResponse getVehiclesList(HttpServletRequest httpReq) {
		VehiclesListResponse response=new VehiclesListResponse();
		List<VehicleDetails> vehiclesDetailsList=new ArrayList<VehicleDetails>();
		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
		Iterable<VehicleRegistration> vehicleList=vehicleRepo.findByUserId(userRefId);
		for(VehicleRegistration vehicleReg:vehicleList)
		{
			VehicleDetails vehicleDetails=new VehicleDetails();
			vehicleDetails.setBaseRange(vehicleReg.getBaseRange());
			vehicleDetails.setBaseArea(vehicleReg.getBaseArea());
			vehicleDetails.setGoodsTransitCover(vehicleReg.getGoodsTransitCover());
			vehicleDetails.setLiabilityInsurance(vehicleReg.getLiabilityInsurance());
			vehicleDetails.setMotorInsurance(vehicleReg.getMotorInsurance());
			vehicleDetails.setNationalInsuranceNumber(vehicleReg.getNationalInsuranceNumber());
			vehicleDetails.setStatus(EnumVehicleDocStatus.getStatus(vehicleReg.getStatus()));
			vehicleDetails.setVanImage(vehicleReg.getVanImage());
			vehicleDetails.setVehicleId(vehicleReg.getVehicleId());
			vehicleDetails.setVehicleRegNo(vehicleReg.getVehicleRegNo());
			vehicleDetails.setVehicleType(EnumVehicleType.getStatus(vehicleReg.getVanSize()));
			vehicleDetails.setVehicleStatus(EnumVehicleStatus.getStatus(vehicleReg.getStatus()));
			vehicleDetails.setVehicleOperationStatus(EnumVehicleOperationStatus.getStatus(vehicleReg.getVehicleStatus()));
			vehicleDetails.setDriverChargesPerHour(vehicleReg.getDriverChargesPerHour());
			vehicleDetails.setChargePerMile(vehicleReg.getChargePerMile());
			vehicleDetails.setPath(vehicleReg.getPath());
			String driverId=vehicleReg.getDriverId();
			if(driverId!=null)
			{
				AddDriver driverDetails=addDriverRepo.findByDriverId(driverId);
				vehicleDetails.setDriverNumber(driverDetails.getPhoneNumber());
				vehicleDetails.setDriverName(driverDetails.getFirstName());
			}
			
			vehiclesDetailsList.add(vehicleDetails);
		}
		response.setResult(vehiclesDetailsList);
		return response;
	}
	private KYCDetails getDocsByVehicleId(String vehicleId) {
		
		return kycRepo.findByVehicleId(vehicleId);
	}
	@Override
	public OrderDetailsResp getAllDriverOrders(HttpServletRequest httpReq) {
		OrderDetailsResp resp=new OrderDetailsResp();
//		String userId=commonUtil.getDrivrUserRefIdI(httpReq);
//		List<Orders> list=getVehicles(userId);
//		resp.setResult(list);
		return resp;
	}
//	private List<Orders> getVehicles(String userId) {
//		List<Orders> orderResponse=new ArrayList<Orders>();
//		List<VehicleRegistration> vehicleList=vehicleRepo.findByUserId(userId);
//		if(!(vehicleList.isEmpty()))
//		{
//			for(VehicleRegistration vehicle:vehicleList)
//			{
//				if(vehicle.getStatus()==EnumVehicleStatus.APPROVED.getStatusId())
//				{
//					
//					List<OrderDetails> orderList=checkAvailableJobs(vehicle.getVanSize());
//					for(OrderDetails orderDetails:orderList)
//					{
//						Orders order=new Orders();
//						order.setCollectionCity(orderDetails.getCollectionCity());
//						order.setCollectionPostCode(orderDetails.getCollectionPostCode());
//						order.setCollectionStreetAddres(orderDetails.getCollectionStreetAddres());
//						order.setDeliveryAddress(orderDetails.getDeliveryAddress());
//						order.setDeliveryCity(orderDetails.getDeliveryCity());
//						order.setDeliveryPostCode(orderDetails.getDeliveryPostCode());
//						order.setDeliveryStreetAddres(orderDetails.getDeliveryStreetAddres());
//						order.setDistance(orderDetails.getDistance());
//						order.setEstimateValue(orderDetails.getEstimateValue());
//						order.setFormAddress(orderDetails.getFormAddress());
//						order.setHelpersCount(orderDetails.getHelpersCount());
//						order.setHoursNeeded(orderDetails.getHoursNeeded());
//						order.setName(orderDetails.getName());
//						order.setPhoneNumber(orderDetails.getPhoneNumber());
//						order.setPickUpDate(orderDetails.getPickUpDate());
//						order.setPickUpTime(orderDetails.getPickUpDate());
//						order.setOrderId(orderDetails.getOrderId());
//						String vehicleTYpe=EnumVehicleType.getStatus(orderDetails.getVanSize());
//						order.setVanSize(vehicleTYpe);
//						order.setOrderStatus(EnumOrderStatus.getStatus(orderDetails.getStatus()));
//						orderResponse.add(order);
//					}
//				}
//			}
//		}
//		
//		return orderResponse;
//	}
//	private List<OrderDetails> checkAvailableJobs(int vanSize) {
//		return orderRepo.findByVanSizeAndStatus(vanSize,EnumOrderStatus.ACTIVE.getStatusId());
//	
//	}
	@Override
	public TransportResponse placeBid(Bid bidReq, HttpServletRequest httpReq) throws BidException, BidAlreadyPlaced, InvalidQuoteId {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		checkQuoteId(bidReq.getQuoteId());
		checkBidPlaced(bidReq.getQuoteId(),commonUtil.getDrivrUserRefIdI(httpReq));
		String driverId=commonUtil.getDrivrUserRefIdI(httpReq);
		bidReq.setBidId(UUIDUtil.randomTenDigitsUUID());
		bidReq.setDriverUserId(driverId);
		bidReq.setStatus(EnumBidStatus.ACTIVE.getStatusId());
//		bidReq.setDriverCharges(getDriverCharges(bidReq.getQuoteId(),bidReq.getVehicleId()));
//		bidReq.setMileageCharges(getMileCharges(bidReq.getQuoteId(),bidReq.getVehicleId()));
		try
		{
			bidRepo.save(bidReq);
			result.setCode(TransprotSuccessCodes.BID_SAVED_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.BID_SAVED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			LOG.debug(ex.getMessage());
			throw new BidException(TransportErrorMessages.FAILED_TO_SAVE_BID);
		}
		return response;
	}
	private void checkQuoteId(String quoteId) throws InvalidQuoteId {
		
		boolean result=quoteRepo.checkQuoteIdExists(quoteId);
		if(!result)
		{
			throw new InvalidQuoteId(TransportErrorMessages.INVALID_QUOTE_ID);
		}
	}
	private double getMileCharges(String quoteId, String vehicleId) {
		Quote quoteDetails=quoteRepo.findByQuoteId(quoteId);
		double distance=quoteDetails.getDistance();
		VehicleRegistration vehicleDetails=vehicleRepo.findByVehicleId(vehicleId);
		double milePerHour=vehicleDetails.getChargePerMile();
		double mileCharges=distance*milePerHour;
		return mileCharges;
	}
	private double getDriverCharges(String quoteId, String vehicleId) {
		Quote quoteDetails=quoteRepo.findByQuoteId(quoteId);
		double hoursNeeded=quoteDetails.getHoursNeeded();
		VehicleRegistration vehicleDetails=vehicleRepo.findByVehicleId(vehicleId);
		double hoursCharge=vehicleDetails.getDriverChargesPerHour();
		double driverCharge=hoursNeeded*hoursCharge;
		return driverCharge;
	}
	private void checkBidPlaced(String quoteId, String driverUserId) throws BidAlreadyPlaced {
		boolean result=bidRepo.findByQuoteIdAndDriverUserId(quoteId,driverUserId);
		if(result)
		{
			throw new BidAlreadyPlaced(TransportErrorMessages.BID_ALREADY_PLACED);
		}
		
	}
	private void checkorderDetailsId(String orderDetailsId) throws  InvalidOrderId {
		boolean result=orderRepo.checkOrderIdExists(orderDetailsId);
		if(!result)
		{
			throw new InvalidOrderId(TransportErrorMessages.INVALID_ORDER_ID);
		}
	}
	@Override
	public ActiveBidsResponse getMyBids(HttpServletRequest httpReq) {
	
		ActiveBidsResponse response=new ActiveBidsResponse();
		List<MyBid> mybidList=new ArrayList<MyBid>();
		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
		List<Bid> bidDetails=bidRepo.findByDriverUserIdAndStatus(userRefId,EnumBidStatus.ACTIVE.getStatusId());
		for(Bid bid:bidDetails)
		{
			MyBid myBid=new MyBid();
			myBid.setAdditionalCharges(bid.getAdditionalCharges());
			myBid.setAdditionalChargesDesc(bid.getAdditionalChargesDesc());
			myBid.setAdditionalStopCharges(bid.getAdditionalStopCharges());
			myBid.setAdditonalTimeChargesPerHour(bid.getAdditonalTimeChargesPerHour());
			myBid.setBidAmount(bid.getBidAmount());
			myBid.setBookingFee(bid.getBookingFee());
			myBid.setComment(bid.getComment());
			myBid.setDriverCharges(bid.getDriverCharges());
			myBid.setMileageCharges(bid.getMileageCharges());
			myBid.setOutOfHourCharges(bid.getOutOfHourCharges());
			myBid.setPromotionalDiscount(bid.getPromotionalDiscount());
			myBid.setTotalCost(bid.getTotalCost());
			myBid.setVehicleId(bid.getVehicleId());
			myBid.setStatus(EnumBidStatus.getStatus(bid.getStatus()));
			myBid.setAdditionalCharges(bid.getAdditionalCharges());
			myBid.setBidId(bid.getBidId());
			myBid.setDriverUserId(bid.getDriverUserId());
			myBid.setCreatedDate(bid.getCreated_date());
			myBid.setQuoteId(bid.getQuoteId());
			mybidList.add(myBid);
		}
		response.setResult(mybidList);
		return response;
	}
	@Override
	public CofirmOrdersResp getConfirmOrders(HttpServletRequest httpReq) {
		CofirmOrdersResp response=new CofirmOrdersResp();
//		List<ConfirmedOrder> ordersList=new ArrayList<ConfirmedOrder>();
//		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
//		List<OrderDetails> orderDetailsList=orderRepo.findByDriverIdAndStatus(userRefId,EnumOrderStatus.ACCEPT.getStatusId());
//		for(OrderDetails orderDetails:orderDetailsList)
//		{
//			ConfirmedOrder confirmedOrders=new ConfirmedOrder();
//			confirmedOrders.setFromAddress(orderDetails.getFormAddress());
//			confirmedOrders.setToAddres(orderDetails.getDeliveryAddress());
//			confirmedOrders.setOrderId(orderDetails.getOrderId());
//			//Bid bidDetails=bidRepo.findByOrderIdAndDriverUserIdAndStatus(orderDetails.getOrderId(), userRefId,EnumBidStatus.ACCEPT.getStatusId());
//			//confirmedOrders.setBidAmount(bidDetails.getBidAmount());
//			confirmedOrders.setMoveDate(orderDetails.getPickUpDate());
//			confirmedOrders.setPickUpTime(orderDetails.getPickUpTime());
//			confirmedOrders.setPhoneNumber(orderDetails.getPhoneNumber());
//			confirmedOrders.setStatus(EnumOrderStatus.getStatus(orderDetails.getStatus()));
//			ordersList.add(confirmedOrders);
//		}
//		response.setResult(ordersList);
		return response;
	}
	@Override
	public DistanceResponse getDistance(DistanceReq distanceReq) throws IOException, InvalidLocation {
		DistanceResponse response=new DistanceResponse();
		RestTemplate restTemplate = new RestTemplate();
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
		 String url_request=distacneURl+distanceReq.getPickUplatitude()+","+distanceReq.getPickUplongitude()+"&destinations="+distanceReq.getDeliveryLatitude()+","+distanceReq.getDeliveryLongitude()+"&key="+distanceAPIKey;
		 LOG.debug("url requ"+url_request);
		 try
		 {
			 String result=restTemplate.exchange(url_request, HttpMethod.GET, entity, String.class).getBody(); 
		      LOG.debug("Result "+ result);
		      JSONObject jsonResponse = new JSONObject(result);
		      JSONArray jsonArray=(JSONArray) jsonResponse.get("rows");
		      JSONObject jsoObj=(JSONObject) jsonArray.get(0);
		      JSONArray elements=(JSONArray) jsoObj.get("elements");
		      
		      JSONObject jsonObj=(JSONObject) elements.get(0);
		      String status=jsonObj.getString("status");
		      if(status.equals("OK"))
		      {
		    	  JSONObject durationObj=(JSONObject) jsonObj.get("duration");
		    	  int timeInSeconds=durationObj.getInt("value");
			      JSONObject distacneObj=(JSONObject) jsonObj.get("distance");
			      String time=durationObj.getString("text");
			      double distance=distacneObj.getDouble("value");
			      double distanceInmiles=convertMetersToMiles(distance);
			      response.setTimeInseconds(timeInSeconds);
			      response.setTime(time);
			      response.setDistance(distanceInmiles);
		      }
		      else
		      {
		    	  throw new InvalidLocation(TransportErrorMessages.INVALID_LOCATION);
		      }
		      
		 }
		 catch(InvalidLocation ex)
	 		{
			 throw new InvalidLocation(TransportErrorMessages.INVALID_LOCATION);
	 		}
		 catch(Exception ex) {
			 ex.printStackTrace();
	 			LOG.debug(ex.getMessage());
		 }
		 return response;

	}
	private double convertMetersToMiles(double distance) {
		return distance*0.000621371;
		
	}
	@Override
	public TransportResponse addDriver(AddDriver addDriver, HttpServletRequest httpReq) throws FailedToRegDriver {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		try
		{
			String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
			addDriver.setOwnerId(userRefId);
			String driverId=generateDriverId();
			addDriver.setDriverId(driverId);
			addDriver.setStatus(EnumDriverStatus.NOT_APPROVED.getStatusId());
			addDriver.setOperationStatus(EnumDriverOperationStatus.ACTIVE.getStatusId());
			addDriverRepo.save(addDriver);
			result.setCode(TransportErrorCodes.DERIVER_REGISTERED_SUCCESSFULLY);
			result.setMessage(TransportErrorMessages.DERIVER_REGISTERED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			LOG.debug(ex.getMessage());
			throw new FailedToRegDriver(TransportErrorMessages.FAILED_TO_REGISTER_DRIVER);
		}
		return response;
	}
	@Override
	public List<DriverDetails> getAllDrivers(HttpServletRequest httpReq) {
		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
		List<DriverDetails> driverList=new ArrayList<DriverDetails>();
		List<AddDriver> deatils=addDriverRepo.findByOwnerId(userRefId);
		for(AddDriver driverDetails:deatils)
		{
			DriverDetails driverDetailsResp=new DriverDetails();
			driverDetailsResp.setDriverId(driverDetails.getDriverId());
			driverDetailsResp.setDrivingLicenseNumber(driverDetails.getDrivingLicenseNumber());
			driverDetailsResp.setDrivingLicenseImage(driverDetails.getDrivingLicenseImage());
			driverDetailsResp.setEmail(driverDetails.getEmail());
			driverDetailsResp.setFirstName(driverDetails.getFirstName());
			driverDetailsResp.setSurName(driverDetails.getSurName());
			driverDetailsResp.setPhoneNumber(driverDetails.getPhoneNumber());
			driverDetailsResp.setPostCode(driverDetails.getPostCode());
			driverDetailsResp.setRegion(driverDetails.getRegion());
			driverDetailsResp.setCity(driverDetails.getCity());
			driverDetailsResp.setStreetAddress(driverDetails.getStreetAddress());
			driverDetailsResp.setStreetAddress2(driverDetails.getStreetAddress2());
			driverDetailsResp.setStatus(EnumDriverStatus.getStatus(driverDetails.getStatus()));
			driverDetailsResp.setDriverOperationStatus(EnumDriverOperationStatus.getStatus(driverDetails.getOperationStatus()));
			driverList.add(driverDetailsResp);
		}
		return driverList;
	}
	@Override
	public TransportResponse assignDriverToVehicle(AssignDriver assignReq, HttpServletRequest httpReq) throws InvalidDriverId, InvalidVehicleId, FaileToAssignDriver {
		TransportResponse response=new TransportResponse();
		checkDriverIdExists(assignReq);
		chekcVehicleIdExists(assignReq);
		VehicleRegistration vehicleDetails=vehicleRepo.findByVehicleId(assignReq.getVehicleId());
		vehicleDetails.setDriverId(assignReq.getDriverId());
		try
		{
			vehicleRepo.save(vehicleDetails);
			TransportResult result = new TransportResult();
			
			result.setCode(TransportErrorCodes.ASSIGN_DRIVER_SUCCESSFULLY);
			result.setMessage(TransportErrorMessages.ASSIGN_DRIVER_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new FaileToAssignDriver(TransportErrorMessages.FAILED_TO_ASSIGN_DRIVER);
		}
		return response;
	}
	private void chekcVehicleIdExists(AssignDriver assignReq) throws InvalidVehicleId {
		boolean result=vehicleRepo.checkVehicleIdExists(assignReq.getVehicleId());
		if(!result)
		{
			throw new InvalidVehicleId(TransportErrorMessages.INVALID_DRIVER_DID);
		}
		
	}
	private void checkDriverIdExists(AssignDriver assignReq) throws InvalidDriverId {
		boolean result=addDriverRepo.checkDrvierIdExists(assignReq.getDriverId());
		if(!result)
		{
			throw new InvalidDriverId(TransportErrorMessages.INVALID_DRIVER_DID);
		}
	}
	@Override
	public TransportResponse changeVehilceStatus(VehicleStatus status, HttpServletRequest httpReq) throws InvalidVehicleId, InvalidOwner, FailedToChangeVehicleStatus, InvalidVehicleOperationStatus {
		String ownerId=commonUtil.getDrivrUserRefIdI(httpReq);
		TransportResponse response=new TransportResponse();
		boolean result=checkVehicleIdExists(status.getVehilceId());
		if(result)
		{
			checkValidOwner(status.getVehilceId(),ownerId);
			VehicleRegistration vehicleDetail=vehicleRepo.findByVehicleId(status.getVehilceId());
			vehicleDetail.setVehicleStatus(status.getStatus());
			try
			{
				if(status.getStatus()==1)
				{
					vehicleRepo.save(vehicleDetail);
					TransportResult transportResult = new TransportResult();
					
					transportResult.setCode(TransportErrorCodes.VEHICLE_ACTIVATED_SUCCESSFULLY);
					transportResult.setMessage(TransportErrorMessages.VEHICLE_ACTIVATED_SUCCESSFULLY);
					response.setResult(transportResult);
				}
				else if(status.getStatus()==2)
				{
					vehicleRepo.save(vehicleDetail);
					TransportResult transportResult = new TransportResult();
					
					transportResult.setCode(TransportErrorCodes.VEHICLE_INACTIVE_SUCCESSFULLY);
					transportResult.setMessage(TransportErrorMessages.VEHICLE_INACTIVE_SUCCESSFULLY);
					response.setResult(transportResult);
				}
				else
				{
					throw new InvalidVehicleOperationStatus(TransportErrorMessages.INVALID_VEHICLE_OPERATON_STATUS);
				}
				
			}
			catch(InvalidVehicleOperationStatus ex)
			{
				throw new InvalidVehicleOperationStatus(TransportErrorMessages.INVALID_VEHICLE_OPERATON_STATUS);
			}
			catch(Exception ex)
			{
				throw new FailedToChangeVehicleStatus(TransportErrorMessages.FAILED_TO_CHANGE_VEHICLE_STATUS);
			}
			
		}
		else
		{
			throw new InvalidVehicleId(TransportErrorMessages.INVALID_VEHICLE_ID);
		}
		return response;
		
	}
	private void checkValidOwner(String vehilceId, String ownerId) throws InvalidOwner {
		boolean result=vehicleRepo.checkValidOwner(vehilceId,ownerId);
		if(!result)
		{
			throw new InvalidOwner(TransportErrorMessages.INVALID_OWNER);
		}
	}
	@Override
	public TransportResponse changeDriverStatus(DriverStatus status, HttpServletRequest httpReq) throws InvalidDriverId, InvalidOwner, FailedToChangeDriverOperationStatus, InvalidDriverOperationStatus {
		String ownerId=commonUtil.getDrivrUserRefIdI(httpReq);
		TransportResponse response=new TransportResponse();
		boolean result=checkDriverIdExist(status.getDriverId());
		if(result)
		{
			checkValidOwnerId(status.getDriverId(),ownerId);
			AddDriver driverDetails=addDriverRepo.findByDriverId(status.getDriverId());
			driverDetails.setOperationStatus(status.getStatus());
			try
			{
				if(status.getStatus()==1)
				{
					addDriverRepo.save(driverDetails);
					TransportResult transportResult = new TransportResult();
					transportResult.setCode(TransportErrorCodes.DRIVER_ACTIVATED_SUCCESSFULLY);
					transportResult.setMessage(TransportErrorMessages.DRIVER_ACTIVATED_SUCCESSFULLY);
					response.setResult(transportResult);
				}
				else if(status.getStatus()==2)
				{
					addDriverRepo.save(driverDetails);
					TransportResult transportResult = new TransportResult();
					transportResult.setCode(TransportErrorCodes.DRIVER_INACTIVATED_SUCCESSFULLY);
					transportResult.setMessage(TransportErrorMessages.DRIVER_INACTIVATED_SUCCESSFULLY);
					response.setResult(transportResult);
				}
				else
				{
					throw new InvalidDriverOperationStatus(TransportErrorMessages.INVALID_DRIVER_OPERATON_STATUS);
				}
				
			}
			catch(InvalidDriverOperationStatus ex)
			{
				throw new InvalidDriverOperationStatus(TransportErrorMessages.INVALID_DRIVER_OPERATON_STATUS);
			}
			catch(Exception ex)
			{
				throw new FailedToChangeDriverOperationStatus(TransportErrorMessages.FAILED_TO_CHANGE_DRIVER_OPERATON_STATUS);
			}
			
		}
		else
		{
			throw new InvalidDriverId(TransportErrorMessages.INVALID_VEHICLE_ID);
		}
		return response;
	}
	private void checkValidOwnerId(String driverId, String ownerId) throws InvalidOwner {
		boolean result=addDriverRepo.checkValidOwnerId(driverId,ownerId);
		if(!result)
		{
			throw new InvalidOwner(TransportErrorMessages.INVALID_OWNER);
		}
		
	}
	private boolean checkDriverIdExist(String driverId) throws InvalidDriverId {
		return addDriverRepo.checkDrvierIdExists(driverId);
	}
	@Override
	public TransportResponse editBid(EditBid bidReq, HttpServletRequest httpReq) throws BidUpdationFailed, BidIdNotFound {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkBidIdExists(bidReq.getBidId());
		String ownerId=commonUtil.getDrivrUserRefIdI(httpReq);
		Bid bidDetails=bidRepo.findByBidId(bidReq.getBidId());
		bidDetails.setAdditionalCharges(bidReq.getAdditionalCharges());
		bidDetails.setAdditionalChargesDesc(bidReq.getAdditionalChargesDesc());
		bidDetails.setAdditionalStopCharges(bidReq.getAdditionalStopCharges());
		bidDetails.setAdditonalTimeChargesPerHour(bidReq.getAdditonalTimeChargesPerHour());
		bidDetails.setBidAmount(bidReq.getBidAmount());
		bidDetails.setBookingFee(bidReq.getBookingFee());
		bidDetails.setComment(bidReq.getComment());
		bidDetails.setDriverCharges(bidReq.getDriverCharges());
		bidDetails.setMileageCharges(bidReq.getMileageCharges());
		bidDetails.setOutOfHourCharges(bidReq.getOutOfHourCharges());
		bidDetails.setPromotionalDiscount(bidReq.getPromotionalDiscount());
		bidDetails.setTotalCost(bidReq.getTotalCost());
		bidDetails.setVehicleId(bidReq.getVehicleId());
		bidDetails.setStatus(EnumBidStatus.ACTIVE.getStatusId());
		try
		{
			bidRepo.save(bidDetails);
			result.setCode(TransportErrorCodes.SUCCESSFULLY_BID_UPDATED);
			result.setMessage(TransportErrorMessages.SUCCESSFULLY_BID_UPDATED);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new BidUpdationFailed(TransportErrorMessages.BID_UPDATION_FAILED);
		}
		return response;
	}
	private void checkBidIdExists(String bidId) throws BidIdNotFound {
		boolean result=bidRepo.checkBidIdExists(bidId);
		if(!result)
		{
			throw new BidIdNotFound(TransportErrorMessages.BID_ID_NOT_FOUND);
		}
	}
	@Override
	public TransportResponse cancelBid(CancelReq bidReq, HttpServletRequest httpReq) throws BidIdNotFound, BidCancellFailed, BidCancellationStatus {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkBidIdExists(bidReq.getBidId());
		Bid bidDetails=bidRepo.findByBidId(bidReq.getBidId());
		if(bidReq.getStatus()==EnumBidStatus.CANCELL.getStatusId())
		{
			bidDetails.setStatus(EnumBidStatus.CANCELL.getStatusId());
			try
			{
				bidRepo.save(bidDetails);
				result.setCode(TransportErrorCodes.BID_CANCELLED_SUCCESSFULLY);
				result.setMessage(TransportErrorMessages.BID_CANCELLED_SUCCESSFULLY);
				response.setResult(result);
			}
			catch(Exception ex)
			{
				throw new BidCancellFailed(TransportErrorMessages.FAILED_TO_CANCELL_BID);
			}
		} 
		else
		{
			throw new BidCancellationStatus(TransportErrorMessages.INVALID_CANCELLATION_STATUS);
		}
		return response;
	}
	@Override
	public TransportResponse edit(RegisterVehicle addVehicle, HttpServletRequest httpReq) throws TransportException, VehicleRegFailed, VehicleUploadFailed {
		
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();String userId=commonUtil.getDrivrUserRefIdI(httpReq);
		saveVehicleDetails(addVehicle,userId);
		result.setCode(TransprotSuccessCodes.VEHICLE_UPLOAD_SUCCESSFULLY);
		result.setMessage(TransportSuccessMessages.VEHICLE_UPLOAD_SUCCESSFULLY);
		response.setResult(result);
		return response;
	}
	@Override
	public TransportResponse editDriver(AddDriver addVehicle, HttpServletRequest httpReq) throws FailedToRegDriver {
		TransportResponse response=new TransportResponse();
		AddDriver driverDetails=addDriverRepo.findByDriverId(addVehicle.getDriverId());
		driverDetails.setCity(addVehicle.getCity());
		driverDetails.setCountry(addVehicle.getCountry());
		driverDetails.setDrivingLicenseImage(addVehicle.getDrivingLicenseImage());
		driverDetails.setDrivingLicenseNumber(addVehicle.getDrivingLicenseNumber());
		driverDetails.setEmail(addVehicle.getEmail());
		driverDetails.setFirstName(addVehicle.getFirstName());
		driverDetails.setPhoneNumber(addVehicle.getPhoneNumber());
		driverDetails.setPostCode(addVehicle.getPostCode());
		driverDetails.setRegion(addVehicle.getRegion());
		driverDetails.setStreetAddress(addVehicle.getStreetAddress());
		driverDetails.setStreetAddress2(addVehicle.getStreetAddress2());
		driverDetails.setSurName(addVehicle.getSurName());
		try
		{
			addDriverRepo.save(driverDetails);
			TransportResult result = new TransportResult();
			result.setCode(TransportErrorCodes.DRIVER_DETAILS_UPDATED_SUCCESSFULLY);
			result.setMessage(TransportErrorMessages.DRIVER_DETAILS_UPDATED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			LOG.debug(ex.getMessage());
			throw new FailedToRegDriver(TransportErrorMessages.FAILED_TO_UPLOAD_DRIVER);
		}
		return response;
		
	}
	@Override
	public QuoteResponse getQuotes(HttpServletRequest httpReq) {
		QuoteResponse response=new QuoteResponse();
		List<QuoteDetails>  quoteList=new ArrayList<QuoteDetails>();
		Iterable<Quote> quoteDetails=quoteRepo.getStatusByStatusAndPickUpDate(EnumQuoteStatus.PLACED.getStatusId());
		for(Quote quote:quoteDetails)
		{
			if(!checkBidExists(quote.getQuoteId(),httpReq))
			{
				QuoteDetails details=new QuoteDetails();
				details.setBookingFee(quote.getBookingFee());
				details.setCollectionCity(quote.getCollectionCity());
				details.setCollectionPostCode(quote.getCollectionPostCode());
				details.setCollectionStreetAddres(quote.getCollectionStreetAddres());
				details.setDeliveryAddress(quote.getDeliveryAddress());
				details.setDeliveryCity(quote.getDeliveryCity());
				details.setDeliveryPostCode(quote.getDeliveryPostCode());
				details.setDeliveryStreetAddres(quote.getDeliveryStreetAddres());
				details.setDiscountAmount(quote.getDiscountAmount());
				details.setDistance(quote.getDistance());
				details.setName(quote.getName());
				details.setEstimateValue(quote.getPrice());
				details.setFormAddress(quote.getFormAddress());
				details.setHelpersCount(quote.getHelpersCount());
				details.setHoursNeeded(quote.getHoursNeeded());
				details.setPhoneNumber(quote.getPhoneNumber());
				details.setPickUpDate(quote.getPickUpDate());
				details.setPickUpTime(quote.getPickUpTime());
				details.setPrice(quote.getPrice());
				details.setQuoteId(quote.getQuoteId());
				details.setQuoteStatus(EnumQuoteStatus.getStatus(quote.getStatus()));
				details.setTotalPrice(quote.getTotalPrice());
				details.setVanId(quote.getVanSize());
				details.setVanSize(vanDetailsRepo.findById(quote.getVanSize()).getVanTypeName());
				quoteList.add(details);
			}
			
		}
		response.setResult(quoteList);
		return response;
	}
	private boolean checkBidExists(String quoteId, HttpServletRequest httpReq) {
		String driverUserId=commonUtil.getDrivrUserRefIdI(httpReq);
		boolean result=bidRepo.findByQuoteIdAndDriverUserId(quoteId,driverUserId);
		return result;
	}
	@Override
	public OrderDetailsResp getDriverOrders(HttpServletRequest httpReq) {
		OrderDetailsResp response=new OrderDetailsResp();
		List<Orders> ordersList=new ArrayList<Orders>();
		String partnerId=commonUtil.getDrivrUserRefIdI(httpReq);
		List<OrderDetails> orderList=orderRepo.findByPartnerId(partnerId);
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
	public TransportResponse transitRequest(TransitRequest req, HttpServletRequest httpReq) throws SendOTPFailed {
		
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		OrderDetails orderDetails=orderRepo.findByOrderId(req.getOrderId());
		Bid bidDetails=bidRepo.findByBidId(orderDetails.getBidId());
		sendOTP(bidDetails.getQuoteId(),req.getOrderId());
		result.setCode(TransprotSuccessCodes.REQUEST_SENT_TO_CUSTMOER);
		result.setMessage(TransportSuccessMessages.REQUEST_SENT_TO_CUSTOMER);
		response.setResult(result);
		return response;
	}
	private void sendOTP(String quoteId,String orderId) throws SendOTPFailed {
		
			Quote quoteDetails=quoteRepo.findByQuoteId(quoteId);
			String otp=UUIDUtil.generateOTP();
			try
			{
				emailService.sendTransitOTP(quoteDetails.getEmail(),otp);
				saveOTP(quoteDetails.getEmail(),otp,orderId);
			}
			catch(Exception ex)
			{
				LOG.debug(ex.getMessage());
				throw new SendOTPFailed(TransportErrorMessages.OTP_FAILED);
			}
		}
	private void saveOTP(String email, String otp,String orderId) {
		TransitOTP otpdetails=new TransitOTP();
		boolean result=transitOtpRepo.checkOTPExists(orderId, otp);
		if(result)
		{
			TransitOTP details=transitOtpRepo.findByOrderId(orderId);
			details.setOtp(otp);
			try
			{
				transitOtpRepo.save(details);
			}
			catch(Exception ex)
			{
				
			}
			
		}
		else
		{
			otpdetails.setEmailId(email);
			otpdetails.setOtp(otp);
			otpdetails.setOrderId(orderId);
			try
			{
				transitOtpRepo.save(otpdetails);
			}
			catch(Exception ex)
			{
				
			}
		}
	}
	@Override
	public TransportResponse confirmTransitRequest(TransitRequest req, HttpServletRequest httpReq) throws InvalidOrderId, InvalidOTP {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkorderDetailsId(req.getOrderId());
		boolean res=transitOtpRepo.checkOTPExists(req.getOrderId(),req.getOtp());
		if(res)
		{
			transitOtpRepo.deleteByOrderId(req.getOrderId());
			OrderDetails orderDetails=orderRepo.findByOrderId(req.getOrderId());
			orderDetails.setStatus(EnumOrderStatus.TRANSIT.getStatusId());
			orderRepo.save(orderDetails);
			result.setCode(TransportErrorCodes.OTP_VERIFIED);
			result.setMessage(TransportErrorMessages.OTP_VERIFIED);
			response.setResult(result);
		}
		else
		{
			throw new InvalidOTP(TransportErrorMessages.INVALID_OTP);
		}
		return response;
	}
	@Override
	public PartnerCharges getPartnerCharges(Charges req, HttpServletRequest httpReq) {
		PartnerCharges response=new PartnerCharges();
		Quote quoteDetails=quoteRepo.findByQuoteId(req.getQuoteId());
		response.setDistance(quoteDetails.getDistance());
		int minutesNeeded=quoteDetails.getHoursNeeded();
		int hours=minutesNeeded/60;
		int minutes=minutesNeeded%60;
		response.setVehicelHoursNeeded(hours+"hrs"+minutes+"minutes");
		VehicleRegistration vehicleDetails=vehicleRepo.findByVehicleId(req.getVehicleId());
		response.setDriverChargePerHour(vehicleDetails.getDriverChargesPerHour());
		response.setChargePerMile(vehicleDetails.getChargePerMile());
		ProfileDetails profileDetails=profileRepo.findByUserId(req.getPartnerId());
		response.setAdditionalStopCharges(profileDetails.getAdditionalStopCharge());
		response.setAdditionalTimeCharge(profileDetails.getAdditionalTimeChargePerHour());
		response.setHelperChargePerHour(profileDetails.getHelperChargePerHour());
		response.setOutOfWorkingChargesPerHour(profileDetails.getOutOfWorkingChargePperhour());
		Settings details=settingsRepo.findByType("HelperPrice");
		response.setPlatformBookingFee(details.getBookingFee());
		response.setPlatformDiscount(details.getPromotionalDiscount());
		return response;
	}
	@Override
	public PartnerVehResp getPartnersVehicleList(HttpServletRequest httpReq) {
		PartnerVehResp response=new PartnerVehResp();
		List<PartnerVehicle> vehicleList=new ArrayList<PartnerVehicle>();
		String partnerId=commonUtil.getDrivrUserRefIdI(httpReq);
		List<VehicleRegistration> vehicleDetails=vehicleRepo.findByUserId(partnerId);
		for(VehicleRegistration vehicle:vehicleDetails)
		{
			PartnerVehicle partnerVehicle=new PartnerVehicle();
			partnerVehicle.setVehicleId(vehicle.getVehicleId());
			partnerVehicle.setVanId(vehicle.getVanSize());
			partnerVehicle.setVehicleRegNo(vehicle.getVehicleRegNo());
			VanDetails vanDetails=vanDetailsRepo.findById(vehicle.getVanSize());
			partnerVehicle.setVanType(vanDetails.getVanTypeName());
			partnerVehicle.setStatus(EnumVehicleStatus.getStatus(vehicle.getStatus()));
			partnerVehicle.setOperationalStatus(EnumVehicleOperationStatus.getStatus(vehicle.getVehicleStatus()));
			vehicleList.add(partnerVehicle);
		}
		response.setResult(vehicleList);
		return response;
	}
	@Override
	public TransportResponse confirmOrder(ConfirmOrder confirmOrder) throws FailedToUpdateDetails, InvalidOrderId, InvalidOTP {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		checkorderIdExists(confirmOrder.getOrderId());
		boolean res=transitOtpRepo.checkOTPExists(confirmOrder.getOrderId(),confirmOrder.getOtp());
		if(res)
		{
			transitOtpRepo.deleteByOrderId(confirmOrder.getOrderId());
			String orderId=confirmOrder.getOrderId();
			OrderDetails details=orderRepo.findByOrderId(orderId);
			String bidId=details.getBidId();
			Bid bidDetails=bidRepo.findByBidId(bidId);
			bidDetails.setAdditionalCharges(confirmOrder.getAdditionalCharges());
			bidDetails.setAdditionalChargesDesc(confirmOrder.getDescription());
			bidDetails.setTotalCost(confirmOrder.getTotalAmount());
			try {
				bidRepo.save(bidDetails);
				details.setTotalAmount(confirmOrder.getTotalAmount());
				details.setStatus(EnumOrderStatus.COMPLETED.getStatusId());
				orderRepo.save(details);
				transferFundsToPartner(details.getOrderId());
				result.setCode(TransportErrorCodes.ORDER_COMPLETED_SUCCESSFULLY);
				result.setMessage(TransportErrorMessages.ORDER_COMPLETED_SUCCESSFULLY);
				response.setResult(result);
			}
			catch(Exception ex)
			{
				throw new FailedToUpdateDetails(TransportErrorMessages.FAILED_TO_UPDATED_DETAILS);
			}
		}
		else
		{
			throw new InvalidOTP(TransportErrorMessages.INVALID_OTP);

		}
		return response;
	}
	private void transferFundsToPartner(String orderId) throws FailedToSaveWalletDetails {
		OrderDetails orderDetails=orderRepo.findByOrderId(orderId);
		double totalAmount=orderDetails.getTotalAmount();
		Settings settingsDetails=settingsRepo.findByType("HelperPrice");
		double bookingCommission=settingsDetails.getBookingCommission();
		double commission=totalAmount*bookingCommission*0.01;
		double remainingAmount=totalAmount-commission;
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
	private void checkorderIdExists(String orderId) throws InvalidOrderId {
		boolean result=orderRepo.checkOrderIdExists(orderId);
		if(!result)
		{
			throw new InvalidOrderId(TransportErrorMessages.INVALID_ORDER_ID);
		}
	}
	@Override
	public WalletResp getWalletDetails(int status,HttpServletRequest httpReq) {
		WalletResp response=new WalletResp();
		String driverId=commonUtil.getDrivrUserRefIdI(httpReq);
		
		if(status==EnumPaymentStatus.ALL.getStatusId())
		{
			List<PartnerWallet> walletDetails=walletRepo.findByPartnerId(driverId);
			response.setResult(walletDetails);
			
		}
		else
		{
			List<PartnerWallet> walletDetails=walletRepo.findByPartnerIdAndStatus(driverId,status);
			response.setResult(walletDetails);
		}
		return response;
	}
}
