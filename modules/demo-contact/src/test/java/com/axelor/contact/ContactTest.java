/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.axelor.contact.service.HelloService;
import com.axelor.inject.Beans;
import com.axelor.test.GuiceExtension;
import com.axelor.test.GuiceModules;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GuiceExtension.class)
@GuiceModules({TestModule.class})
public class ContactTest {

  @Test
  public void test() {
    HelloService service = Beans.get(HelloService.class);
    assertNotNull(service);
    assertEquals("Hello world!!!", service.hello());
  }
}
