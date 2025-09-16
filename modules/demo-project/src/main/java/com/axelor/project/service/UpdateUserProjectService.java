/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.project.service;

import com.axelor.auth.db.User;
import com.axelor.auth.db.repo.UserRepository;
import com.axelor.inject.Beans;
import com.axelor.project.db.Project;
import com.google.inject.persist.Transactional;

public class UpdateUserProjectService {

  @Transactional
  public void updateDefaultProject(User user, Project project) {
    user.setDefaultProject(project);
    Beans.get(UserRepository.class).save(user);
  }
}
