public interface MovieService {
    public Movie createNewMovie(Movie movie);
    public Movie getMovie(int movieId)   throws MovieNotFoundException;
    public boolean deleteMovie(int movieId)  throws MovieNotFoundException ;
    public List<Movie> getAllMovies();
}
