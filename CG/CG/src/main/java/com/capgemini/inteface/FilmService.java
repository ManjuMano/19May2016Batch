package com.capgemini.inteface;

import java.util.Set;

import com.capgemini.beans.Film;

public interface FilmService {

	String createFilm(Film film);
	String removeFilm(String title);
	String modifyFilm(Film film);
	Film searchFilmByTitle(String title);
	Set<Film> searchFilmByCategory(String categoryName);
	Set<Film> searchFilmByRating(byte rating);
	Set<Film> searchFilmByLanguage(String language);
	Set<Film> searchFilmByActor(String firstNmae, String lastName);
	Set<Film> getAllFilms();
	}
