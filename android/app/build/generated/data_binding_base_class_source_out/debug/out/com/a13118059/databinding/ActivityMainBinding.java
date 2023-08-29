// Generated by view binder compiler. Do not edit!
package com.a13118059.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.a13118059.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button addButton;

  @NonNull
  public final LinearLayout buttonsLayout;

  @NonNull
  public final EditText editName;

  @NonNull
  public final EditText editPhone;

  @NonNull
  public final LinearLayout namePhoneLayout;

  @NonNull
  public final Button viewButton;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull Button addButton,
      @NonNull LinearLayout buttonsLayout, @NonNull EditText editName, @NonNull EditText editPhone,
      @NonNull LinearLayout namePhoneLayout, @NonNull Button viewButton) {
    this.rootView = rootView;
    this.addButton = addButton;
    this.buttonsLayout = buttonsLayout;
    this.editName = editName;
    this.editPhone = editPhone;
    this.namePhoneLayout = namePhoneLayout;
    this.viewButton = viewButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_button;
      Button addButton = ViewBindings.findChildViewById(rootView, id);
      if (addButton == null) {
        break missingId;
      }

      id = R.id.buttons_layout;
      LinearLayout buttonsLayout = ViewBindings.findChildViewById(rootView, id);
      if (buttonsLayout == null) {
        break missingId;
      }

      id = R.id.editName;
      EditText editName = ViewBindings.findChildViewById(rootView, id);
      if (editName == null) {
        break missingId;
      }

      id = R.id.editPhone;
      EditText editPhone = ViewBindings.findChildViewById(rootView, id);
      if (editPhone == null) {
        break missingId;
      }

      id = R.id.name_phone_layout;
      LinearLayout namePhoneLayout = ViewBindings.findChildViewById(rootView, id);
      if (namePhoneLayout == null) {
        break missingId;
      }

      id = R.id.view_button;
      Button viewButton = ViewBindings.findChildViewById(rootView, id);
      if (viewButton == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, addButton, buttonsLayout, editName,
          editPhone, namePhoneLayout, viewButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
