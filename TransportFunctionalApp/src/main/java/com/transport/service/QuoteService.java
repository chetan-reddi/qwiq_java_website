package com.transport.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.transport.exception.AuthenticationFailed;
import com.transport.exception.BidAlreadyPlaced;
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
import com.transport.exception.QuoteUpdationFailed;
import com.transport.exception.UnableToCompleteOrder;
import com.transport.model.Quote;
import com.transport.model.SaveTicket;
import com.transport.response.BidAcceptResponse;
import com.transport.response.CustomerOrder;
import com.transport.response.DistanceResp;
import com.transport.response.DriverBidsResponse;
import com.transport.response.EstimatedQutoeResp;
import com.transport.response.ItemResponse;
import com.transport.response.OrderDetailsResp;
import com.transport.response.OrderResponse;
import com.transport.response.TransportResponse;
import com.transport.response.VanDetailsResp;
import com.transport.response.VanSizeCalculateResp;
import com.transport.service.exception.OrderCancell;

@Service
public interface QuoteService {

//	AuctionResponse getAuctions(HttpServletRequest httpReq);
//
//	BidResponse getBidsByQuote(String quoteId, HttpServletRequest httpReq) throws BidsNotFound;
//
	BidAcceptResponse acceptBid(AcceptBid acceptBidReq, HttpServletRequest httpReq) throws FaileToAcceptBid, BidAlreadyPlaced, FailedToUpdateBidStatus;

	OrderResponse createNewOrder(OrderDetails quoteDetails, HttpServletRequest httpReq) throws OrderCreationFailed, IOException, InvalidLocation;

	OrderDetailsResp getAllCustomerOrders(HttpServletRequest httpReq);

	DriverBidsResponse getBidsByQuoteId(String quoteId) throws InvalidQuoteId;

	CustomerOrder getOrderDetails(String orderId) throws InvalidOrderId;

	EstimatedQutoeResp getEstimatedQuote(QuoteDetails quoteReq) throws IOException, InvalidLocation;

	OrderResponse editOrder(OrderDetails quoteDetails, HttpServletRequest httpReq) throws IOException, InvalidLocation, OrderUpdationFailed, AuthenticationFailed, InvalidOrderId;

	TransportResponse cancellOrder(CancelOrderReq cancelReq, HttpServletRequest httpReq) throws InvalidOrderId, OrderCancell;

	ItemResponse getAllItems();

	VanDetailsResp getVanDetails();

	VanSizeCalculateResp vanSizeCalculator(CalculateReq calculateReq) throws InvalidItemId, CapacityExceeds;

	void placeOrder(OrderDetails quoteDetails, HttpServletRequest httpReq);

	TransportResponse paymentSuccess(PaymentSuccess req) throws FailToUpdatePaymentStatus, InvalidOrderId;


	TransportResponse saveQuote(Quote quoteReq, HttpServletRequest httpReq) throws FailedToSaveDetails;

	List<QuoteDetails> getQuote(HttpServletRequest httpReq);

	void getAllQutoes(HttpServletRequest httpReq);

	TransportResponse editQuote(Quote quoteReq, HttpServletRequest httpReq);

	TransportResponse editQuote(EditQuote editQuote, HttpServletRequest httpReq) throws InvalidQuoteId, QuoteUpdationFailed;

	TransportResponse cancellQuote(CancellQuoteReq cancelReq, HttpServletRequest httpReq) throws InvalidQuoteId, QuoteCancell;

	TransportResponse completeOrder(CompleteOrder req) throws InvalidOrderId, UnableToCompleteOrder;

	DistanceResp getDistance(DistanceReq distanceReq) throws IOException, InvalidLocation;

	TransportResponse saveTicket(SaveTicket ticketReq, HttpServletRequest httpReq) throws FailedToSaveTicketDetails;

	void getTickets(HttpServletRequest httpReq);

}
