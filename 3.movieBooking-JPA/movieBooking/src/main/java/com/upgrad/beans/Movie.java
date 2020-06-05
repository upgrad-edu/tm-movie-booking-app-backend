package com.upgrad.beans;

import java.util.Date;
import java.util.Objects;

public class Movie {
    int movieId;
    String movieName;
    String movieDesc;
    Date releaseDate;
    int duration;
    String coverPhotoURL;
    String trailerURL;
    int statusId;
    int languageId;

    public Movie(int movieId, String movieName, String movieDesc, Date releaseDate, int duration, String coverPhotoURL, String trailerURL, int statusId , int languageId) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
        this.statusId = statusId;
        this.languageId = languageId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId &&
                duration == movie.duration &&
                statusId == movie.statusId &&
                Objects.equals(movieName, movie.movieName) &&
                Objects.equals(movieDesc, movie.movieDesc) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(coverPhotoURL, movie.coverPhotoURL) &&
                Objects.equals(trailerURL, movie.trailerURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieDesc, releaseDate, duration, coverPhotoURL, trailerURL, statusId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieDesc='" + movieDesc + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", coverPhotoURL='" + coverPhotoURL + '\'' +
                ", trailerURL='" + trailerURL + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
