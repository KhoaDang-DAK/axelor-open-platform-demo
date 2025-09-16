/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.contact.db.Contact;
import com.axelor.event.Observes;
import com.axelor.events.PostAction;
import com.axelor.events.PostLogin;
import com.axelor.events.PostRequest;
import com.axelor.events.PreAction;
import com.axelor.events.PreLogin;
import com.axelor.events.PreRequest;
import com.axelor.events.RequestEvent;
import com.axelor.events.qualifiers.EntityType;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;

public class DemoEventLogger {

  @Inject private Logger log;

  public void onPreLogin(@Observes PreLogin event) {
    log.trace("PRE LOGIN: {}", event.getPrincipal());
  }

  public void onPostLogin(@Observes PostLogin event) {
    log.trace(
        "POST LOGIN: {}",
        event.isSuccess() ? event.getUser().getCode() : event.getError().getMessage());
  }

  public void onLoginSuccess(@Observes @Named(PostLogin.SUCCESS) PostLogin event) {
    log.trace("LOGIN SUCCESS: {}", event.getUser());
    // TIP: we can redirect to some special view after login
    // throw new LoginRedirectException("#/about");
  }

  public void onLoginFailure(@Observes @Named(PostLogin.FAILURE) PostLogin event) {
    log.trace("LOGIN FAILED: {}", event.getError().getMessage());
  }

  public void onSave(@Observes @Named(RequestEvent.SAVE) PreRequest event) {
    log.trace("ON SAVE ALL: {}", event.getRequest().getBeanClass());
  }

  public void onSaveContact(
      @Observes @Named(RequestEvent.SAVE) @EntityType(Contact.class) PreRequest event) {
    log.trace("ON SAVE CONTACT: {}", event.getRequest().getBeanClass());
  }

  public void onPreRequest(@Observes PreRequest event) {
    log.trace("PRE REQUEST: {} -> {}", event.getSource(), event.getRequest().getModel());
  }

  public void onPostRequest(@Observes PostRequest event) {
    log.trace("POST REQUEST: {} -> {}", event.getSource(), event.getRequest().getModel());
  }

  public void onPreSearch(@Observes @Named(RequestEvent.SEARCH) PreRequest event) {
    log.trace("PRE SEARCH: {}", event.getRequest().getModel());
  }

  public void onPostSearch(@Observes @Named(RequestEvent.SEARCH) PostRequest event) {
    log.trace("POST SEARCH: {}", event.getRequest().getModel());
  }

  public void onPreAction(@Observes PreAction event) {
    log.trace("PRE ACTION: {}", event.getName());
  }

  public void onPostAction(@Observes PostAction event) {
    log.trace("POST ACTION: {}", event.getName());
  }

  public void onPostActionCall(
      @Observes @Named("com.axelor.sale.web.SaleOrderController:calculate") PostAction event) {
    log.trace("POST CALCULATE: {}", event.getResult());
  }
}
