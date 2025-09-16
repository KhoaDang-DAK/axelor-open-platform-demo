/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.common.ObjectUtils;
import com.axelor.common.StringUtils;
import com.axelor.project.db.ProjectTask;
import com.axelor.project.db.repo.ProjectTaskRepository;
import com.google.inject.persist.Transactional;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.Map;

public class ProjectImport {

  private final ProjectTaskRepository taskRepository;

  @Inject
  public ProjectImport(ProjectTaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Transactional
  public Object importTask(Object bean, Map context) {
    var task = (ProjectTask) bean;
    var subTaskNamesString = (String) context.get("subTasks[].name");

    if (StringUtils.notBlank(subTaskNamesString)) {
      var subTaskNames = Arrays.asList(subTaskNamesString.split("\\s*\\|\\s*"));
      if (ObjectUtils.notEmpty(subTaskNames)) {
        taskRepository
            .all()
            .filter("self.name IN :names")
            .bind("names", subTaskNames)
            .fetch()
            .forEach(task::addSubTask);
      }
    }

    return task;
  }
}
