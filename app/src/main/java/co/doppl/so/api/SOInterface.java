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

package co.doppl.so.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface SOInterface {
  @GET("/2.2/questions?pagesize=15&order=desc&sort=creation&tagged=android&site=stackoverflow")
  Single<Response> recent();

  class Item {
    public final String title=null;
    public final Owner owner=null;

    @Override
    public String toString() {
      return(title);
    }
  }

  class Owner {
    @SerializedName("profile_image")
    public final String profileImage=null;
  }

  class Response {
    public final List<Item> items=null;
  }
}
