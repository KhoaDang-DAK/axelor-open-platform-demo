/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.project;

import com.axelor.app.AxelorModule;
import com.axelor.project.service.UpdateUserProjectQuickMenu;

public class ProjectModule extends AxelorModule {

  @Override
  protected void configure() {
    addQuickMenu(UpdateUserProjectQuickMenu.class);
  }
}
