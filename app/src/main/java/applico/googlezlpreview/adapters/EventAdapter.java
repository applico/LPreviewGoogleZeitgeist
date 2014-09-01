package applico.googlezlpreview.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import applico.googlezlpreview.R;
import applico.googlezlpreview.activities.GlobalDetailsActivity;
import applico.googlezlpreview.models.Event;

/**
 * Created by Matthew on 8/21/2014.
 * There are two methods that I have incorporated as a logical
 * branch to show off different animations. One is using the standard moveImage tag
 * The other is to show off combining the animation classes with the make scene transition
 * animation class
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements View
        .OnClickListener {

    private static String LOG_TAG = EventAdapter.class.getSimpleName();
    //ArrayList of the events
    private List<Event> mEventDataset;

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
        holder.mTitleRankTV.setText(position);
        holder.mTitleTV.setText(ev.eventTitle);
        holder.mBaseImageIV.setImageBitmap(ev.eventImageSmall);
        //Set the tag for the onClick event
        holder.mLearnMoreTV.setTag(holder);
        holder.mLearnMoreTV.setOnClickListener(this);

        //TODO - holding onto the view holder twice, need to clean that up.
        holder.mCardView.setTag(holder);
        holder.mCardView.setOnClickListener(this);

    }


    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mEventDataset.size();
    }


    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder)v.getTag();
        switch(v.getId())
        {
            case R.id.learn_more:
                slideandSharedAnimation(holder, v.getContext());
                break;
            case R.id.region_card_view:
                standardSharedAnimation(holder, v.getContext());
                break;

        }

    }

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleTV;
        private TextView mTitleRankTV;
        private TextView mShareTV;
        private TextView mLearnMoreTV;
        private ImageView mBaseImageIV;
        private CardView mCardView;

        public ViewHolder(View v) {
            super(v);
            mTitleRankTV = (TextView)v.findViewById(R.id.base_caption_num);
            mTitleTV = (TextView)v.findViewById(R.id.base_caption);
            mShareTV = (TextView)v.findViewById(R.id.share_link);
            mLearnMoreTV = (TextView) v.findViewById(R.id.learn_more);
            mBaseImageIV = (ImageView)v.findViewById(R.id.base_image);
            mCardView = (CardView)v.findViewById(R.id.region_card_view);
        }
    }


    private void standardSharedAnimation(ViewHolder holder, Context ctx)
    {
        TextView aVTitle = holder.mTitleTV;
        aVTitle.setViewName(GlobalDetailsActivity.SHARED_VIEW_TITLE);

        TextView aVRank = holder.mTitleRankTV;
        aVRank.setViewName(GlobalDetailsActivity.SHARED_VIEW_RANK);

        ImageView aVImage = holder.mBaseImageIV;
        aVImage.setViewName(GlobalDetailsActivity.SHARED_IMAGE);

        final CardView cv = (CardView)aVImage.getParent().getParent();

        Event event = mEventDataset.get(Integer.parseInt(aVRank.getText().toString()) - 1);

        final Intent intent = new Intent(ctx, GlobalDetailsActivity.class);
        intent.putExtra(GlobalDetailsActivity.TITLE_KEY, event.eventTitle);
        intent.putExtra(GlobalDetailsActivity.SUMMARY_KEY, event.eventSummary);
        intent.putExtra(GlobalDetailsActivity.RANK_KEY, aVRank.getText());
        intent.putExtra(GlobalDetailsActivity.RESOURCE_KEY, event.eventImageDetailID);
        Activity act = (Activity)ctx;

        final Pair sharedFirst = Pair.create(aVImage,GlobalDetailsActivity.SHARED_IMAGE);
        final Pair sharedThird = Pair.create(aVTitle,GlobalDetailsActivity.SHARED_VIEW_TITLE);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)ctx, sharedFirst);
        final Bundle bundle = options.toBundle();
        ctx.startActivity(intent, bundle);
    }

    private void slideandSharedAnimation(ViewHolder holder, final Context ctx)
    {
        TextView aVTitle = holder.mTitleTV;
        aVTitle.setViewName(GlobalDetailsActivity.SHARED_VIEW_TITLE);

        TextView aVRank = holder.mTitleRankTV;
        aVRank.setViewName(GlobalDetailsActivity.SHARED_VIEW_RANK);

        ImageView aVImage = holder.mBaseImageIV;
        aVImage.setViewName(GlobalDetailsActivity.SHARED_IMAGE);


        final CardView cv = (CardView)aVImage.getParent().getParent();

        Event event = mEventDataset.get(Integer.parseInt(aVRank.getText().toString()) - 1);

        final Intent intent = new Intent(ctx, GlobalDetailsActivity.class);
        intent.putExtra(GlobalDetailsActivity.TITLE_KEY, event.eventTitle);
        intent.putExtra(GlobalDetailsActivity.SUMMARY_KEY, event.eventSummary);
        intent.putExtra(GlobalDetailsActivity.RANK_KEY, aVRank.getText());
        intent.putExtra(GlobalDetailsActivity.RESOURCE_KEY, event.eventImageDetailID);
        Activity act = (Activity)ctx;

        final Pair sharedFirst = Pair.create(aVImage,GlobalDetailsActivity.SHARED_IMAGE);
        final Pair sharedThird = Pair.create(aVTitle,GlobalDetailsActivity.SHARED_VIEW_TITLE);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)ctx, sharedFirst);


        final Bundle bundle = options.toBundle();
        final int newMargin = 0;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)cv.getLayoutParams();
        final int originalLeftMargin = params.leftMargin;
        final int originalRightMargin = params.rightMargin;
        Animation anim = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.e(LOG_TAG, "Interpolated Time: " + interpolatedTime);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)cv.getLayoutParams();
                int currentLeftMargin = params.leftMargin;
                int currentRightMargin = params.rightMargin;

                params.leftMargin = currentLeftMargin - (int)((currentLeftMargin - newMargin) *
                        interpolatedTime);
                params.rightMargin = currentRightMargin - (int)((currentRightMargin - newMargin) *
                        interpolatedTime);
                cv.setLayoutParams(params);

            }

        };
        anim.setDuration(300);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e(LOG_TAG, "Animation End");
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)cv.getLayoutParams();


                ctx.startActivity(intent, bundle);
                        /*
                        cv.clearAnimation();
                        params.leftMargin = originalLeftMargin;
                        params.rightMargin = originalRightMargin;
                        cv.setLayoutParams(params);*/
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        cv.startAnimation(anim);
    }

}
