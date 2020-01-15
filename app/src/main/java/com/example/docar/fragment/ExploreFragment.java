package com.example.docar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.docar.R;
import com.example.docar.adapter.GridAdapter;

/**
 * Created by Ujang Wahyu on 24/01/2017.
 */

public class ExploreFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public ExploreFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_explore, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager  = new GridLayoutManager(getActivity(),2);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new GridAdapter();
            mRecyclerView.setAdapter(mAdapter);

        return view ;
    }
}
