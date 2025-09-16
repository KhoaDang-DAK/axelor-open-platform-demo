/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact;

import com.axelor.app.AxelorModule;
import com.axelor.contact.event.CustomEventListenerConfigurator;
import com.axelor.contact.service.AccessContactQuickMenu;
import com.axelor.contact.service.HelloService;
import com.axelor.contact.service.HelloServiceImpl;

public class ContactModule extends AxelorModule {

  @Override
  protected void configure() {
    bind(HelloService.class).to(HelloServiceImpl.class);

    addHibernateListenerConfigurator(CustomEventListenerConfigurator.class);
    addQuickMenu(AccessContactQuickMenu.class);
  }
}
