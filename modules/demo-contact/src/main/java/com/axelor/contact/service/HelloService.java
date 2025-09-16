/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact.service;

import com.axelor.contact.db.Contact;

public interface HelloService {

  String say(Contact contact);

  String hello();
}
