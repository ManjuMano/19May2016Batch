package REPO;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.repository.FilmRepoImpl;


public class FilmRepoImplTest {

	
	FilmRepoImpl filmRepoImpl;
	
	@Before
	public void init() {
		filmRepoImpl=new FilmRepoImpl();
	}
	
	//addFilm will return false if the inout is invalid
	
//	@Test
//	public void addFilmWillReturnFalseIfTheInputIsNull() {
//		
//		Film film=new Film();
//		
//		assertEquals(false, filmRepoImpl.addFilm(film));
//	}
//	
//	// remocveActor will return false if the input is invalid
//	@Test
//	public void removeFilmWillReturnFalseWithValidInputs(){
//				
//		assertEquals(false, filmRepoImpl.removeFilm(null));
//	}
//	
//	//modifyFilm Will Return False With invalid input
//	
//	@Test
//	public void modifyFilmWillReturnFalseWithNullInput(){
//		
//		
//		assertEquals(false, filmRepoImpl.modifyFilm(null));
//	}
//	
	//searchFilmByTitle must return film with not null object
	
	@Test
	public void searchFilmByTitleWillReturnTrueWithInvalidInputs(){
		
		assertEquals(null, filmRepoImpl.searchFilmByTitle(null));
	}
	
	//searchFilmByActor must return null with invalid inputs
	
	@Test
	public void searchFilmByActorMustReturnNullWithInvalidInputs(){
		
		assertEquals(null, filmRepoImpl.searchFilmByActor(null,"assu"));
	}
	
	//searchFilmByLanguage will return null with invalid input
	
	@Test
	public void searchFilmByLanguageWillReturnNullWithNullValue(){
		
		assertEquals(null, filmRepoImpl.searchFilmByLanguage(null));
	}
	
	@Test
	public void searchFilmByRatingWillReturnNullWithInvalidInputs(){
		
		assertEquals(null, filmRepoImpl.searchFilmByRating((byte)0));
	}

}
