package com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public abstract class Payment {

    private final BigDecimal amount;

    public abstract void submit();
}
