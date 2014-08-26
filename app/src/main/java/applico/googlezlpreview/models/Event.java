package applico.googlezlpreview.models;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 8/21/2014.
 * Event represents an event that happened over the course of the year
 */
public class Event implements Parcelable {

    //The event title
    public String eventTitle;

    //The event summary
    public String eventSummary;

    //The uri to share
    public Uri eventShareLink;

    //The card image
    public Drawable eventImageSmall;

    //The detail image
    public Drawable eventImageDetail;

    public Event ()
    {}


    public Event(Parcel in) {
        eventTitle = in.readString();
        eventSummary = in.readString();
        eventShareLink = (Uri) in.readValue(Uri.class.getClassLoader());
        eventImageSmall = (Drawable) in.readValue(Drawable.class.getClassLoader());
        eventImageDetail = (Drawable) in.readValue(Drawable.class.getClassLoader());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(eventTitle);
        parcel.writeString(eventSummary);
        parcel.writeValue(eventShareLink);
        //parcel.writeParcelable(eventImageSmall);
        //parcel.writeParcelable(eventImageDetail);
    }
}
