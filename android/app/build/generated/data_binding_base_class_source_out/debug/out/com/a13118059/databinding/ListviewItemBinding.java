// Generated by view binder compiler. Do not edit!
package com.a13118059.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.a13118059.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListviewItemBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final CheckBox checkBox;

  @NonNull
  public final TextView contactName;

  @NonNull
  public final TextView contactNameTxt;

  @NonNull
  public final TextView contactPhone;

  @NonNull
  public final TextView contactPhoneTxt;

  @NonNull
  public final RelativeLayout firstLayout;

  @NonNull
  public final RelativeLayout secondLayout;

  @NonNull
  public final RelativeLayout thirdLayout;

  private ListviewItemBinding(@NonNull RelativeLayout rootView, @NonNull CheckBox checkBox,
      @NonNull TextView contactName, @NonNull TextView contactNameTxt,
      @NonNull TextView contactPhone, @NonNull TextView contactPhoneTxt,
      @NonNull RelativeLayout firstLayout, @NonNull RelativeLayout secondLayout,
      @NonNull RelativeLayout thirdLayout) {
    this.rootView = rootView;
    this.checkBox = checkBox;
    this.contactName = contactName;
    this.contactNameTxt = contactNameTxt;
    this.contactPhone = contactPhone;
    this.contactPhoneTxt = contactPhoneTxt;
    this.firstLayout = firstLayout;
    this.secondLayout = secondLayout;
    this.thirdLayout = thirdLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ListviewItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListviewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.listview_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListviewItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.checkBox;
      CheckBox checkBox = ViewBindings.findChildViewById(rootView, id);
      if (checkBox == null) {
        break missingId;
      }

      id = R.id.contact_name;
      TextView contactName = ViewBindings.findChildViewById(rootView, id);
      if (contactName == null) {
        break missingId;
      }

      id = R.id.contact_name_txt;
      TextView contactNameTxt = ViewBindings.findChildViewById(rootView, id);
      if (contactNameTxt == null) {
        break missingId;
      }

      id = R.id.contact_phone;
      TextView contactPhone = ViewBindings.findChildViewById(rootView, id);
      if (contactPhone == null) {
        break missingId;
      }

      id = R.id.contact_phone_txt;
      TextView contactPhoneTxt = ViewBindings.findChildViewById(rootView, id);
      if (contactPhoneTxt == null) {
        break missingId;
      }

      id = R.id.first_layout;
      RelativeLayout firstLayout = ViewBindings.findChildViewById(rootView, id);
      if (firstLayout == null) {
        break missingId;
      }

      id = R.id.second_layout;
      RelativeLayout secondLayout = ViewBindings.findChildViewById(rootView, id);
      if (secondLayout == null) {
        break missingId;
      }

      id = R.id.third_layout;
      RelativeLayout thirdLayout = ViewBindings.findChildViewById(rootView, id);
      if (thirdLayout == null) {
        break missingId;
      }

      return new ListviewItemBinding((RelativeLayout) rootView, checkBox, contactName,
          contactNameTxt, contactPhone, contactPhoneTxt, firstLayout, secondLayout, thirdLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
