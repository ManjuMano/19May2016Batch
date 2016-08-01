package REPO;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.capgemini.beans.Actor;
import com.capgemini.repository.ActorRepoImpl;

public class ActorRepoImplTest {

	ActorRepoImpl actorRepoImpl;
	
	@Before
	public void init(){
		
		actorRepoImpl=new ActorRepoImpl();
	}
	
	//the add actor must return false if the actor object is null
	
	@Test
	public void addActorReturnFalseIfActorObjectIsNull(){
		
		Actor actor=null;
		assertEquals(false, actorRepoImpl.addActor(actor));
	}
	
	//removeActor must return false if the input is null
	
	@Test
	public void removeActorMustReturnFalseIfTheInputIsNull(){
		
		assertEquals(false, actorRepoImpl.removeActor("aslam", null));
	}
	
	//modifyActor will return false if the input is null
	
	@Test
	public void modifyActorWillReturnFalseWithNullInput(){
		
		Actor actor=null;
		assertEquals(false, actorRepoImpl.modifyActor(actor));
	}
	
	//searchActorByName will return false if the input is null
	
	@Test
	public void searchActorByNameWillReturnFalseIfTheInputIsNull(){
		
		assertEquals(null, actorRepoImpl.searchActorByName("aslma", null));
	}
	
	

}
