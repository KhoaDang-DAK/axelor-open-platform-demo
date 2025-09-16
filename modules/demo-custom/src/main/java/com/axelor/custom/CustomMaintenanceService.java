/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.custom;

import com.axelor.auth.AuthUtils;
import com.axelor.auth.db.User;
import com.axelor.web.service.MaintenanceService;
import jakarta.servlet.http.HttpServletRequest;

public class CustomMaintenanceService implements MaintenanceService {

  @Override
  public boolean isMaintenanceMode(User user, HttpServletRequest httpRequest) {
    return user != null
        && !AuthUtils.isAdmin(user)
        && user.getGroup() != null
        && user.getGroup().getEnableMaintenanceMode();
  }
}
