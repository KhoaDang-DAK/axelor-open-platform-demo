/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import static com.axelor.common.StringUtils.isBlank;

import com.axelor.contact.db.Contact;
import com.axelor.contact.db.repo.ContactRepository;
import com.axelor.db.Model;
import com.axelor.db.Query;
import com.axelor.inject.Beans;
import com.axelor.mail.service.MailServiceImpl;
import com.google.common.base.Joiner;
import jakarta.inject.Singleton;
import jakarta.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DemoMailService extends MailServiceImpl {

  @Override
  public Model resolve(String email) {
    final ContactRepository contacts = Beans.get(ContactRepository.class);
    final Contact contact = contacts.findByEmail(email);
    if (contact != null) {
      return contact;
    }
    return super.resolve(email);
  }

  /** Override to return contact email addresses. */
  @Override
  public List<InternetAddress> findEmails(String matching, List<String> selected, int maxResult) {

    final List<String> where = new ArrayList<>();
    final Map<String, Object> params = new HashMap<>();

    where.add("self.email is not null");

    if (!isBlank(matching)) {
      where.add(
          "(LOWER(self.email) like LOWER(:email) OR LOWER(self.fullName) like LOWER(:email))");
      params.put("email", "%" + matching + "%");
    }
    if (selected != null && !selected.isEmpty()) {
      where.add("self.email not in (:selected)");
      params.put("selected", selected);
    }

    final String filter = Joiner.on(" AND ").join(where);
    final Query<Contact> query = Query.of(Contact.class);

    if (!isBlank(filter)) {
      query.filter(filter);
      query.bind(params);
    }

    final List<InternetAddress> addresses = new ArrayList<>();
    for (Contact contact : query.fetch(maxResult)) {
      try {
        final InternetAddress item = new InternetAddress(contact.getEmail(), contact.getFullName());
        addresses.add(item);
      } catch (UnsupportedEncodingException e) {
      }
    }

    return addresses;
  }
}
