/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data;

import com.axelor.data.xml.XMLImporter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class XMLImportTest extends AbstractTest {

  @Test
  public void test() {
    XMLImporter importer = new XMLImporter("data/xml-config.xml");
    Map<String, Object> context = new HashMap<>();

    context.put("LOCATION", "FR");
    context.put("DATE_FORMAT", "dd/MM/yyyy");

    importer.setContext(context);

    importer.run(
        new ImportTask() {

          @Override
          public void configure() throws IOException {
            input("contacts.xml", new File("data/xml/contacts.xml"));
            input(
                "contacts.xml",
                new File("data/xml/contacts-non-unicode.xml"),
                Charset.forName("ISO-8859-15"));
          }

          @Override
          public boolean handle(ImportException exception) {
            System.err.println("Import error: " + exception);
            return true;
          }
        });
  }
}
