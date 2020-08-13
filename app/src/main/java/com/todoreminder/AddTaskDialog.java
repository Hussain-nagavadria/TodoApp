package com.todoreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddTaskDialog extends AppCompatDialogFragment {
    private EditText Title,Description;
    private DialogListner listner;
    CustomAdapter customAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        try {
            listner=(DialogListner) context;
        }
        catch (ClassCastException ex){
            throw  new ClassCastException(context.toString()+"must implement ExamplDialogListner");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_dialougr,null);
        Title= view.findViewById(R.id.et_title);
        Description=view.findViewById(R.id.et_description);

        builder.setView(view)
                .setTitle("New Task")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         String data_title=Title.getText().toString();
                         String data_description =Description.getText().toString();
                         data_description=data_description.toUpperCase();
                       
                        if(TextUtils.isEmpty(Title.getText().toString())){
                            Toast.makeText( getContext(),"Enter Title ",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(TextUtils.isEmpty(Description.getText().toString())){
                                Toast.makeText(getContext(),"Enter Description ",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                listner.addListItem(data_title,data_description);

                            }
                        }
                    }
                });



    return builder.create();

    }

    public interface  DialogListner{

        void addListItem(String data_title,String data_Description);

    }
}
