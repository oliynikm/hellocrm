package com.gmail.oleynikn.hellocrm.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("PHONE_CALL")
public class PhoneCall extends Interaction {

    @Transient
    private final String phone = "+100200020002";

    public String getPhone() {
        return phone;
    }
}
