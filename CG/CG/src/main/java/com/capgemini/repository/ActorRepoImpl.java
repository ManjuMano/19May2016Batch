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
import com.capgemini.beans.Film;
import com.capgemini.inteface.ActorRepo;

public class ActorRepoImpl implements ActorRepo {

	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("hello");

	EntityManager entityManager=entityManagerFactory.createEntityManager();

	public boolean addActor(Actor actor)  {
		
		if(actor==null)
			return false;

		TypedQuery<Actor> firstQuery=entityManager.createQuery("SELECT actor FROM Actor actor",Actor.class);
		List<Actor> actors=firstQuery.getResultList();

		for(Actor actualActor:actors){

			if(actualActor.getFirstName().equals(actor.getFirstName()) && actualActor.getLastName().equals(actor.getLastName())){
				return false;				
			}		
		}

		TypedQuery<Film> secondQuery=entityManager.createQuery("SELECT film FROM Film film",Film.class);
		List<Film> films=secondQuery.getResultList();

		Set<Film> filmsSet=new HashSet<Film>();

		for(Film filmFromUser:actor.getFilms()){
			int flag=0;
			Film film=null;
			for(Film filmFromDB:films){

				if(filmFromDB.getTitle().equals(filmFromUser.getTitle())){
					film=filmFromDB;
					flag++;
					break;
				}
			}
			if(flag==0){
				return false;
			}
			else{
				film.getActors().add(actor);
				filmsSet.add(film);
			}
		}

		actor.getFilms().clear();
		actor.getFilms().addAll(filmsSet);
		entityManager.getTransaction().begin();
		entityManager.persist(actor);
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean removeActor(String firstName, String lastName) {
		
		if(firstName==null || lastName==null){
			return false;
		}
		
		TypedQuery<Actor> query=entityManager.createQuery("SELECT actor FROM Actor actor",Actor.class);
		List<Actor> actors=query.getResultList();
		
		for(Actor actorFromDB:actors){
			
			if(actorFromDB.getFirstName().equals(firstName)&&actorFromDB.getLastName().equals(lastName)){
				actorFromDB.setDeleteDate(new Date());
				entityManager.getTransaction().begin();
				entityManager.merge(actorFromDB);
				entityManager.getTransaction().commit();
				return true;
			}
		}
		
		return false;
	}

	public boolean modifyActor(Actor actor) {
		
		if(actor==null)
			return false;
		
		TypedQuery<Actor> query=entityManager.createQuery("SELECT actor FROM Actor actor",Actor.class);
		List<Actor> actors=query.getResultList();
		
		Actor tempActor=null;
		for(Actor actorFromDB:actors){
			
			if(actorFromDB.getFirstName().equals(actor.getFirstName())&&actorFromDB.getLastName().equals(actor.getLastName())){
				
				actorFromDB=tempActor;
				entityManager.getTransaction().begin();
				entityManager.merge(actorFromDB);
				entityManager.getTransaction().commit();
				return true;
			}
		}
		
		
		return false;
	}

	public Actor searchActorByName(String firstName, String lastName) {
		
		if(firstName==null || lastName==null)
			return null;
		
		TypedQuery<Actor> firstQuery=entityManager.createQuery("SELECT actor FROM Actor actor",Actor.class);
		List<Actor> actors=firstQuery.getResultList();

		for(Actor actualActor:actors){

			if(actualActor.getFirstName().equals(actualActor.getFirstName()) && actualActor.getLastName().equals(actualActor.getLastName())){
				return actualActor;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Set<Actor> getAllActors() {
		
		TypedQuery<Actor> firstQuery=entityManager.createQuery("SELECT actor FROM Actor actor WHERE deleteDate IS NULL",Actor.class);
		List<Actor> actors=firstQuery.getResultList();
		
		return (Set<Actor>) actors;
	}

}
