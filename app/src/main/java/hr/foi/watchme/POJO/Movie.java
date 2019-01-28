package hr.foi.watchme.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    //TODO dodati sve atribute, ne samo url
    public Movie(Integer id, String name, String releaseDate, Integer season,
                 Integer episode, Double duration, Integer feedback, String coverPhoto ){
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.season = season;
        this.episode = episode;
        this.duration = duration;
        this.feedback = feedback;
        this.coverPhoto = coverPhoto;
    }

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ReleaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("Season")
    @Expose
    private Integer season;
    @SerializedName("Episode")
    @Expose
    private Integer episode;
    @SerializedName("Duration")
    @Expose
    private Double duration;
    @SerializedName("FeedBack")
    @Expose
    private Integer feedback;
    @SerializedName("CoverPhoto")
    @Expose
    private String coverPhoto;
    @SerializedName("Category")
    @Expose
    private String category;

    protected Movie(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        releaseDate = in.readString();
        if (in.readByte() == 0) {
            season = null;
        } else {
            season = in.readInt();
        }
        if (in.readByte() == 0) {
            episode = null;
        } else {
            episode = in.readInt();
        }
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readDouble();
        }
        if (in.readByte() == 0) {
            feedback = null;
        } else {
            feedback = in.readInt();
        }
        coverPhoto = in.readString();
        category = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Integer getID() {
        return id;
    }

    public void setID(Integer iD) {
        this.id = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getFeedback() {
        return feedback;
    }

    public void setFeedback(Integer feedback) {
        this.feedback = feedback;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //TODO dodati sve ostale atribute
        dest.writeInt(id);
        dest.writeString(coverPhoto);
    }
}
