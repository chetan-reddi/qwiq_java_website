package com.transport.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transport.bean.AddDriver;
import com.transport.bean.AssignDriver;
import com.transport.bean.CancelReq;
import com.transport.bean.Charges;
import com.transport.bean.ConfirmOrder;
import com.transport.bean.DriverStatus;
import com.transport.bean.EditBid;
import com.transport.bean.RegisterVehicle;
import com.transport.bean.TransitRequest;
import com.transport.bean.VehicleStatus;
import com.transport.bean.WalletStatus;
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
import com.transport.exception.FailedToUpdateDetails;
import com.transport.exception.InvalidDriverId;
import com.transport.exception.InvalidDriverOperationStatus;
import com.transport.exception.InvalidOTP;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidOwner;
import com.transport.exception.InvalidQuoteId;
import com.transport.exception.InvalidVehicleId;
import com.transport.exception.InvalidVehicleOperationStatus;
import com.transport.exception.InvalidorderDetailsId;
import com.transport.exception.MailExistsException;
import com.transport.exception.SendOTPFailed;
import com.transport.exception.TransportException;
import com.transport.exception.UserNameExistsException;
import com.transport.exception.VehicleRegFailed;
import com.transport.exception.VehicleUploadFailed;
import com.transport.model.Bid;
import com.transport.response.ActiveBidsResponse;
import com.transport.response.CofirmOrdersResp;
import com.transport.response.DriverDetails;
import com.transport.response.OrderDetailsResp;
import com.transport.response.PartnerCharges;
import com.transport.response.PartnerVehResp;
import com.transport.response.QuoteResponse;
import com.transport.response.TransportResponse;
import com.transport.response.VehicleRegResp;
import com.transport.response.VehiclesListResponse;
import com.transport.response.WalletResp;
import com.transport.service.DriverService;

@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class DriverController {

	@Autowired
	DriverService driverService;
	@RequestMapping(value="/addVehicle",method=RequestMethod.POST)		
	private VehicleRegResp addVehicle(@Valid @RequestBody RegisterVehicle addVehicle,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, VehicleUploadFailed
	{
		return driverService.addVehicle(addVehicle,httpReq);
	}
	@RequestMapping(value="/edit/vehicleDetails",method=RequestMethod.POST)		
	private TransportResponse edit(@Valid @RequestBody RegisterVehicle addVehicle,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, VehicleUploadFailed
	{
		return driverService.edit(addVehicle,httpReq);
	}
	@RequestMapping(value="/changeVehicleStatus",method=RequestMethod.POST)		
	private TransportResponse changeVehilceStatus(@Valid @RequestBody VehicleStatus status,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, VehicleUploadFailed, InvalidVehicleId, InvalidOwner, FailedToChangeVehicleStatus, InvalidVehicleOperationStatus
	{
		return driverService.changeVehilceStatus(status,httpReq);
	}
	@RequestMapping(value="/getVehiclesList",method=RequestMethod.GET)		
	private VehiclesListResponse getVehiclesList(HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed
	{
		return driverService.getVehiclesList(httpReq);
	}
	@RequestMapping(value="/addDriver",method=RequestMethod.POST)		
	private TransportResponse addDriver(@Valid @RequestBody AddDriver addVehicle,HttpServletRequest httpReq) throws FailedToRegDriver 
	{
		return driverService.addDriver(addVehicle,httpReq);
	}
	@RequestMapping(value="/edit/driver",method=RequestMethod.POST)		
	private TransportResponse editDriver(@Valid @RequestBody AddDriver addVehicle,HttpServletRequest httpReq) throws FailedToRegDriver 
	{
		return driverService.editDriver(addVehicle,httpReq);
	}
	@RequestMapping(value="/changeDriverStatus",method=RequestMethod.POST)		
	private TransportResponse changeDriverStatus(@Valid @RequestBody DriverStatus status,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, VehicleUploadFailed, InvalidVehicleId, InvalidOwner, FailedToChangeVehicleStatus, InvalidDriverId, FailedToChangeDriverOperationStatus, InvalidDriverOperationStatus
	{
		return driverService.changeDriverStatus(status,httpReq);
	}
	@RequestMapping(value="/getAllDrivers",method=RequestMethod.GET)		
	private List<DriverDetails> getAllDrivers(HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed
	{
		return driverService.getAllDrivers(httpReq);
	}
	@RequestMapping(value="/assignDriverToVehicle",method=RequestMethod.POST)		
	private TransportResponse assignDriverToVehicle(@RequestBody AssignDriver assignReq,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, InvalidDriverId, InvalidVehicleId, FaileToAssignDriver
	{
		 return driverService.assignDriverToVehicle(assignReq,httpReq);
	}
	
	@RequestMapping(value="/getQuotes",method=RequestMethod.GET)		
	private QuoteResponse getQuotes(HttpServletRequest httpReq)
	{
		return driverService.getQuotes(httpReq);
	}
	@RequestMapping(value="/getAllDriverOrders",method=RequestMethod.GET)		
	private OrderDetailsResp getAllDriverOrders(HttpServletRequest httpReq)
	{
		return driverService.getAllDriverOrders(httpReq);
	}
	
	@RequestMapping(value="/getPartnerOrders",method=RequestMethod.GET)		
	private OrderDetailsResp getDriverOrders(HttpServletRequest httpReq)
	{
		return driverService.getDriverOrders(httpReq);
	}
	@RequestMapping(value="/placeBid",method=RequestMethod.POST)		
	private TransportResponse placeBid(@RequestBody Bid bidReq,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, BidException, InvalidQuoteId, BidAlreadyPlaced, InvalidorderDetailsId, InvalidOrderId
	{
		
		return driverService.placeBid(bidReq,httpReq);
	}
	@RequestMapping(value="/editBid",method=RequestMethod.POST)		
	private TransportResponse editBid(@RequestBody EditBid bidReq,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, BidException, InvalidQuoteId, BidAlreadyPlaced, InvalidorderDetailsId, InvalidOrderId, BidUpdationFailed, BidIdNotFound
	{
		return driverService.editBid(bidReq,httpReq);
	}
	@RequestMapping(value="/cancelBid",method=RequestMethod.POST)		
	private TransportResponse cancelBid(@RequestBody CancelReq bidCancellReq,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, BidException, InvalidQuoteId, BidAlreadyPlaced, InvalidorderDetailsId, InvalidOrderId, BidIdNotFound, BidCancellFailed, BidCancellationStatus
	{
		return driverService.cancelBid(bidCancellReq,httpReq);
	}
	
	
	@RequestMapping(value="/getDriverBids",method=RequestMethod.GET)		
	private ActiveBidsResponse getMyBids(HttpServletRequest httpReq) 
	{
		return driverService.getMyBids(httpReq);
	}
	@RequestMapping(value="/getConfirmOrders",method=RequestMethod.GET)		
	private CofirmOrdersResp getConfirmOrders(HttpServletRequest httpReq) 
	{
		return driverService.getConfirmOrders(httpReq);
	}
	
	@RequestMapping(value="/transitRequest",method=RequestMethod.POST)		
	private TransportResponse transitRequest(@RequestBody TransitRequest req,HttpServletRequest httpReq) throws SendOTPFailed 
	{
		 return driverService.transitRequest(req,httpReq);
	}
	@RequestMapping(value="/partnerCharges",method=RequestMethod.POST)		
	private PartnerCharges getPartnerCharges(@RequestBody Charges req,HttpServletRequest httpReq)
	{
		
		 return driverService.getPartnerCharges(req,httpReq);
	}
	@RequestMapping(value="/confirmTransitRequest",method=RequestMethod.POST)		
	private TransportResponse confirmTransitRequest(@RequestBody TransitRequest req,HttpServletRequest httpReq) throws SendOTPFailed, InvalidOrderId, InvalidOTP 
	{
		 return driverService.confirmTransitRequest(req,httpReq);
	}
	@RequestMapping(value="/getPartnersVehicleList",method=RequestMethod.GET)		
	private PartnerVehResp getPartnersVehicleList(HttpServletRequest httpReq)  
	{
		 return driverService.getPartnersVehicleList(httpReq);
	}
	@RequestMapping(value="/confirmOrder",method=RequestMethod.POST)		
	private TransportResponse confirmOrder(@RequestBody ConfirmOrder confirmOrder) throws FailedToUpdateDetails, InvalidOrderId, InvalidOTP  
	{
		return  driverService.confirmOrder(confirmOrder);
	}
	@RequestMapping(value="/getWalletDetailsByStatus",method=RequestMethod.POST)		
	private WalletResp getWalletDetails(@RequestBody WalletStatus status,HttpServletRequest httpReq) throws FailedToUpdateDetails, InvalidOrderId, InvalidOTP  
	{
		System.out.println("hIIIIIIIIIIIIIi");
		return  driverService.getWalletDetails(status.getStatus(),httpReq);
	}
//	@RequestMapping(value="/partnerCharges",method=RequestMethod.POST)		
//	private PartnerCharges getPartnerCharges(@RequestBody ChargesRequest req,HttpServletRequest httpReq)
//	{
//		System.out.println("hIIIIIIIIIIIIIi");
//		 return driverService.getPartnerCharges(req,httpReq);
//	}
}
