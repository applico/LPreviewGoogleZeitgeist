package applico.googlezlpreview.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Event represents an event that happened over the course of the year
 * @author Matt Powers
 */
public class Event {

    //The event title
    public String eventTitle;

    //The uri to share
    public Uri eventShareLink;

    //The card image
    public Bitmap eventImageSmall;

    //The detail image
    public Bitmap eventImageDetail;

    //Drawable ID
    public int eventImageDetailID;


}
