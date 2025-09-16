/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact.service;

import com.axelor.contact.db.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements HelloService {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public String say(Contact contact) {
    return String.format("Welcome '%s!'", contact.getFullName());
  }

  @Override
  public String hello() {
    return "Hello world!!!";
  }
}
