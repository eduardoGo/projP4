package com.itodo.app.itodo.GUI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.Project;
import com.itodo.app.itodo.Domain.Task;
import com.itodo.app.itodo.R;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private List<Task> list;
    private Context context;
    private String idProject;

    TextView item, idItem,removeBt;

    public TaskAdapter(Context context, List<Task> myList) {
        super(context, 0, myList);
        this.list = myList;
        this.context = context;
    }

    public TaskAdapter(Context context, List<Task> myList, String idProject) {
        super(context, 0, myList);
        this.list = myList;
        this.context = context;
        this.idProject = idProject;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_layout,parent,false
            );
        }

        final Task currentTask = getItem(position);

        idItem = (TextView)listItemView.findViewById(R.id.id_item);
        item = (TextView)listItemView.findViewById(R.id.item);
        removeBt = (TextView)listItemView.findViewById(R.id.delete_item);

        idItem.setText(idProject);
        item.setText(currentTask.getName());

        removeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();

                Connect.getNodeProject().child(idProject+"/tasks/"+position).setValue(null);
            }
        });

        return listItemView;
    }
}
