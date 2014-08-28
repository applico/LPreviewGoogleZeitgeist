package applico.googlezlpreview.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 8/21/2014.
 * Event represents an event that happened over the course of the year
 */
public class Event {

    //The event title
    public String eventTitle;

    //The event summary
    public String eventSummary;

    //The uri to share
    public Uri eventShareLink;

    //The card image
    public Bitmap eventImageSmall;

    //The detail image
    public Bitmap eventImageDetail;

    //Drawable ID
    public int eventImageDetailID;


}
