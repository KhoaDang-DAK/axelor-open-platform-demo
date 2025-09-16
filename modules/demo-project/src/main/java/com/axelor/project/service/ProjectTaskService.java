/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.project.service;

import com.axelor.common.ObjectUtils;
import com.axelor.project.db.ProjectTask;

public class ProjectTaskService {

  public void computeSubTasks(ProjectTask task) {
    var subTasks = task.getSubTasks();

    if (ObjectUtils.isEmpty(subTasks)) {
      return;
    }

    var startDate = task.getStartDate();
    var endDate = task.getEndDate();

    for (var subTask : task.getSubTasks()) {
      // Update start date
      if (startDate != null
          && (subTask.getStartDate() == null || subTask.getStartDate().isBefore(startDate))) {
        subTask.setStartDate(startDate);
      } else if (endDate != null && subTask.getStartDate().isAfter(endDate)) {
        subTask.setStartDate(endDate);
      }

      // Update end date
      if (endDate != null
          && (subTask.getEndDate() == null || subTask.getEndDate().isAfter(task.getEndDate()))) {
        subTask.setEndDate(endDate);
      } else if (startDate != null && subTask.getEndDate().isBefore(startDate)) {
        subTask.setEndDate(startDate);
      }

      // Recursively update sub-tasks of this task.
      computeSubTasks(subTask);
    }
  }
}
