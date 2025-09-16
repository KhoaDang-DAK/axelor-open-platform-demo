/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.data;

import com.axelor.db.EntityHelper;
import com.axelor.db.JPA;
import com.axelor.meta.db.MetaSequence;
import com.axelor.meta.db.repo.MetaSequenceRepository;
import com.axelor.test.GuiceExtension;
import com.axelor.test.GuiceModules;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(GuiceExtension.class)
@GuiceModules(DataModule.class)
public abstract class AbstractTest {

  protected final Logger log = LoggerFactory.getLogger(EntityHelper.getEntityClass(this));

  @Inject private MetaSequenceRepository sequences;

  private static boolean checked;

  @BeforeEach
  public void ensureSequence() {

    if (checked || sequences.findByName("sale.order.seq") != null) {
      return;
    }

    checked = true;

    final MetaSequence seq = new MetaSequence();
    seq.setName("sale.order.seq");
    seq.setPrefix("SO");
    seq.setPadding(5);

    JPA.runInTransaction(() -> sequences.save(seq));
  }
}
