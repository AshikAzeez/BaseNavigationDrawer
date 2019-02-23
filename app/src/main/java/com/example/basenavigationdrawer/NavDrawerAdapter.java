package com.example.basenavigationdrawer;import android.content.Context;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.LinearLayout;import android.widget.TextView;import java.util.List;import androidx.annotation.NonNull;import androidx.core.content.ContextCompat;import androidx.recyclerview.widget.RecyclerView;/** * User: Ashik * Date: 18-02-2019 * Time: 23:12 */public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder> {    private DrawerItemClickListner listner;    private List<String> name;    private int selectedPos = 0;//set first item selected in recycler view    private Context context;    public NavDrawerAdapter(Context context, List<String> name, DrawerItemClickListner listner) {        this.name = name;        this.listner = listner;        this.context = context;    }    @NonNull    @Override    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_drawer, parent, false);        return new MyViewHolder(view, listner);    }    @Override    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {        String item = name.get(position);        if (context instanceof ActivityOne && position == 0) {            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));        } else if (context instanceof ActivityTwo && position == 1) {            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));        } else if (context instanceof ActivityThree && position == 2) {            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));        } else if (context instanceof ActivityFour && position == 3) {            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));        } else {            holder.container.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));        }        holder.textView.setText(item);    }    @Override    public int getItemCount() {        return name.size();    }    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {        private DrawerItemClickListner listner;        private TextView textView;        private LinearLayout container;        MyViewHolder(@NonNull View itemView, DrawerItemClickListner listner) {            super(itemView);            this.listner = listner;            itemView.setOnClickListener(this);            textView = itemView.findViewById(R.id.txt_nav_head);            container = itemView.findViewById(R.id.container);        }        @Override        public void onClick(View v) {            try {                selectedPos = getAdapterPosition();                listner.onClick(getAdapterPosition());                notifyDataSetChanged();            } catch (ArrayIndexOutOfBoundsException e) {                e.printStackTrace();            }        }    }    public interface DrawerItemClickListner {        void onClick(int position);    }}