/*
 Copyright (c) 2017 Touch Lab, Inc.
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.
 */

package co.doppl.so.arch;

/**
 * Model object for a single Stack Overflow question, assembled from Stack Exchange
 * API response
 */
public class Question {
  /**
   * The title of the question, as seen in lists
   */
  private String title;
  /**
   * The URL of the avatar of the person who asked this question
   */
  private String ownerAvatar;

  /**
   * Constructor. `Nuff said.
   *
   * @param title the title of the question, as seen in lists
   * @param ownerAvatar the URL of the question-asker's avatar
   */
  Question(String title, String ownerAvatar) {
    this.title=title;
    this.ownerAvatar=ownerAvatar;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the owner's avatar
   */
  public String getOwnerAvatar() {
    return ownerAvatar;
  }
}
