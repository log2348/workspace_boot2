package dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MovieList {
	String status;
	String status_message;
	Data data;

	public class Data {
		int movie_count;
		int limit;
		int page_number;

		List<Movie> movies = new ArrayList<>();

		String summary;
		String description_full;
		String synopsis;
		String yt_trailer_code;
		String language;
		String mpa_rating;

		String background_image;
		String background_image_original;
		String small_cover_image;
		String medium_cover_image;
		String large_cover_image;
		String state;

		class Movie {
			Movie movie1 = new Movie();
			Movie movie2 = new Movie();
			
			int id;
			String url;
			String imdb_code;
			String title;
			String title_english;
			String title_long;
			String slug;
			int year;
			double rating;
			int runtime;

			ArrayList<String> genres = new ArrayList<String>();

		}
	}
}
