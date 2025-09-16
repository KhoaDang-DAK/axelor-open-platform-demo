/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact.event;

import com.axelor.contact.db.Contact;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(ContactListener.class);

  @PostPersist
  private void onPostPersist(Contact contact) {
    LOGGER.trace("(@PostPersist) Contact '{}' inserted", contact.getFullName());
  }

  @PostUpdate
  private void onPostUpdate(Contact contact) {
    LOGGER.trace("(@onPostUpdate) Contact '{}' updated", contact.getFullName());
  }

  @PostRemove
  private void onPostRemove(Contact contact) {
    LOGGER.trace("(@onPostRemove) Contact '{}' removed", contact.getFullName());
  }
}
