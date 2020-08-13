package com.todoreminder;

import android.content.Context;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewholder> {
    private OnNoteListener monNoteListener;
    View view;

     private List<Todo> todolist;




    Todo mRecentlyDeletedItem;
    int  mRecentlyDeletedItemPosition;
    public void submitList(List<Todo> itemList){
        todolist=itemList;
    }

    public CustomAdapter(List<Todo> todolist,OnNoteListener onNoteListener) {

        this.todolist = todolist;
        this.monNoteListener=onNoteListener;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasktheme, parent, false);
        return new MyViewholder(itemView,monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Todo todo = todolist.get(position);
        holder.titletext.setText(todo.getTitle());
        holder.desctext.setText(todo.getDescription());
    }



    @Override
    public int getItemCount() {

        if(todolist.size()==0) {
            //  View.inflate(view.getContext(),R.layout.emptylist,null);
            //System.out.println("empty..................");
           // view = inflater.inflate(R.layout.emptylist, null);
        }

        return todolist.size();


    }
    public void deleteItem(int position){
         mRecentlyDeletedItem = todolist.get(position);
         mRecentlyDeletedItemPosition = position;
         todolist.remove(position);
         notifyItemRemoved(position);


         showUndoSnackbar();
    }

    private void showUndoSnackbar() {

        final Snackbar snackbar=Snackbar.make(view,"Item deleted",Snackbar.LENGTH_LONG);
        snackbar.show();
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAdapter.this.undoDelete();
            }
        });


    }

    private void undoDelete() {
        todolist.add(mRecentlyDeletedItemPosition,mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }


    public class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LayoutInflater inflater;

        public TextView titletext, desctext;
        MaterialCheckBox checkBox;
        OnNoteListener onNoteListener;


        public MyViewholder(@NonNull final View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            view=itemView.getRootView();







            this.onNoteListener=onNoteListener;
            checkBox=itemView.findViewById(R.id.checkbox);
            titletext = itemView.findViewById(R.id.tv_title);
            desctext = itemView.findViewById(R.id.tv_desc);
            checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    titletext.setPaintFlags(titletext.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else{
                    titletext.setPaintFlags(titletext.getPaintFlags()&(~Paint.STRIKE_THRU_TEXT_FLAG) );
                }

            }
        });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onNoteListener.onNoteClicked(getAdapterPosition());
        }
    }

    public interface  OnNoteListener{
        void onNoteClicked(int position);
    }




}
