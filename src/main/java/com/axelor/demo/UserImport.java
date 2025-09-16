/*
 * SPDX-FileCopyrightText: Axelor <https://axelor.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.axelor.demo;

import com.axelor.auth.AuthService;
import com.axelor.auth.db.User;
import com.axelor.inject.Beans;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class UserImport {

  private static final String USER_IMAGES_DIR = "user_images";

  public Object importUser(Object bean, Map context) {
    User user = (User) bean;

    Beans.get(AuthService.class).encrypt(user);

    final Path path = (Path) context.get("__path__");

    try {
      final Path image = ImportUtils.findByFileName(path.resolve(USER_IMAGES_DIR), user.getCode());
      if (image != null && image.toFile().exists()) {
        user.setImage(readFileToBytes(image));
      }
    } catch (Exception e) {
      // ignore
    }

    return user;
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private byte[] readFileToBytes(Path path) throws IOException {

    File file = path.toFile();
    byte[] bytes = new byte[(int) file.length()];

    try (FileInputStream fis = new FileInputStream(file)) {
      fis.read(bytes);
    }

    return bytes;
  }
}
