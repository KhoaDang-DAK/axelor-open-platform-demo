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

import com.axelor.db.EntityHelper;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogEventListener
    implements PreInsertEventListener,
        PreUpdateEventListener,
        PreDeleteEventListener,
        PostUpdateEventListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogEventListener.class);

  @Override
  public boolean onPreDelete(PreDeleteEvent event) {
    LOGGER.trace(
        "(onPreDelete) Entity {} with id {} removed",
        EntityHelper.getEntityClass(event.getEntity()).getSimpleName(),
        event.getId());
    return false;
  }

  @Override
  public boolean onPreInsert(PreInsertEvent event) {
    LOGGER.trace(
        "(onPreInsert) Entity {} with id {} inserted",
        EntityHelper.getEntityClass(event.getEntity()).getSimpleName(),
        event.getId());
    return false;
  }

  @Override
  public boolean onPreUpdate(PreUpdateEvent event) {
    LOGGER.trace(
        "(onPreUpdate) Entity {} with id {} updated",
        EntityHelper.getEntityClass(event.getEntity()).getSimpleName(),
        event.getId());
    return false;
  }

  @Override
  public void onPostUpdate(PostUpdateEvent event) {
    LOGGER.trace(
        "(onPostUpdate) Entity {} with id {} updated",
        EntityHelper.getEntityClass(event.getEntity()).getSimpleName(),
        event.getId());
  }

  @Override
  public boolean requiresPostCommitHandling(EntityPersister persister) {
    return false;
  }
}
