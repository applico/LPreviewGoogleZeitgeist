package applico.googlezlpreview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import applico.googlezlpreview.R;
import applico.googlezlpreview.models.Event;

/**
 * Created by Matthew on 8/21/2014.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements View.OnClickListener {

    //ArrayList of the events
    private List<Event> mEventDataset;

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mTitlePos;
        private TextView mShareTV;

        private ImageView mBaseImage;

        public ViewHolder(View v) {
            super(v);

            mTitlePos = (TextView)v.findViewById(R.id.base_caption_num);
            mTitleTV = (TextView)v.findViewById(R.id.base_caption);
            mShareTV = (TextView)v.findViewById(R.id.share_link);
            mBaseImage = (ImageView)v.findViewById(R.id.base_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(List<Event> myDataset)
    {
        mEventDataset = myDataset;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_global_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Event ev = mEventDataset.get(pos);
        String position = String.valueOf(pos + 1);
        holder.mTitlePos.setText(position);
        holder.mTitleTV.setText(ev.eventTitle);
        holder.mBaseImage.setImageDrawable(ev.eventImageSmall);
        //TODO - set this properly
        //holder.mShareTV.setTag("uri", ev);
    }




    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mEventDataset.size();
    }


    @Override
    public void onClick(View view) {

    }
}
