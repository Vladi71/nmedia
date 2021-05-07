// Generated by view binder compiler. Do not edit!
package ru.netology.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.netology.R;

public final class CardPostBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView authorTv;

  @NonNull
  public final ImageView avatarV;

  @NonNull
  public final TextView contentTv;

  @NonNull
  public final ImageView imageIV;

  @NonNull
  public final MaterialButton likeIb;

  @NonNull
  public final MaterialButton menuIb;

  @NonNull
  public final Barrier publishedBarrier;

  @NonNull
  public final Barrier publishedBarrier2;

  @NonNull
  public final TextView publishedTv;

  private CardPostBinding(@NonNull ConstraintLayout rootView, @NonNull TextView authorTv,
      @NonNull ImageView avatarV, @NonNull TextView contentTv, @NonNull ImageView imageIV,
      @NonNull MaterialButton likeIb, @NonNull MaterialButton menuIb,
      @NonNull Barrier publishedBarrier, @NonNull Barrier publishedBarrier2,
      @NonNull TextView publishedTv) {
    this.rootView = rootView;
    this.authorTv = authorTv;
    this.avatarV = avatarV;
    this.contentTv = contentTv;
    this.imageIV = imageIV;
    this.likeIb = likeIb;
    this.menuIb = menuIb;
    this.publishedBarrier = publishedBarrier;
    this.publishedBarrier2 = publishedBarrier2;
    this.publishedTv = publishedTv;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CardPostBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CardPostBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.card_post, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CardPostBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.authorTv;
      TextView authorTv = rootView.findViewById(id);
      if (authorTv == null) {
        break missingId;
      }

      id = R.id.avatarV;
      ImageView avatarV = rootView.findViewById(id);
      if (avatarV == null) {
        break missingId;
      }

      id = R.id.contentTv;
      TextView contentTv = rootView.findViewById(id);
      if (contentTv == null) {
        break missingId;
      }

      id = R.id.imageIV;
      ImageView imageIV = rootView.findViewById(id);
      if (imageIV == null) {
        break missingId;
      }

      id = R.id.likeIb;
      MaterialButton likeIb = rootView.findViewById(id);
      if (likeIb == null) {
        break missingId;
      }

      id = R.id.menuIb;
      MaterialButton menuIb = rootView.findViewById(id);
      if (menuIb == null) {
        break missingId;
      }

      id = R.id.publishedBarrier;
      Barrier publishedBarrier = rootView.findViewById(id);
      if (publishedBarrier == null) {
        break missingId;
      }

      id = R.id.publishedBarrier2;
      Barrier publishedBarrier2 = rootView.findViewById(id);
      if (publishedBarrier2 == null) {
        break missingId;
      }

      id = R.id.publishedTv;
      TextView publishedTv = rootView.findViewById(id);
      if (publishedTv == null) {
        break missingId;
      }

      return new CardPostBinding((ConstraintLayout) rootView, authorTv, avatarV, contentTv, imageIV,
          likeIb, menuIb, publishedBarrier, publishedBarrier2, publishedTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}