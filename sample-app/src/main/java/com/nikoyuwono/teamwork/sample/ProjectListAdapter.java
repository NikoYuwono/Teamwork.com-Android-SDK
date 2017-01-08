package com.nikoyuwono.teamwork.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.sample.widget.StarButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niko-yuwono on 17/01/08.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    private List<Project> projects;

    public ProjectListAdapter(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Project project = projects.get(position);
        holder.projectName.setText(project.getName());
        holder.projectDescription.setText(project.getDescription());
        holder.projectStarButton.setChecked(project.isStarred());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.project_name)
        TextView projectName;
        @BindView(R.id.project_description)
        TextView projectDescription;
        @BindView(R.id.project_star_button)
        StarButton projectStarButton;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
