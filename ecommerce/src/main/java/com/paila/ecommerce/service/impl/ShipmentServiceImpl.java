package com.paila.ecommerce.service.impl;


import com.paila.ecommerce.entity.Shipment;
import com.paila.ecommerce.repository.ShipmentRepo;
import com.paila.ecommerce.repository.UserRepository;
import com.paila.ecommerce.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepo shipmentRepo;
    private final UserRepository userRepository;


    //save the shipment to table
    @Override
    public Shipment saveShipmentToTable(Shipment newShipment) {

        newShipment.setUser(userRepository.getUSerByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

        return shipmentRepo.save(newShipment);
    }

//    /Get all the shipment details
    @Override
    public List<Shipment> getAllShipmentDetails() {

        return  shipmentRepo.findByUser(userRepository.getUSerByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    //Update the shipping address
    @Override
    public Shipment updateShipmentDetails(Integer shipmentId, Shipment shipment) {
        Shipment foundDetail = shipmentRepo.findById(shipmentId).get();
        foundDetail.setFirstName(shipment.getFirstName());
        foundDetail.setLastName(shipment.getLastName());
        foundDetail.setAddress(shipment.getAddress());
        foundDetail.setPostcode(shipment.getPostcode());
        foundDetail.setCity(shipment.getCity());
        foundDetail.setProvince(shipment.getProvince());
        foundDetail.setPhone(shipment.getPhone());
        foundDetail.setEmail(shipment.getEmail());

        return shipmentRepo.save(foundDetail);
    }

    //Delete the shipping address
    @Override
    public void deleteShipmentDetails(Integer shipmentId) {
        shipmentRepo.deleteById(shipmentId);
    }
}
