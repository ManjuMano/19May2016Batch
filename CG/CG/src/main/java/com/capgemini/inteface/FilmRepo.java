package com.capgemini.inteface;

import java.util.Set;

import com.capgemini.beans.Film;

public interface FilmRepo {

	boolean addFilm(Film film);
	boolean removeFilm(String title);
	boolean modifyFilm(Film film);
	Film searchFilmByTitle(String title);
	Set<Film> searchFilmByCategory(String categoryName);
	Set<Film> searchFilmByRating(byte rating);
	Set<Film> searchFilmByLanguage(String language);
	Set<Film> searchFilmByActor(String firstName,String lastName);
	Set<Film> getAllFilms();
}
