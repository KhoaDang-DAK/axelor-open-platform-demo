/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.contact.jobs;

import com.axelor.common.ObjectUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

/** An example {@link Job} class that prints some messages to the stderr. */
public class HelloJob implements Job {

  @Override
  public void execute(JobExecutionContext context) {
    JobDetail detail = context.getJobDetail();
    JobDataMap data = context.getJobDetail().getJobDataMap();

    String name = detail.getKey().getName();
    String desc = detail.getDescription();

    StringBuilder sb = new StringBuilder("Job fired: %s (%s)".formatted(name, desc));
    if (ObjectUtils.notEmpty(data)) {
      for (String key : data.keySet()) {
        sb.append("%n    %s = %s".formatted(key, data.getString(key)));
      }
    }

    System.err.println(sb.toString());
  }
}
