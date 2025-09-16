/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data.tests;

import com.axelor.contact.db.Contact;
import com.axelor.db.JPA;
import com.axelor.sale.db.Order;
import com.axelor.sale.service.SaleOrderService;
import com.google.inject.Inject;
import java.util.Map;

public class Validators {

  @Inject private SaleOrderService soService;

  public Object validateSaleOrder(Object bean, Map<String, Object> context) {
    assert bean instanceof Order;
    Order so = (Order) bean;

    soService.validate(so);

    System.err.println("Date: " + so.getOrderDate());
    System.err.println("Customer: " + so.getCustomer().getFullName());
    System.err.println("Items: " + so.getItems().size());

    long count = JPA.all(Contact.class).count();
    assert count > 1;

    return bean;
  }
}
