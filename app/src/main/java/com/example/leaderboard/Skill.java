package com.example.leaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Skill#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Skill extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private SkillRecyclerViewAdapter mSkillRecyclerViewAdapter;
    View mView;
    private List<SkillData> mSkillDataList;
    private LinearLayoutManager mLinearLayoutManager;

    public Skill() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Skill.
     */
    // TODO: Rename and change types and number of parameters
    public static Skill newInstance(String param1, String param2) {
        Skill fragment = new Skill();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mSkillDataList = new ArrayList<>();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerViewSkill);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSkill.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiSkill api = retrofit.create(ApiSkill.class);
        Call<List<SkillData>> call = api.getArticle();

        call.enqueue(new Callback<List<SkillData>>() {
            @Override
            public void onResponse(Call<List<SkillData>> call, Response<List<SkillData>> response) {
                mSkillDataList = response.body();
                mSkillRecyclerViewAdapter = new SkillRecyclerViewAdapter(getContext(),
                        mSkillDataList);
                mRecyclerView.setAdapter(mSkillRecyclerViewAdapter);

            }

            @Override
            public void onFailure(Call<List<SkillData>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return mView;

    }
}