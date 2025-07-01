/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2025 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
