/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.app;

import com.axelor.data.csv.CSVImporter;
import com.axelor.test.GuiceExtension;
import com.axelor.test.GuiceModules;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GuiceExtension.class)
@GuiceModules({MyModule.class})
public class DataTest {

  @Test
  public void test() {
    CSVImporter importer =
        new CSVImporter(
            "src/main/resources/data-demo/input-config.xml", "src/main/resources/data-demo/input");
    importer.run();
  }
}
