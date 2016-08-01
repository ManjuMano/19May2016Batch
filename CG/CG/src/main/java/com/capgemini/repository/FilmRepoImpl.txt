package com.capgemini.repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.beans.Actor;
import com.capgemini.beans.Category;
import com.capgemini.beans.Film;
import com.capgemini.inteface.FilmRepo;

public class FilmRepoImpl implements FilmRepo {
	
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("hello");
	
	EntityManager entityManager=entityManagerFactory.createEntityManager();

	public boolean addFilm(Film film) {
		
		TypedQuery<Film> secondQuery=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=secondQuery.getResultList();
		
		for(Film filmsInDB:films){
			
			if(filmsInDB.getTitle().equals(film.getTitle()))
				return false;
		}
		
		TypedQuery<Actor> firstQuery=entityManager.createQuery("SELECT actor FROM Actor actor",Actor.class);
		List<Actor> actors=firstQuery.getResultList();
		Set<Actor> actorSet=new HashSet<Actor>();
		
		for(Actor actorFromUser:film.getActors()){
			boolean flag=false;
			Actor DBActor=null;
			for(Actor actorFromDB:actors){
				
				if(actorFromUser.getFirstName().equals(actorFromDB.getFirstName())&&actorFromUser.getLastName().equals(actorFromDB.getLastName())){
					System.out.println("if pass once");
					DBActor=actorFromDB;
					flag=true;
					break;
				}
				else{
					flag=false;
				}
			}
			if(flag){
				
				System.out.println("flag true scenario");
				actorSet.add(DBActor);
			}
			else{
				Actor newActor=new Actor();
				newActor=actorFromUser;
				newActor.getFilms().add(film);
				entityManager.getTransaction().begin();
				entityManager.persist(newActor);
				entityManager.getTransaction().commit();
				actorSet.add(newActor);
			}
		}
		film.getActors().clear();
		film.getActors().addAll(actorSet);
		entityManager.getTransaction().begin();
		entityManager.persist(film);
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean removeFilm(String title) {
		
		if(title.equals(null)){
			return false;
		}
		
		TypedQuery<Film> query=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=query.getResultList();
		
		for(Film filmFromDB:films){
			
			if(filmFromDB.getTitle().equals(title)){
				filmFromDB.setDeleteDate(new Date());
				return true;
			}
		}
		
		return false;
	}

	public boolean modifyFilm(Film film) {
		
		if(film.equals(null)){
			return false;
		}
		
		TypedQuery<Film> query=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=query.getResultList();
		
		for(Film filmFromDB:films){
			
			if(filmFromDB.getTitle().equals(film.getTitle())){
				filmFromDB=film;
				entityManager.getTransaction().begin();
				entityManager.merge(filmFromDB);
				entityManager.getTransaction().commit();
				return true;
			}
		}
				
		return false;
	}

	public Film searchFilmByTitle(String title) {
		
		if(title==null){
			return null;
		}
		
		TypedQuery<Film> secondQuery=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=secondQuery.getResultList();
		
		for(Film filmInDB:films){
			
			if(filmInDB.getTitle().equals(title))
				return filmInDB;
		}
		return null;
	}

	
	@SuppressWarnings("unused")
	public Set<Film> searchFilmByCategory(String categoryName) {
		
		if(categoryName==null){
			return null;
		}
		
		TypedQuery<Film> secondQuery=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=secondQuery.getResultList();
		Set<Film> filmSet=new HashSet<Film>();
		
		for(Film filmFromDB:films){
			
			for(Category category:filmFromDB.getCatgories()){
				
				if(category.getName().equals(categoryName)){
					filmSet.add(filmFromDB);
				}
			}
		}
		
		if(filmSet!=null){
			return filmSet;
		}
		return null;
	}

	@SuppressWarnings("unused")
	public Set<Film> searchFilmByRating(byte rating) {
		
		if(rating==0){
			return null;
		}
		
		TypedQuery<Film> query=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=query.getResultList();
		Set<Film> filmSet=new HashSet<Film>();
		
		for(Film filmFromDB:films){
			
			if(filmFromDB.getRating()==rating){
				filmSet.add(filmFromDB);
			}
		}
		
		if(filmSet!=null){
			return filmSet;
		}
		
		return null;
	}

	@SuppressWarnings("unused")
	public Set<Film> searchFilmByLanguage(String language) {
		
		if(language==null){
			return null;
		}
		
		TypedQuery<Film> query=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=query.getResultList();
		Set<Film> filmSet=new HashSet<Film>();
		
		for(Film filmFromDB:films){
				
			if(filmFromDB.getLanguage().equals(language)){
				
				filmSet.add(filmFromDB);
			}
		}
		
		if(filmSet!=null){
			return filmSet;
		}
		
		return null;
	}

	@SuppressWarnings("unused")
	public Set<Film> searchFilmByActor(String firstName,String lastName) {
		
		if(firstName==null || lastName==null){
			return null;
		}
		
		TypedQuery<Film> secondQuery=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=secondQuery.getResultList();
		Set<Film> filmSet=new HashSet<Film>();
		
		for(Film filmFromDB:films){
			
			for(Actor actor:filmFromDB.getActors()){
				
				if(actor.getFirstName().equals(firstName)&&actor.getLastName().equals(lastName)){
					filmSet.add(filmFromDB);
				}
			}
		}
		
		if(filmSet!=null){
			return filmSet;
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	public Set<Film> getAllFilms() {
		
		TypedQuery<Film> secondQuery=entityManager.createQuery("SELECT film FROM Film film where deleteDate is null",Film.class);
		List<Film> films=secondQuery.getResultList();
		
		return (Set<Film>)films;
	}

}
