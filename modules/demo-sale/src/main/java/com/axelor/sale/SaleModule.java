/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.sale;

import com.axelor.app.AxelorModule;
import com.axelor.contact.service.AccessContactQuickMenu;
import com.axelor.contact.service.HelloServiceImpl;
import com.axelor.sale.service.AccessSaleQuickMenu;
import com.axelor.sale.service.HelloServiceSaleImpl;

public class SaleModule extends AxelorModule {

  @Override
  protected void configure() {
    bind(HelloServiceImpl.class).to(HelloServiceSaleImpl.class);
    bind(AccessContactQuickMenu.class).to(AccessSaleQuickMenu.class);
  }
}
