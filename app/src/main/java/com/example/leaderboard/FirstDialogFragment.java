package com.example.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class FirstDialogFragment extends AppCompatDialogFragment {
    public Button yesButton;
    public ImageButton cancelButton;
    public String first,last, email_acc, github;

    public static FirstDialogFragment newInstance(){
        FirstDialogFragment firstDialogFragment = new FirstDialogFragment();
        return firstDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_are_u_sure, container,false);
        yesButton = (Button) view.findViewById(R.id.yesButton);
        cancelButton = (ImageButton) view.findViewById(R.id.cancelButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Submission)getActivity()).yes();
                getDialog().dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Submission)getActivity()).cancel();
                getDialog().dismiss();
            }
        });
        return view;

    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setMessage("Are you sure ?");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                ((Submission)getActivity()).yes();
//            }
//        });
//        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                ((Submission)getActivity()).cancel();
//
//            }
//        });
//
//        return builder.create();



//    }

    public interface FirstDialogListener{
        void submit(String email_acc, String first, String last, String github);

    }


}

