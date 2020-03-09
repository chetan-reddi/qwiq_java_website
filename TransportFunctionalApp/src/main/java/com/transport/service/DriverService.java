package com.transport.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.transport.bean.AddDriver;
import com.transport.bean.AssignDriver;
import com.transport.bean.CancelReq;
import com.transport.bean.Charges;
import com.transport.bean.ConfirmOrder;
import com.transport.bean.DistanceReq;
import com.transport.bean.DriverStatus;
import com.transport.bean.EditBid;
import com.transport.bean.RegisterVehicle;
import com.transport.bean.TransitRequest;
import com.transport.bean.VehicleStatus;
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
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidOTP;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidOwner;
import com.transport.exception.InvalidQuoteId;
import com.transport.exception.InvalidVehicleId;
import com.transport.exception.InvalidVehicleOperationStatus;
import com.transport.exception.InvalidorderDetailsId;
import com.transport.exception.SendOTPFailed;
import com.transport.exception.TransportException;
import com.transport.exception.VehicleRegFailed;
import com.transport.exception.VehicleUploadFailed;
import com.transport.model.Bid;
import com.transport.response.ActiveBidsResponse;
import com.transport.response.CofirmOrdersResp;
import com.transport.response.DistanceResponse;
import com.transport.response.DriverDetails;
import com.transport.response.OrderDetailsResp;
import com.transport.response.PartnerCharges;
import com.transport.response.PartnerVehResp;
import com.transport.response.QuoteResponse;
import com.transport.response.TransportResponse;
import com.transport.response.VehicleRegResp;
import com.transport.response.VehiclesListResponse;
import com.transport.response.WalletResp;

@Service
public interface DriverService {

	VehicleRegResp addVehicle(RegisterVehicle addVehicle, HttpServletRequest httpReq) throws TransportException, VehicleRegFailed, VehicleUploadFailed;

	VehiclesListResponse getVehiclesList(HttpServletRequest httpReq);

	OrderDetailsResp getAllDriverOrders(HttpServletRequest httpReq);

	TransportResponse placeBid(Bid bidReq, HttpServletRequest httpReq) throws BidException, InvalidQuoteId, BidAlreadyPlaced, InvalidorderDetailsId, InvalidOrderId;

	ActiveBidsResponse getMyBids(HttpServletRequest httpReq);

	CofirmOrdersResp getConfirmOrders(HttpServletRequest httpReq);

	DistanceResponse getDistance(DistanceReq distance) throws IOException, InvalidLocation;

	TransportResponse addDriver(AddDriver addVehicle, HttpServletRequest httpReq) throws FailedToRegDriver;

	List<DriverDetails> getAllDrivers(HttpServletRequest httpReq);

	TransportResponse assignDriverToVehicle(AssignDriver assignReq, HttpServletRequest httpReq) throws InvalidDriverId, InvalidVehicleId, FaileToAssignDriver;

	TransportResponse changeVehilceStatus(VehicleStatus status, HttpServletRequest httpReq) throws InvalidVehicleId, InvalidOwner, FailedToChangeVehicleStatus, InvalidVehicleOperationStatus;

	TransportResponse changeDriverStatus(DriverStatus status, HttpServletRequest httpReq) throws InvalidDriverId, InvalidOwner, FailedToChangeDriverOperationStatus, InvalidDriverOperationStatus;

	TransportResponse editBid(EditBid bidReq, HttpServletRequest httpReq) throws BidUpdationFailed, BidIdNotFound;

	TransportResponse cancelBid(CancelReq cancellReq, HttpServletRequest httpReq) throws BidIdNotFound, BidCancellFailed, BidCancellationStatus;

	TransportResponse edit(RegisterVehicle addVehicle, HttpServletRequest httpReq) throws TransportException, VehicleRegFailed, VehicleUploadFailed;

	TransportResponse editDriver(AddDriver addVehicle, HttpServletRequest httpReq) throws FailedToRegDriver;

	QuoteResponse getQuotes(HttpServletRequest httpReq);

	OrderDetailsResp getDriverOrders(HttpServletRequest httpReq);

	TransportResponse transitRequest(TransitRequest req, HttpServletRequest httpReq) throws SendOTPFailed;

	TransportResponse confirmTransitRequest(TransitRequest req, HttpServletRequest httpReq) throws InvalidOrderId, InvalidOTP;

	PartnerCharges getPartnerCharges(Charges req, HttpServletRequest httpReq);

	PartnerVehResp getPartnersVehicleList(HttpServletRequest httpReq);

	TransportResponse confirmOrder(ConfirmOrder confirmOrder) throws FailedToUpdateDetails, InvalidOrderId, InvalidOTP;

	WalletResp getWalletDetails(int status, HttpServletRequest httpReq);

}
