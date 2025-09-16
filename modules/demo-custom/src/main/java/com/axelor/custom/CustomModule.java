/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.custom;

import com.axelor.app.AxelorModule;
import com.axelor.web.service.MaintenanceService;

public class CustomModule extends AxelorModule {

  @Override
  protected void configure() {
    bind(MaintenanceService.class).to(CustomMaintenanceService.class);
  }
}
