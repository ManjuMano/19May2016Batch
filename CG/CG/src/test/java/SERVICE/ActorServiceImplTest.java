package SERVICE;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Actor;
import com.capgemini.beans.Album;
import com.capgemini.beans.Film;
import com.capgemini.repository.ActorRepoImpl;
import com.capgemini.service.ActorServiceImpl;

public class ActorServiceImplTest {

	@Mock
	ActorRepoImpl actorRepoImpl;
	
	ActorServiceImpl  actorServiceImpl;
	
	@Before
	public void init(){
		
		MockitoAnnotations.initMocks(this);
		actorServiceImpl=new ActorServiceImpl(actorRepoImpl);
	}
	
	//when any of the parameter is null it must return NULL
	@Test
	public void createActorReturnNullWithNullInputs(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender(null);
		
		assertEquals(null,actorServiceImpl.createActor(actor));
	}
	
	
	//when all values are valid then it returns valid
	@Test
	public void createActorReturnValidWithValidInputs(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.addActor(actor)).thenReturn(true);
		
		
		assertEquals("mission successful",actorServiceImpl.createActor(actor));
	}
	
	//when duplicate entry occurs that time it will return null
	@Test
	public void createActorReturnNullWithDuplicateEntry(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.addActor(actor)).thenReturn(false);
		
		assertEquals(null,actorServiceImpl.createActor(actor));
	}
	
	// createActor will return null when dependent method return false
	
	@Test
	public void createActorWillReturnNullWhenDependentMethodReturnFalse(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.addActor(actor)).thenReturn(false);
		
		
		assertEquals(null,actorServiceImpl.createActor(actor));
	}
	
	//It must return valid when Inputs are valid
	@Test
	public void removeActorWillRemoveWithValidInputs(){
		
		Mockito.when(actorRepoImpl.removeActor("aslam", "assu")).thenReturn(true);
		
		assertEquals("mission successful",actorServiceImpl.removeActor("aslam", "assu"));
	}
	
	//Remove actor will return null when with invalid inputs.
	
	@Test
	public void removeActorWillReturnNullWithInvalidNullInputs(){
		
		assertEquals(null,actorServiceImpl.removeActor(null, "assu"));
	}
	
	//removeActor will return null when dependent method return false
	@Test
	public void removeActorWillReturnNullWhenDependentMethodReturnFalse(){
		
		Mockito.when(actorRepoImpl.removeActor("aslam", "assu")).thenReturn(false);
		
		assertEquals(null,actorServiceImpl.removeActor("aslam", "assu"));
		
	}
	
	//It must return valid when Inputs are valid
	@Test
	public void modifyActorWillReturnValidWithValidInputs(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.modifyActor(actor)).thenReturn(true);
		
		assertEquals("mission successful",actorServiceImpl.modifyActor(actor));
	}
	
	//It must return null when inputs are invalid or null
	
	@Test
	public void modifyActorWillReturnInvalidWithInvalidInputs(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		assertEquals(null,actorServiceImpl.modifyActor(actor));
	}
	
	//modifyActor must return null when dependent method return false
	@Test
	public void modifyActorWillReturnNullWhenDependentMethodReturnFalse(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.modifyActor(actor)).thenReturn(false);
		
		assertEquals(null,actorServiceImpl.modifyActor(actor));
	}
	
	//searchActorByName must return valid when inputs were valid
	
	@Test
	public void searchByNameWillReturnValidWithValidInputs(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.searchActorByName("Sharuq", "Khan")).thenReturn(actor);
		
		assertEquals(actor,actorServiceImpl.searchActorByName("Sharuq", "Khan"));
	}
	
	//searchActorByName must return null when inputs are invalid
	
	@Test
	public void searchByNameWillReturnNullWithInvalidOrNullInputs(){
		
		
		assertEquals(null,actorServiceImpl.searchActorByName(null, "Khan"));
	}
	
	//searchActorByName will return null when dependent method return null
	
	@Test
	public void searchByNameWillReturnNullWhenDependentMethodReturnNull(){
		
		Actor actor=new Actor();
		actor.setAlbum(new Album());
		actor.setFilms(new HashSet<Film>());
		actor.setFirstName("Sharuq");
		actor.setLastName("Khan");
		actor.setGender("male");
		
		Mockito.when(actorRepoImpl.searchActorByName("Sharuq", "Khan")).thenReturn(null);
		
		assertEquals(null,actorServiceImpl.searchActorByName("Sharuq", "Khan"));
	}
	
	//getAllActors must return list of actors when dependent method return list of films
	
	@Test
	public void getAllActorsMustReturnListOfActorsWhenDependentMethodReturnsList(){
		
		Set<Actor> actors=new HashSet<Actor>();
		Mockito.when(actorRepoImpl.getAllActors()).thenReturn(actors);
		
		assertEquals(actors, actorServiceImpl.getAllActors());
	}
	
	//getAllActors must return null when dependent method returns null
	
	@Test
	public void getAllActorsMustReturnNullWhenDependentMethodReturnNull(){
		
		Mockito.when(actorRepoImpl.getAllActors()).thenReturn(null);
		
		assertEquals(null, actorServiceImpl.getAllActors());
	}
	
}






























