package applico.googlezlpreview.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
 * There are two methods that I have incorporated as a logical
 * branch to show off different animations. One is using the standard moveImage tag
 * The other is to show off combining the animation classes with the make scene transition
 * animation class.  The animations are not clean right now, it would take a little refactoring, but the
 * intention of these was to show off the new shared element animation and the combination of a shared
 * element
 * @author Matt Powers
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements View
        .OnClickListener {

    private static String LOG_TAG = EventAdapter.class.getSimpleName();
    private List<Event> mEventDataset;
    private static final int SLIDE_DURATION = 300;
    private View mFabView;

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(List<Event> myDataset, View fabView)
    {
        mEventDataset = myDataset;
        mFabView = fabView;
        mFabView.setTransitionName(GlobalDetailsActivity.SHARED_FAB_VIEW);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_global_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        vh.mBaseImageIV.setTransitionName(GlobalDetailsActivity.SHARED_IMAGE);
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
                slideAndSharedAnimation(holder, v.getContext());
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


    //TODO this logic needs to be moved out of the adapter and into the fragment.
    private void standardSharedAnimation(ViewHolder holder, Context ctx)
    {
        final Event event = mEventDataset.get(Integer.parseInt(holder.mTitleRankTV.getText().toString()) - 1);

        final Intent intent = new Intent(ctx, GlobalDetailsActivity.class);
        intent.putExtra(GlobalDetailsActivity.TITLE_KEY, event.eventTitle);
        intent.putExtra(GlobalDetailsActivity.RANK_KEY, holder.mTitleRankTV.getText());
        intent.putExtra(GlobalDetailsActivity.RESOURCE_KEY, event.eventImageDetailID);

        final Pair shared = Pair.create(holder.mBaseImageIV, GlobalDetailsActivity.SHARED_IMAGE);
        //TODO: add another pair for the FAB view, below variable should be used but is not at this time.
        //Pair sharedFab = Pair.create(mFabView, GlobalDetailsActivity.SHARED_FAB_VIEW);
        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)ctx, shared);
        final Bundle bundle = options.toBundle();
        ctx.startActivity(intent, bundle);
    }

    //TODO this logic needs to be moved out of the adapter and into the fragment.
    private void slideAndSharedAnimation(ViewHolder holder, final Context ctx)
    {

        final CardView cv = (CardView)holder.mBaseImageIV.getParent().getParent();

        final Event event = mEventDataset.get(Integer.parseInt(holder.mTitleRankTV.getText().toString()) - 1);

        final Intent intent = new Intent(ctx, GlobalDetailsActivity.class);
        intent.putExtra(GlobalDetailsActivity.TITLE_KEY, event.eventTitle);
        intent.putExtra(GlobalDetailsActivity.RANK_KEY, holder.mTitleRankTV.getText());
        intent.putExtra(GlobalDetailsActivity.RESOURCE_KEY, event.eventImageDetailID);

        final Pair sharedFirst = Pair.create(holder.mBaseImageIV,GlobalDetailsActivity.SHARED_IMAGE);
        final Pair sharedSecond = Pair.create(mFabView, GlobalDetailsActivity.SHARED_FAB_VIEW);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)ctx,sharedFirst,sharedSecond);

        final Bundle bundle = options.toBundle();
        //TODO need to figure out how to unwind this animation, as the callback in the onBackPressed does not cover this due to it not being a part of the activity transition.
        Animation anim = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)cv.getLayoutParams();
                final int currentLeftMargin = params.leftMargin;
                final int currentRightMargin = params.rightMargin;

                params.leftMargin = currentLeftMargin - (int)(currentLeftMargin *
                        interpolatedTime);
                params.rightMargin = currentRightMargin - (int)(currentRightMargin *
                        interpolatedTime);
                cv.setLayoutParams(params);
            }

        };
        anim.setDuration(SLIDE_DURATION);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ctx.startActivity(intent, bundle);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        cv.startAnimation(anim);
    }

}
