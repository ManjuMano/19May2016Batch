package com.capgemini.inteface;

import java.sql.SQLException;
import java.util.Set;

import com.capgemini.beans.Actor;

public interface ActorRepo {

	boolean addActor(Actor actor) throws SQLException;
	boolean removeActor(String firstName,String lastName);
	boolean modifyActor(Actor actor);
	Actor searchActorByName(String firstName,String lastName);
	Set<Actor> getAllActors();
}
