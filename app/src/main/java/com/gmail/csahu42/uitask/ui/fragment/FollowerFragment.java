package com.gmail.csahu42.uitask.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;

public class FollowerFragment extends Fragment {


  public FollowerFragment() {
    // Required empty public constructor
  }



  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_follower, container, false);
  }
}
