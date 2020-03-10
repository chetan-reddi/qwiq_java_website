package com.transport.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.transport.bean.AcceptBid;
import com.transport.bean.CalculateReq;
import com.transport.bean.CancelOrderReq;
import com.transport.bean.CancellQuoteReq;
import com.transport.bean.CompleteOrder;
import com.transport.bean.DeleteTicketReq;
import com.transport.bean.DistanceReq;
import com.transport.bean.EditQuote;
import com.transport.bean.EditTicket;
import com.transport.bean.PaymentSuccess;
import com.transport.bean.QuoteDetails;
import com.transport.exception.AuthenticationFailed;
import com.transport.exception.BidAlreadyPlaced;
import com.transport.exception.BidsNotFound;
import com.transport.exception.CapacityExceeds;
import com.transport.exception.FailToUpdatePaymentStatus;
import com.transport.exception.FaileToAcceptBid;
import com.transport.exception.FailedToSaveDetails;
import com.transport.exception.FailedToSaveTicketDetails;
import com.transport.exception.FailedToUpdateBidStatus;
import com.transport.exception.InvalidItemId;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidQuoteId;
import com.transport.exception.OrderCreationFailed;
import com.transport.exception.OrderUpdationFailed;
import com.transport.exception.QuoteCancell;
import com.transport.exception.QuoteException;
import com.transport.exception.QuoteUpdationFailed;
import com.transport.exception.UnableToCompleteOrder;
import com.transport.model.Quote;
import com.transport.model.SaveTicket;
import com.transport.response.BidAcceptResponse;
import com.transport.response.DistanceResp;
import com.transport.response.DriverBidsResponse;
import com.transport.response.EstimatedQutoeResp;
import com.transport.response.ItemResponse;
import com.transport.response.OrderDetailsResp;
import com.transport.response.TicketResponse;
import com.transport.response.TransportResponse;
import com.transport.response.VanDetailsResp;
import com.transport.response.VanSizeCalculateResp;
import com.transport.service.QuoteService;
import com.transport.service.exception.OrderCancell;
@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class CustomerController {

	@Autowired
	QuoteService quoteService;
//	@RequestMapping(value="/placeOrder",method=RequestMethod.POST)
//	private void placeOrder(@Valid @RequestBody OrderDetails quoteDetails,HttpServletRequest httpReq) throws QuoteException, OrderCreationFailed, IOException, InvalidLocation
//	{
//		quoteService.placeOrder(quoteDetails,httpReq);
//	}
//	@RequestMapping(value="/createNewOrder",method=RequestMethod.POST)
//	private OrderResponse createNewOrder(@Valid @RequestBody OrderDetails quoteDetails,HttpServletRequest httpReq) throws QuoteException, OrderCreationFailed, IOException, InvalidLocation
//	{
//		return quoteService.createNewOrder(quoteDetails,httpReq);
//	}
//	@RequestMapping(value="/editOrder",method=RequestMethod.POST)
//	private OrderResponse editOrder(@Valid @RequestBody OrderDetails quoteDetails,HttpServletRequest httpReq) throws QuoteException, OrderCreationFailed, IOException, InvalidLocation, OrderUpdationFailed, AuthenticationFailed, InvalidOrderId
//	{
//		return quoteService.editOrder(quoteDetails,httpReq);
//	}
	@RequestMapping(value="/cancellOrder",method=RequestMethod.POST)
	private TransportResponse cancellOrder(@Valid @RequestBody CancelOrderReq cancelReq,HttpServletRequest httpReq) throws QuoteException, OrderCreationFailed, IOException, InvalidLocation, OrderUpdationFailed, AuthenticationFailed, InvalidOrderId, OrderCancell
	{
		return quoteService.cancellOrder(cancelReq,httpReq);
	}
	
	@RequestMapping(value="/getAllCustomerOrders",method=RequestMethod.GET)
	private OrderDetailsResp getAllCustomerOrders(HttpServletRequest httpReq) throws QuoteException	{
	return quoteService.getAllCustomerOrders(httpReq);
	}

	@RequestMapping(value="/getBidsByQuoteId/{quoteId}",method=RequestMethod.GET)
	private DriverBidsResponse getBidsByQuoteId(@PathVariable("quoteId") String quoteId) throws QuoteException, BidsNotFound, InvalidQuoteId
	{
		return quoteService.getBidsByQuoteId(quoteId);
	}
	@RequestMapping(value="/acceptBid",method=RequestMethod.POST)
	private BidAcceptResponse acceptBid(@RequestBody AcceptBid acceptBidReq,HttpServletRequest httpReq) throws FaileToAcceptBid, BidAlreadyPlaced, FailedToUpdateBidStatus 
	{
		return quoteService.acceptBid(acceptBidReq,httpReq);
	}
//	@RequestMapping(value="/getOrderDetailsByOrderId/{orderId}",method=RequestMethod.GET)
//	private CustomerOrder getOrderDetails(@PathVariable("orderId") String orderId) throws InvalidOrderId 
//	{
//		return quoteService.getOrderDetails(orderId);
//	}
	@RequestMapping(value="/getEstimatedQuote",method=RequestMethod.POST)
	private EstimatedQutoeResp getEstimatedQuote(@Valid @RequestBody QuoteDetails quoteReq) throws InvalidOrderId, IOException, InvalidLocation 
	{
		 return quoteService.getEstimatedQuote(quoteReq);
	}
	@RequestMapping(value="/getDistance",method=RequestMethod.POST)
	private DistanceResp getDistance(@Valid @RequestBody DistanceReq distanceReq) throws InvalidOrderId, IOException, InvalidLocation 
	{
		 return quoteService.getDistance(distanceReq);
	}
	@RequestMapping(value="/saveQuote",method=RequestMethod.POST)
	private TransportResponse saveQuote(@Valid @RequestBody Quote  quoteReq,HttpServletRequest httpReq) throws FailedToSaveDetails  
	{
		  return quoteService.saveQuote(quoteReq,httpReq);
	}
	@RequestMapping(value="/editQuote",method=RequestMethod.POST)
	private TransportResponse editQuote(@Valid @RequestBody EditQuote  editQuote,HttpServletRequest httpReq) throws InvalidQuoteId, QuoteUpdationFailed  
	{
		  return quoteService.editQuote(editQuote,httpReq);
	}
	@RequestMapping(value="/cancellQuote",method=RequestMethod.POST)
	private TransportResponse cancellQuote(@Valid @RequestBody CancellQuoteReq cancelReq,HttpServletRequest httpReq) throws InvalidQuoteId, QuoteCancell
	{
		return quoteService.cancellQuote(cancelReq,httpReq);
	}
	@RequestMapping(value="/getQuotesByUser",method=RequestMethod.GET)
	private List<QuoteDetails> getQuote(HttpServletRequest httpReq)   
	{
		  return quoteService.getQuote(httpReq);
	}
	@RequestMapping(value="/vanSizeCalculator",method=RequestMethod.POST)
	private VanSizeCalculateResp vanSizeCalculator(@Valid @RequestBody CalculateReq calculateReq) throws InvalidItemId, CapacityExceeds 
	{
		 return quoteService.vanSizeCalculator(calculateReq);
	}
	@RequestMapping(value="/getAllItems",method=RequestMethod.GET)
	private ItemResponse getAllItems() throws InvalidOrderId, IOException, InvalidLocation 
	{
		 return quoteService.getAllItems();
	}
	@RequestMapping(value="/getVanDetails",method=RequestMethod.GET)
	private VanDetailsResp getVanDetails()  
	{
		 return quoteService.getVanDetails();
	}
	@RequestMapping(value="/paymentSuccess",method=RequestMethod.POST)
	private TransportResponse paymentSuccess(@RequestBody PaymentSuccess req,HttpServletRequest httpReq) throws FailToUpdatePaymentStatus, InvalidOrderId  
	{
		 return quoteService.paymentSuccess(req);
	}
	@RequestMapping(value="/completeOrder",method=RequestMethod.POST)
	private TransportResponse completeOrder(@RequestBody CompleteOrder req,HttpServletRequest httpReq) throws InvalidOrderId, UnableToCompleteOrder   
	{
		 return quoteService.completeOrder(req);
	}
	
	@RequestMapping(value="/saveTicket",method=RequestMethod.POST)
	private TransportResponse saveTicket(@RequestBody SaveTicket ticketReq,HttpServletRequest httpReq) throws InvalidOrderId, UnableToCompleteOrder, FailedToSaveTicketDetails   
	{
		 return quoteService.saveTicket(ticketReq,httpReq);
	}
	@RequestMapping(value="/getTicketsByUser",method=RequestMethod.GET)
	private TicketResponse getTickets(HttpServletRequest httpReq) 
	{
		 return quoteService.getTickets(httpReq);
	}
	
	@RequestMapping(value="/editTicket",method=RequestMethod.POST)
	private TransportResponse editTicket(@Valid @RequestBody EditTicket  editTicket,HttpServletRequest httpReq) throws InvalidQuoteId, QuoteUpdationFailed  
	{
		  return quoteService.editTicket(editTicket,httpReq);
	}
	
	@RequestMapping(value="/deleteTicketById",method=RequestMethod.POST)		
	private TransportResponse deleteTicketById(@Valid @RequestBody DeleteTicketReq ticket,HttpServletRequest httpReq) throws InvalidItemId, CapacityExceeds
	{
		System.out.print("Ticket Id :: "+ticket.getTicketId());
		 return quoteService.deleteTicket(ticket.getTicketId(),httpReq);
	}
//	@RequestMapping(value="/getPartnerWalletDetails/{status}",method=RequestMethod.POST)
//	private TransportResponse completeOrder(@RequestBody CompleteOrder req,HttpServletRequest httpReq) throws InvalidOrderId, UnableToCompleteOrder   
//	{
//		 return quoteService.completeOrder(req);
//	}
}
