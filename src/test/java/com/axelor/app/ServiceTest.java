/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.axelor.contact.db.Contact;
import com.axelor.contact.service.HelloService;
import com.axelor.test.GuiceExtension;
import com.axelor.test.GuiceModules;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GuiceExtension.class)
@GuiceModules({MyModule.class})
public class ServiceTest {

  @Inject private HelloService service;

  @Test
  public void test() {

    Contact contact = new Contact();
    contact.setFirstName("John");
    contact.setLastName("Smith");

    String said = service.say(contact);
    String hello = service.hello();

    assertEquals("You are welcome 'John Smith!'", said);
    assertEquals("Hello world!!!", hello);
  }
}
