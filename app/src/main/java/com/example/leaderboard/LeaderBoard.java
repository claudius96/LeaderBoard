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
 * Use the {@link LeaderBoard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderBoard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private LeaderBoardRecyclerViewAdapter mLeaderBoardRecyclerViewAdapter;
    View mView;
    private List<LeaderBoardData> mListLeaderBoardData;
    private LinearLayoutManager mLinearLayoutManager;

    public LeaderBoard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaderBoard.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaderBoard newInstance(String param1, String param2) {
        LeaderBoard fragment = new LeaderBoard();
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
            mListLeaderBoardData = new ArrayList<>();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_leader_board, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLeaderboard.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiLeaderboard api = retrofit.create(ApiLeaderboard.class);
        Call<List<LeaderBoardData>> call = api.getArticle();

        call.enqueue(new Callback<List<LeaderBoardData>>() {
            @Override
            public void onResponse(Call<List<LeaderBoardData>> call, Response<List<LeaderBoardData>> response) {
                mListLeaderBoardData = response.body();
                mLeaderBoardRecyclerViewAdapter = new LeaderBoardRecyclerViewAdapter(getContext(), mListLeaderBoardData);
                mRecyclerView.setAdapter(mLeaderBoardRecyclerViewAdapter);

            }

            @Override
            public void onFailure(Call<List<LeaderBoardData>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        return mView;
    }
}