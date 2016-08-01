package com.capgemini.service;

import java.util.Set;

import com.capgemini.beans.Film;
import com.capgemini.inteface.FilmService;
import com.capgemini.repository.FilmRepoImpl;

public class FilmServiceImpl implements FilmService{
	
	private FilmRepoImpl filmRepoImpl;
	
	

	public FilmServiceImpl(FilmRepoImpl filmRepoImpl) {
		super();
		this.filmRepoImpl = filmRepoImpl;
	}

	public String createFilm(Film film) {
		
		if(film.getActors()!=null && film.getAlbum()!=null && film.getCatgories()!=null && film.getDescription()!=null && film.getLanguage()!=null && film.getLength()!=0 && film.getRating()!=0 && film.getReleaseYear()!=null && film.getTitle()!=null){
			
			if(film.getLength()<=0 || film.getRating()<=0)
				throw new IllegalArgumentException();
		
			if(filmRepoImpl.addFilm(film))
				return "mission successful";
		}	
		return null;
	}

	public String removeFilm(String title) {
		
		if(title!=null){
			if(filmRepoImpl.removeFilm(title))
				return "mission successful";
		}
		return null;
	}

	public String modifyFilm(Film film) {
		
		if(film!=null){
			if(filmRepoImpl.modifyFilm(film))
				return "mission successful";
		}
		return null;
	}

	public Film searchFilmByTitle(String title) {
		
		if(title!=null){
			Film film=filmRepoImpl.searchFilmByTitle(title);
			if(film!=null)
				return film;
		}
		return null;
	}

	public Set<Film> searchFilmByCategory(String categoryName) {
		
		if(categoryName!=null){
			Set<Film> films=filmRepoImpl.searchFilmByCategory(categoryName);
			if(films!=null)
				return films;
		}
		return null;
	}

	public Set<Film> searchFilmByRating(byte rating) {
		
		if(rating!=0){
			if(rating<0)
				throw new IllegalArgumentException();
			Set<Film> films=filmRepoImpl.searchFilmByRating(rating);
			if(films!=null)
				return films;
		}
		return null;
	}

	public Set<Film> searchFilmByLanguage(String language) {
		
		if(language!=null){
			Set<Film> films=filmRepoImpl.searchFilmByLanguage(language);
			if(films!=null)
				return films;
		}
		return null;
	}

	public Set<Film> searchFilmByActor(String firstName, String lastName) {
		
		if(firstName!=null && lastName!=null){
			
			Set<Film> films=filmRepoImpl.searchFilmByActor(firstName, lastName);
			if(films!=null)
				return films;
		}
		return null;
	}

	public Set<Film> getAllFilms() {
		
		Set<Film> films=filmRepoImpl.getAllFilms();
		
		if(films!=null)
			return films;
		return null;
	}
}
