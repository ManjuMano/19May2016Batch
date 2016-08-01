package com.cg.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.beans.Actor;
import com.capgemini.beans.Album;
import com.capgemini.beans.Category;
import com.capgemini.beans.Film;
import com.capgemini.beans.Image;
import com.capgemini.repository.FilmRepoImpl;
import com.capgemini.service.FilmServiceImpl;


public class Test {

	public static void main(String[] args) {
		
		
		Image image1=new Image();
		image1.setImageURL("prabhas.jpg");
		image1.setCreateDate(new Date());
		
		Image image2=new Image();
		image2.setImageURL("prabhasfmaily.jpg");
		image2.setCreateDate(new Date());
		
		Image image3=new Image();
		image3.setImageURL("Anushka.jpg");
		image3.setCreateDate(new Date());
		
		Image image4=new Image();
		image4.setImageURL("Anushkafamaily.jpg");
		image4.setCreateDate(new Date());
		
		Image image5=new Image();
		image5.setImageURL("bahubali.jpg");
		image5.setCreateDate(new Date());
		
		Image image6=new Image();
		image6.setImageURL("bahubalicomplete.jpg");
		image6.setCreateDate(new Date());
		
		Album album1=new Album();
		album1.setAlbumName("actor 1's album");
		album1.setCreateDate(new Date());
		
		List<Image> actor1images=new ArrayList<Image>();
		actor1images.add(image1);
		actor1images.add(image2);
		
		album1.setImages(actor1images);
		
		Actor actor1=new Actor();
		actor1.setFirstName("Prabhas");
		actor1.setLastName("Raaju");
		actor1.setGender("Male");
		actor1.setCreateDate(new Date());
		
		actor1.setAlbum(album1);
		
		Album album2=new Album();
		album2.setAlbumName("actor 2's album");
		album2.setCreateDate(new Date());
		
		List<Image> actor2images=new ArrayList<Image>();
		actor2images.add(image3);
		actor2images.add(image4);
		
		album2.setImages(actor2images);
		
		Actor actor2=new Actor();
		actor2.setFirstName("Anushka");
		actor2.setLastName("Shetty");
		actor2.setGender("Female");
		actor2.setCreateDate(new Date());
		
		actor2.setAlbum(album2);
		
		Album filmAlbum=new Album();
		filmAlbum.setAlbumName("bahubali album");
		filmAlbum.setCreateDate(new Date());
		
		List<Image> filmImages=new ArrayList<Image>();
		filmImages.add(image5);
		filmImages.add(image6);
		
		filmAlbum.setImages(filmImages);
		
		Category category=new Category();
		category.setName("epic");
		category.setCreateDate(new Date());
		
		List<Category> categories=new ArrayList<Category>();
		categories.add(category);
		
		
		Film film=new Film(); 
		film.setTitle("Bahubali");
		film.setDescription("This is an epic.");
		film.setLanguage("Telugu");
		film.setLength((short)120);
		film.setRating((byte)5);
		film.setReleaseYear("2016");
		film.setCreateDate(new Date());
		
		film.setAlbum(filmAlbum);
		
		film.getActors().add(actor1);
		film.getActors().add(actor2);
		
		film.setCatgories(categories);
		
		System.out.println(new FilmServiceImpl(new FilmRepoImpl()).createFilm(film));
	}
}
