package com.timbar.uam.wmi.oop.boxoffice.domain.pos;

import lombok.Value;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

@Value
public class Payment {

    BigDecimal amount;

}
