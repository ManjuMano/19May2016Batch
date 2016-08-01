package com.capgemini.inteface;



import java.util.Set;

import com.capgemini.beans.Actor;

public interface ActorService {

	String createActor(Actor actor);
	String removeActor(String firstName,String lastName);
	String modifyActor(Actor actor);
	Actor searchActorByName(String firstName,String lastName);
	Set<Actor> getAllActors();
}
