package SERVICE;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Actor;
import com.capgemini.beans.Album;
import com.capgemini.beans.Category;
import com.capgemini.beans.Film;
import com.capgemini.repository.FilmRepoImpl;
import com.capgemini.service.FilmServiceImpl;

public class FilmServiceImplTest {

	FilmServiceImpl filmServiceImpl;
	
	@Mock
	FilmRepoImpl filmRepoImpl;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		filmServiceImpl=new FilmServiceImpl(filmRepoImpl);
	}
	
	//createFilm must return valid with valid inputs
	@Test
	public void createFilmWillReturnValidWithValidInputs() {
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)90);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Mockito.when(filmRepoImpl.addFilm(film)).thenReturn(true);
		
		assertEquals("mission successful",filmServiceImpl.createFilm(film));
	}
	
	//createFilm must return null when any input is null
	
	@Test
	public void createFilmWillReturnNullWithInvalidOrNullInputs(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)90);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		assertEquals(null,filmServiceImpl.createFilm(film));
	}
	
	//createFilm must return null if dependent method returns false
	
	@Test
	public void createFilmWillReturnNullIfDependentMethodReturnFalse() {
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)90);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Mockito.when(filmRepoImpl.addFilm(film)).thenReturn(false);
		
		assertEquals(null,filmServiceImpl.createFilm(film));
	}
	
	
	// createFilm must throw an exception when with illegal arguments
	@Test(expected=IllegalArgumentException.class)
	public void createFilmWillThrowAnExceptionWithIllegalArguments() {
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		filmServiceImpl.createFilm(film);
	}
	
	//removeFilm must return valid when inputs are valid
	
	@Test
	public void removeFilmWillReturnValidWithValidInputs(){
		
		Mockito.when(filmRepoImpl.removeFilm("Jalsa")).thenReturn(true);
		
		assertEquals("mission successful",filmServiceImpl.removeFilm("Jalsa"));
	}
	
	//removeFilm must return null with invalid or null inputs
	
	@Test
	public void removeFilmWillReturnNullWithInvalidInputs(){
		
		assertEquals(null,filmServiceImpl.removeFilm(null));
	}
	
	//removeFilm must return null if dependent method return false
	
	@Test
	public void removeFilmWillReturnNullWhenDependentMethodReturnFalse(){
		
		Mockito.when(filmRepoImpl.removeFilm("Jalsa")).thenReturn(false);
		
		assertEquals(null,filmServiceImpl.removeFilm("Jalsa"));
	}
	
	//modifyFilm will return valid with valid inputs
	
	@Test
	public void modifyFilmWillReturnValidWithValidInput(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Mockito.when(filmRepoImpl.modifyFilm(film)).thenReturn(true);
		
		assertEquals("mission successful", filmServiceImpl.modifyFilm(film));
	}
	
	//modifyFilm will return null when dependent method returns false
	
	@Test
	public void modifyFilmWillReturnNullWhenDependentMethodReturnFalse(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Mockito.when(filmRepoImpl.modifyFilm(film)).thenReturn(false);
		
		assertEquals(null, filmServiceImpl.modifyFilm(film));
	}
	
	//modifyFilm will return null when inputs are Invalid or null
	
	@Test
	public void modifyFilmWillReturnNullWhenInputsAreNull(){
		
		assertEquals(null, filmServiceImpl.modifyFilm(null));
	}
	
	//searchFilmByTitle will return film with valid inputs 
	
	@Test
	public void searchFilmByTitleWillReturnFilmWithValidInput(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Mockito.when(filmRepoImpl.searchFilmByTitle("bhahubali")).thenReturn(film);
		
		assertEquals(film,filmServiceImpl.searchFilmByTitle("bhahubali"));
	}
	
	//searchFilmByTitle will return null with invalid or null inputs
	
	@Test
	public void searchFilmByTitleWillReturnNullWithInvalidOrNullInput(){
		
		assertEquals(null,filmServiceImpl.searchFilmByTitle(null));
	}
	
	//serachFilmByTitle will return Null when dependent method return false
	
	@Test
	public void searchFilmByTitleWillReturnNullWhenDependentMethodReturnNull(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Mockito.when(filmRepoImpl.searchFilmByTitle("bhahubali")).thenReturn(null);
		
		assertEquals(null,filmServiceImpl.searchFilmByTitle("bhahubali"));
	}
	
	//searchFilmByCategory must return List of films with valid input
		
	@Test
	public void searchFilmByCategoryWillReturnRecordsWithValidInput(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByCategory("epic")).thenReturn(films);
		
		assertEquals(films,filmServiceImpl.searchFilmByCategory("epic"));
	}
	
	//searchFilmByCategory will return null with invalid or null inputs
	
	@Test
	public void searchFilmByCategoryWillReturnNullWithInvalidOrNullInput(){
		
		assertEquals(null,filmServiceImpl.searchFilmByCategory(null));
	}
	
	// searchFilmByCategory will return null when dependent method return null
	
	
	@Test
	public void searchFilmByCategoryWillReturnNullWhenDependentMethodReturnNull(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByCategory("bhahubali")).thenReturn(null);
		
		assertEquals(null,filmServiceImpl.searchFilmByCategory("bhahubali"));
	}
	
	// searchFilmByRating will return list of films with valid input
	
	@Test
	public void searchFilmByRatingWillReturnRecordsWithValidInput(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)-5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByRating((byte)5)).thenReturn(films);
		
		assertEquals(films,filmServiceImpl.searchFilmByRating((byte)5));
	}
	
	//searchFilmByRating will throw exception with invalid or null inputs
	
	@Test
	public void searchFilmByRatingWillReturnNullWithInvalidOrNullInput(){
		
		assertEquals(null,filmServiceImpl.searchFilmByRating((byte)0));
	}
	
	//searchFilmByRating will return null when dependent method return null
	
	@Test
	public void searchFilmByRatingWillReturnNullWhenDependentMethodReturnNull(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)55);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		HashSet<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByRating((byte)5)).thenReturn(null);
		
		assertEquals(null,filmServiceImpl.searchFilmByRating((byte)5));
	}
	
	//searchFilmByRating Will return throw exception when input is invalid
	
	@Test(expected=IllegalArgumentException.class)
	public void searchFilmByRatingThrowExceptionWhenInputIsInvalid(){
		
		filmServiceImpl.searchFilmByRating((byte)-5);
	}
	
	//searchFilmByLanguage will return valid with  valid inputs
	
	@Test
	public void searchFilmByLanguageWillReturnValidWithValidIpnput(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("telugu");
		film.setLength((short)5);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByLanguage("telugu")).thenReturn(null);
		
		assertEquals(null,filmServiceImpl.searchFilmByLanguage("telugu"));
	}
	
	//searchFilmByLanguage will return Null if the input is null
	
	@Test
	public void searchFilmByLanguageWillReturnNullWhenInputIsNull(){
		
		assertEquals(null,filmServiceImpl.searchFilmByLanguage(null));
	}
	
	//searchFilmByLanguage will return null if dependent method return null
	
	@Test
	public void searchFilmByLanguageWillReturnNullWhenDependentMethodReturnNull(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)55);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByLanguage("telugu")).thenReturn(null);
		
		assertEquals(null,filmServiceImpl.searchFilmByLanguage("telugu"));
	}
	
	//searchFilmByActor will return valid with valid inputs
	
	@Test
	public void searchFilmByActorWillReturnValidWithValidInputs(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)55);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByActor("prabhas","raaju")).thenReturn(films);
		
		assertEquals(films,filmServiceImpl.searchFilmByActor("prabhas","raaju"));
	}
	
	//searchFilmByActor will return null when any input null
	
	@Test
	public void searchFilmByActorWillReturnNullWhenInputIsNull(){
		
		assertEquals(null,filmServiceImpl.searchFilmByActor(null,"raaju"));
	}
	
	//searchFilmByActor will return null when dependent method return null
	
	@Test
	public void searchFilmByActorWillReturnNullWhenDependentMethodIsNull(){
		
		Film film=new Film();
		film.setActors(new HashSet<Actor>());
		film.setAlbum(new Album());
		film.setCatgories(new ArrayList<Category>());
		film.setDescription("This film is an epic.");
		film.setLanguage("All languages");
		film.setLength((short)55);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setTitle("bhahubali");
		
		Set<Film> films=new HashSet<Film>();
		films.add(film);
		
		Mockito.when(filmRepoImpl.searchFilmByActor("prabhas","raaju")).thenReturn(null);
		
		assertEquals(null,filmServiceImpl.searchFilmByActor("prabhas","raaju"));
	}
	
	//getAllFilms must return list of actors when dependent method return list of films
	
		@Test
		public void getAllFilmsMustReturnListOfActorsWhenDependentMethodReturnsList(){
			
			Set<Film> films=new HashSet<Film>();
			Mockito.when(filmRepoImpl.getAllFilms()).thenReturn(films);
			
			assertEquals(films, filmServiceImpl.getAllFilms());
		}
		
		//getAllfilms must return null when dependent method returns null
		
		@Test
		public void getAllFilmsMustReturnNullWhenDependentMethodReturnNull(){
			
			Mockito.when(filmRepoImpl.getAllFilms()).thenReturn(null);
			
			assertEquals(null, filmServiceImpl.getAllFilms());
		}
}
