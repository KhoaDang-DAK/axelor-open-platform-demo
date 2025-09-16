/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data.tests;

import com.axelor.db.JpaModel;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "DATA_TYPES")
public class Types extends JpaModel {

  private LocalDate date;

  private LocalTime time;

  private LocalDateTime dateTime;

  private ZonedDateTime dateTimeTz;

  private Boolean active;

  private Integer number;

  private BigDecimal price;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public ZonedDateTime getDateTimeTz() {
    return dateTimeTz;
  }

  public void setDateTimeTz(ZonedDateTime dateTimeTz) {
    this.dateTimeTz = dateTimeTz;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(getClass())
        .add("date", date)
        .add("time", time)
        .add("datetime", dateTime)
        .add("datetime-tz", dateTimeTz)
        .add("active", active)
        .add("number", number)
        .add("price", price)
        .toString();
  }
}
