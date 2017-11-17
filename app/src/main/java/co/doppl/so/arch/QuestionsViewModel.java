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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModel for the questions that we get back from the Stack Exchange API.
 */
public class QuestionsViewModel extends ViewModel {
  public interface Host {
    void setQuestions(List<Question> questions);
  }

  /**
   * The most recently retrieved list of questions. We use Publisher here
   * so that we can use LiveDataReactiveStreams in the UI layer, which requires
   * a Publisher.
   */
  private final Publisher<List<Question>> current;

  /**
   * The outstanding subscription for model updates, if any
   */
  private Disposable sub;

  /**
   * Constructor. 'Nuff said.
   */
  public QuestionsViewModel() {
    current=Repository.get()
      .current()
      .subscribeOn(Schedulers.io())
      .toFlowable()
      .cache()
      .share();
  }

  /**
   * Getter for current field.
   *
   * @return the most recently retrieved list of questions
   */
  public Publisher<List<Question>> current() {
    return current;
  }

  /**
   * Register for model updates
   *
   * @param host the consumer of the model updates
   */
  public void register(Host host) {
    sub=Repository.get()
      .current()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(host::setQuestions);
  }

  /**
   * Unregister from model updates
   */
  public void unregister() {
    if (sub!=null && !sub.isDisposed()) {
      sub.dispose();
    }
  }
}
