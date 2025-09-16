/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact.event;

import com.axelor.db.audit.HibernateListenerConfigurator;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;

public class CustomEventListenerConfigurator implements HibernateListenerConfigurator {

  @Override
  public void registerListeners(EventListenerRegistry registry) {
    final LogEventListener contactEventListener = new LogEventListener();
    registry.appendListeners(EventType.PRE_INSERT, contactEventListener);
    registry.appendListeners(EventType.PRE_UPDATE, contactEventListener);
    registry.appendListeners(EventType.PRE_DELETE, contactEventListener);
    registry.appendListeners(EventType.POST_UPDATE, contactEventListener);
  }
}
