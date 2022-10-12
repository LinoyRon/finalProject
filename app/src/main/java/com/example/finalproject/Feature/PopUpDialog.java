package com.example.finalproject.Feature;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.finalproject.R;

public class PopUpDialog extends DialogFragment {

    String title;
    String description;
    String naturalText;
    String negativeText;
    Boolean isNegativeGrey;

    PopUpDialogListener listener;
     public interface PopUpDialogListener extends Parcelable {
        void  onNaturalBtnClick();
        void onNegativeBtnClick();

         @Override
         default int describeContents(){
             return 0;
         }

         @Override
         default void writeToParcel(Parcel dest, int flags){}

     }

    public PopUpDialog() {
    }

    public static PopUpDialog newInstance(String title, String description ,String naturalText , String negativeText, PopUpDialogListener listener ,Boolean isNegativeGrey) {
        PopUpDialog frag = new PopUpDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putString("naturalText", naturalText);
        args.putString("negativeText", negativeText);
        args.putBoolean("isNegativeGrey" ,isNegativeGrey);
        args.putParcelable("listener", listener);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_popup, container);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);
        title = getArguments().getString("title", "");
        description = getArguments().getString("description", "");
        naturalText = getArguments().getString("naturalText", "");
        negativeText = getArguments().getString("negativeText", "");
        isNegativeGrey = getArguments().getBoolean("isNegativeGrey", false);
        listener = getArguments().getParcelable("listener");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)getView().findViewById(R.id.popUpDialogTitle)).setText(title);
        ((TextView)getView().findViewById(R.id.popUpDialogDesc)).setText(description);

        Button naturalBtn = getView().findViewById(R.id.popUpNaturalBtn);
        naturalBtn.setText(naturalText);
        naturalBtn.setOnClickListener(v->{
            listener.onNaturalBtnClick();
            dismiss();
        });

        Button negativeBtn = getView().findViewById(R.id.popUpNegativeBtn);
        negativeBtn.setText(negativeText);
        negativeBtn.setOnClickListener(v->{
            listener.onNegativeBtnClick();
            dismiss();
        });

        if(isNegativeGrey){
            negativeBtn.setActivated(true);
        }

        getView().findViewById(R.id.popUpCloseBtn).setOnClickListener(v->{
            dismiss();
        });
    }
}