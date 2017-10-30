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

import android.arch.lifecycle.ViewModel;
import org.reactivestreams.Publisher;
import java.util.List;
import io.reactivex.schedulers.Schedulers;

public class QuestionsViewModel extends ViewModel {
  private final Publisher<List<Question>> current;

  public QuestionsViewModel() {
    current=Repository.get()
      .current()
      .subscribeOn(Schedulers.io())
      .toFlowable()
      .cache()
      .share();
  }

  public Publisher<List<Question>> current() {
    return current;
  }
}
