/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data;

import com.axelor.data.xml.XMLImporter;
import org.junit.jupiter.api.Test;

public class XMLDataTest extends AbstractTest {

  @Test
  public void testTypes() {
    Importer importer = new XMLImporter("data/xml-config-types.xml", "data/xml");
    importer.run();
  }

  @Test
  public void testDefault() {
    Importer importer = new XMLImporter("data/xml-config.xml", "data/xml");
    importer.run();
  }
}
