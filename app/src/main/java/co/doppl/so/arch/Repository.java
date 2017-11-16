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

import java.util.ArrayList;
import java.util.List;

import co.doppl.so.api.SOInterface;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Manages our interaction with back-end operations, in this case with the
 * Stack Exchange server via its REST API.
 */
public class Repository {
  /**
   * Singleton instance of our repository
   */
  private static Repository INSTANCE=new Repository();
  /**
   * Configured interface for accessing the Stack Exchange API
   */
  private final SOInterface so;

  /**
   * @return the singleton instance of the repository
   */
  public static Repository get() {
    return(INSTANCE);
  }

  private Repository() {
    Retrofit retrofit=
      new Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();

    so=retrofit.create(SOInterface.class);
  }

  /**
   * Returns a fresh result from the Stack Exchange API, unwrapping the
   * response into a collection of model objects (Question).
   *
   * @return a list of model objects, wrapped in an Rx Single
   */
  public Single<List<Question>> current() {
    return(so.recent().map(response -> {
      List<Question> result=new ArrayList<>(response.getItems().size());

      for (SOInterface.Item item : response.getItems()) {
        result.add(new Question(item.getTitle(), item.getOwner().profileImage));
      }

      return result;
    }));
  }
}
