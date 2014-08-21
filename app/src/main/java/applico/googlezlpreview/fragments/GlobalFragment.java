package applico.googlezlpreview.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import applico.googlezlpreview.R;
import applico.googlezlpreview.activities.GlobalDetailsActivity;
import applico.googlezlpreview.adapters.EventAdapter;
import applico.googlezlpreview.models.Event;


public class GlobalFragment extends Fragment{

    private static String LOG_TAG = GlobalFragment.class.getSimpleName();
    private View mRootView;
    private RecyclerView mRecView;
    private RecyclerView.Adapter mAdapter;
    private TextView mLearnMoreTV;

    private List<Event> mEvents;



    /**
     * How much fucking scaffolding do they give you nowadays
     *
     */



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGlobalFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GlobalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GlobalFragment newInstance(String param1, String param2) {
        GlobalFragment fragment = new GlobalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public GlobalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvents = initializeDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_globals, container, false);
        mRecView = (RecyclerView) mRootView.findViewById(R.id.event_recycle_view);

        //Fixed size improve performance, this is a demo application so I am going to set it
        mRecView.setHasFixedSize(true);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // specify an adapter (see also next example)
        mAdapter = new EventAdapter(mEvents);
        mRecView.setAdapter(mAdapter);
        return mRootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnGlobalFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnGlobalFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



    /**
     * This is the initialization of dataset
     */
    private List<Event> initializeDataset()
    {
        List<Event> annualEvents = new ArrayList<Event>();

        Event ev1 = new Event();
        ev1.eventShareLink = Uri.parse("lorem ipsum");
        ev1.eventSummary = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum ";
        ev1.eventTitle = "Gangnam Style";
        ev1.eventImageSmall = getResources().getDrawable(R.drawable.img_gangnam_small);
        ev1.eventImageDetail = getResources().getDrawable(R.drawable.img_gangnam);

        Event ev2 = new Event();
        ev2.eventShareLink = Uri.parse("lorem ipsum");
        ev2.eventSummary = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum ";
        ev2.eventTitle = "Hurricane Sandy";
        ev2.eventImageSmall = getResources().getDrawable(R.drawable.img_sandy);
        ev2.eventImageDetail = getResources().getDrawable(R.drawable.img_sandy);


        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);
        annualEvents.add(ev1);
        annualEvents.add(ev2);

       return annualEvents;
    }


}
