package applico.googlezlpreview.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import applico.googlezlpreview.R;
import applico.googlezlpreview.activities.GlobalDetailsActivity;
import applico.googlezlpreview.activities.HomeActivity;
import applico.googlezlpreview.models.Event;

/**
 * Created by Matthew on 8/21/2014.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements View.OnClickListener {

    private static String LOG_TAG = EventAdapter.class.getSimpleName();
    //ArrayList of the events
    private List<Event> mEventDataset;


    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleTV;
        private TextView mTitleRank;
        private TextView mShareTV;
        private TextView mLearnMoreTV;

        private ImageView mBaseImage;

        public ViewHolder(View v) {
            super(v);
            mTitleRank = (TextView)v.findViewById(R.id.base_caption_num);
            mTitleTV = (TextView)v.findViewById(R.id.base_caption);
            mShareTV = (TextView)v.findViewById(R.id.share_link);
            mLearnMoreTV = (TextView) v.findViewById(R.id.learn_more);
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
        holder.mTitleRank.setText(position);
        holder.mTitleTV.setText(ev.eventTitle);
        holder.mBaseImage.setImageBitmap(ev.eventImageSmall);
        //Set the tag for the onClick event
        holder.mLearnMoreTV.setTag(holder);
        holder.mLearnMoreTV.setOnClickListener(this);

    }




    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mEventDataset.size();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.learn_more:
                ViewHolder holder = (ViewHolder)v.getTag();
                TextView aVTitle = holder.mTitleTV;
                aVTitle.setViewName(GlobalDetailsActivity.SHARED_VIEW_TITLE);

                TextView aVRank = holder.mTitleRank;
                aVRank.setViewName(GlobalDetailsActivity.SHARED_VIEW_RANK);

                ImageView aVImage = holder.mBaseImage;
                aVImage.setViewName(GlobalDetailsActivity.SHARED_IMAGE);

                Event event = mEventDataset.get(Integer.parseInt(aVRank.getText().toString()) - 1);
                Context ctx = v.getContext();
                Intent intent = new Intent(ctx, GlobalDetailsActivity.class);
                intent.putExtra(GlobalDetailsActivity.TITLE_KEY, event.eventTitle);
                intent.putExtra(GlobalDetailsActivity.SUMMARY_KEY, event.eventSummary);
                intent.putExtra(GlobalDetailsActivity.RANK_KEY, aVRank.getText());
                Activity act = (Activity)ctx;

                Pair sharedFirst = Pair.create(aVImage,GlobalDetailsActivity.SHARED_IMAGE);
                Pair sharedSecond = Pair.create(aVRank,GlobalDetailsActivity.SHARED_VIEW_RANK);
                Pair sharedThird = Pair.create(aVTitle,GlobalDetailsActivity.SHARED_VIEW_TITLE);


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)ctx,sharedFirst,sharedSecond,sharedThird);

                        //new Pair<View, String>
                        //        (aVTitle,GlobalDetailsActivity.SHARED_VIEW_TITLE));
                Bundle bundle = options.toBundle();
                ctx.startActivity(intent, bundle);
                break;
        }
    }
}
