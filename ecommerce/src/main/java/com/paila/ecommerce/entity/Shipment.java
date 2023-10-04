package com.paila.ecommerce.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;


    private String address;
    private Integer postcode;

    private String city;

    private String province;

    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
