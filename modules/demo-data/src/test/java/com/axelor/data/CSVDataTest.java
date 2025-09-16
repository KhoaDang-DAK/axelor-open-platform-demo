/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data;

import com.axelor.data.csv.CSVImporter;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CSVDataTest extends AbstractTest {

  @Test
  public void testDefault() {
    Importer importer = new CSVImporter("data/csv-config.xml", "data/csv");
    importer.run();
  }

  @Test
  public void testMulti() {
    Importer importer = new CSVImporter("data/csv-multi-config.xml");
    importer.run(
        new ImportTask() {
          @Override
          public void configure() throws IOException {
            input("[sale.order]", new File("data/csv-multi/so1.csv"));
            input("[sale.order]", new File("data/csv-multi/so2.csv"));
          }
        });
  }

  @Test
  public void testData() {
    Importer importer = new CSVImporter("data/csv-config-types.xml", "data/csv");
    importer.run();
  }
}
