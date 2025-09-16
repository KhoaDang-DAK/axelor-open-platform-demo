/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.sale.service;

import com.axelor.common.ObjectUtils;
import com.axelor.sale.db.Order;
import com.axelor.sale.db.OrderLine;
import com.axelor.sale.db.Tax;
import jakarta.validation.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleOrderService {

  public void validate(Order order) {
    if (order != null
        && order.getConfirmDate() != null
        && order.getConfirmDate().isBefore(order.getOrderDate())) {
      throw new ValidationException("Invalid sale order, confirm date is before order date.");
    }
  }

  public Order calculate(Order order) {

    BigDecimal amount = BigDecimal.ZERO;
    BigDecimal taxAmount = BigDecimal.ZERO;

    if (!ObjectUtils.isEmpty(order.getItems())) {
      for (OrderLine item : order.getItems()) {
        BigDecimal value = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
        BigDecimal taxValue = BigDecimal.ZERO;

        if (!ObjectUtils.isEmpty(item.getTaxes())) {
          for (Tax tax : item.getTaxes()) {
            taxValue = taxValue.add(tax.getRate().multiply(value));
          }
        }

        amount = amount.add(value);
        taxAmount = taxAmount.add(taxValue);
      }
    }

    int decimalPlaces = 2;
    if (order.getCurrency() != null) {
      decimalPlaces = order.getCurrency().getDecimalPlaces();
    }

    order.setAmount(amount.setScale(decimalPlaces, RoundingMode.HALF_UP));
    order.setTaxAmount(taxAmount.setScale(decimalPlaces, RoundingMode.HALF_UP));
    order.setTotalAmount(amount.add(taxAmount).setScale(decimalPlaces, RoundingMode.HALF_UP));

    return order;
  }
}
