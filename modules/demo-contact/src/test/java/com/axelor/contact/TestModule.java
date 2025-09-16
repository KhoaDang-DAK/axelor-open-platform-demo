/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact;

import com.axelor.app.AppModule;
import com.axelor.db.JpaModule;
import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new JpaModule("testUnit"));
    install(new AppModule());
  }
}
