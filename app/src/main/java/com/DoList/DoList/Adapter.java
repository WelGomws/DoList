package com.DoList.DoList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.SpeciesViewHolder> {
    private Context context;
    private List<Task> taskResult = new ArrayList<>();
    private ClickListenerFeature listener;

    public TaskAdapter(Context context, List<Task> taskResult, ClickListenerFeature listener){
        this.context = context;
        this.taskResult = taskResult;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_task, parent, false);
        return new SpeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesViewHolder holder, int position) {
        holder.taskText.setText(taskResult.get(position).task);
    }

    public int getItemCount() {
        if(taskResult == null){
            return 0;
        } else {
            return taskResult.size();
        }
    }

    public class SpeciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView taskText;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);

            taskText = itemView.findViewById((R.id.textList));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    public interface ClickListenerFeature{
        void onClick(View v, int position);
    }

}
