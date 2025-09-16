/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact.db.repo;

import com.axelor.common.ObjectUtils;
import com.axelor.contact.db.Contact;
import java.util.Map;

public class ContactRepository extends AbstractContactRepository {

  @Override
  public Map<String, Object> populate(Map<String, Object> json, Map<String, Object> context) {
    if (!context.containsKey("json-enhance")) {
      return json;
    }
    try {
      Long id = (Long) json.get("id");
      Contact contact = find(id);
      json.put(
          "address",
          ObjectUtils.isEmpty(contact.getAddresses()) ? null : contact.getAddresses().get(0));
      json.put("hasImage", contact.getImage() != null);
    } catch (Exception e) {
    }

    return json;
  }
}
