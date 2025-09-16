/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.inject.Beans;
import com.axelor.meta.db.MetaJsonModel;
import com.axelor.meta.db.MetaJsonRecord;
import com.axelor.meta.db.repo.MetaJsonModelRepository;
import com.axelor.meta.db.repo.MetaJsonRecordRepository;
import com.axelor.rpc.Context;
import java.util.Map;

public class CustomModelImport {

  public Object importCustomModel(Object bean, Map context) {
    MetaJsonModel model = (MetaJsonModel) bean;

    Beans.get(MetaJsonModelRepository.class).save(model);

    return model;
  }

  public Object importProductExtraOptions(Object bean, Map context) {
    MetaJsonRecord record = (MetaJsonRecord) bean;
    MetaJsonRecordRepository repo = Beans.get(MetaJsonRecordRepository.class);

    Context extraOptions = repo.create(record);
    extraOptions.put("name", record.getName());
    extraOptions.put("importId", context.get("importId"));

    return repo.save(record);
  }
}
