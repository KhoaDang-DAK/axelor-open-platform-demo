/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
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
