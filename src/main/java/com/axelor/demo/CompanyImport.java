/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.contact.db.Company;
import com.axelor.contact.db.repo.CompanyRepository;
import com.axelor.inject.Beans;
import com.axelor.meta.MetaFiles;
import com.axelor.meta.db.MetaFile;
import java.nio.file.Path;
import java.util.Map;

public class CompanyImport {

  private static final String COMPANY_SIGNATURES_DIR = "company_signatures";

  public Object importCompany(Object bean, Map context) {
    Company company = (Company) bean;

    Beans.get(CompanyRepository.class).save(company);

    loadSignature(company, (Path) context.get("__path__"));

    return company;
  }

  private void loadSignature(Company company, Path basePath) {
    try {
      final Path signature =
          ImportUtils.findByFileName(basePath.resolve(COMPANY_SIGNATURES_DIR), company.getCode());
      if (signature != null && signature.toFile().exists()) {
        final MetaFile metaFile = Beans.get(MetaFiles.class).upload(signature.toFile());
        company.setSignature(metaFile);
      }
    } catch (Exception e) {
      // ignore
    }
  }
}
