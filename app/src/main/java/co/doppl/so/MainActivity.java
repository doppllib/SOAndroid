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

package co.doppl.so;

import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import co.doppl.so.arch.Question;
import co.doppl.so.arch.QuestionsViewModel;
import co.doppl.so.databinding.RowBinding;

public class MainActivity extends FragmentActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView rv=findViewById(R.id.questions);

    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.addItemDecoration(new DividerItemDecoration(this,
      LinearLayoutManager.VERTICAL));

    final QuestionsAdapter adapter=new QuestionsAdapter();

    rv.setAdapter(adapter);

    QuestionsViewModel vm=
      ViewModelProviders.of(this).get(QuestionsViewModel.class);

    LiveDataReactiveStreams.fromPublisher(vm.current())
      .observe(this, adapter::setQuestions);
  }

  private class QuestionsAdapter extends RecyclerView.Adapter<RowHolder> {
    private List<Question> questions;

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent,
                                        int viewType) {
      return new RowHolder(RowBinding.inflate(getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(RowHolder holder, int position) {
      holder.bind(questions.get(position));
    }

    @Override
    public int getItemCount() {
      return questions==null? 0 : questions.size();
    }

    void setQuestions(List<Question> questions) {
      this.questions=questions;
      notifyDataSetChanged();
    }
  }

  private static class RowHolder extends RecyclerView.ViewHolder {
    private final RowBinding binding;

    RowHolder(RowBinding binding) {
      super(binding.getRoot());

      this.binding=binding;
    }

    void bind(Question question) {
      binding.setQuestion(question);
      binding.executePendingBindings();
    }
  }

  @BindingAdapter({"app:imageUrl", "app:placeholder", "app:error"})
  public static void bindImageView(ImageView iv,
                                   String url,
                                   Drawable placeholder,
                                   Drawable error) {
    Picasso.with(iv.getContext())
      .load(url)
      .fit()
      .centerCrop()
      .placeholder(placeholder)
      .error(error)
      .into(iv);
  }
}
