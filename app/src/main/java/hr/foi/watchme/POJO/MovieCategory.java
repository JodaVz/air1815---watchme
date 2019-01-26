package hr.foi.watchme.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.foi.watchme.POJO.Movie;

//TODO refaktorirati ovu klasu tako da se stavi u pravi paket
public class MovieCategory implements Parcelable {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("MovieList")
    @Expose
    private List<Movie> movies;

    public MovieCategory(String catName, List<Movie> movies){
        this.name = catName;
        this.movies = movies;
    }



    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
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

    }
}
