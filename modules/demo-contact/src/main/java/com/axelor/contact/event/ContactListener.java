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
