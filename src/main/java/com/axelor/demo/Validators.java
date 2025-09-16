/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.db.JpaSequence;
import com.axelor.sale.db.Order;
import com.axelor.sale.service.SaleOrderService;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validators {

  private static final Logger logger =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Inject private SaleOrderService service;

  @SuppressWarnings("rawtypes")
  public Object validateSaleOrder(Object bean, Map context) {
    Order so = (Order) bean;

    try {
      service.validate(so);
    } catch (ValidationException e) {
      logger.error("Validation error on SO '{}': {}", so.getName(), e.getMessage());
    }
    service.calculate(so);
    JpaSequence.nextValue("sale.order.seq");

    return so;
  }
}
