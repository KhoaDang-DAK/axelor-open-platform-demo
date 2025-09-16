/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.app.AxelorModule;
import com.axelor.mail.service.MailService;

public class DemoModule extends AxelorModule {

  @Override
  protected void configure() {
    bind(MailService.class).to(DemoMailService.class);
    bind(DemoEventLogger.class);
  }
}
