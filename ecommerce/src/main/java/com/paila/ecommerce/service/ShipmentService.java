package com.paila.ecommerce.service;

import com.paila.ecommerce.entity.Shipment;

import java.util.List;

public interface ShipmentService {
    //Creating the shipment details
    Shipment saveShipmentToTable(Shipment dto);

    //GetAllShipment
    List<Shipment> getAllShipmentDetails();

    //Update the shipment
    Shipment updateShipmentDetails(Integer shipmentId, Shipment shipmentRequestDto);

    //Delete the shipment
    void deleteShipmentDetails(Integer shipmentId);
}
