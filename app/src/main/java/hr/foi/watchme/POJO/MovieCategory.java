package hr.foi.watchme.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//TODO refaktorirati ovu klasu tako da se stavi u pravi paket
public class MovieCategory implements Parcelable {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("MovieList")
    @Expose
    private ArrayList<Movie> movies;

    public MovieCategory(String catName, ArrayList<Movie> movies){
        this.name = catName;
        this.movies = movies;
    }


    protected MovieCategory(Parcel in) {
        name = in.readString();
        movies = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<MovieCategory> CREATOR = new Creator<MovieCategory>() {
        @Override
        public MovieCategory createFromParcel(Parcel in) {
            return new MovieCategory(in);
        }

        @Override
        public MovieCategory[] newArray(int size) {
            return new MovieCategory[size];
        }
    };

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(movies);
    }
}
