/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data;

import com.axelor.data.csv.CSVImporter;
import com.axelor.db.Model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CSVImportTest extends AbstractTest {

  @Test
  public void test() throws ClassNotFoundException {
    final List<Model> records = new ArrayList<>();
    CSVImporter importer = new CSVImporter("data/csv-multi-config.xml");

    Map<String, Object> context = new HashMap<>();

    context.put("CUSTOMER_PHONE", "+3326253225");

    importer.setContext(context);

    importer.addListener(
        new Listener() {
          @Override
          public void imported(Model bean) {
            log.info("Bean saved : {}(id={})", bean.getClass().getSimpleName(), bean.getId());
            records.add(bean);
          }

          @Override
          public void imported(Integer total, Integer success) {
            log.info("Record (total): {}", total);
            log.info("Record (success): {}", success);
          }

          @Override
          public void handle(Model bean, Exception e) {}
        });

    importer.run(
        new ImportTask() {

          @Override
          public void configure() throws IOException {
            input("[sale.order]", new File("data/csv-multi/so1.csv"));
            input("[sale.order]", new File("data/csv-multi/so2.csv"));
          }

          @Override
          public boolean handle(ImportException exception) {
            log.error("Import error : {}", String.valueOf(exception));
            return false;
          }

          @Override
          public boolean handle(IOException e) {
            log.error("IOException error : {}", String.valueOf(e));
            return true;
          }
        });
  }
}
