package com.book.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private @NotBlank String name;
	private @NotBlank String email;
	private @NotBlank String imageUrl;
	// add imageURL here
	@JsonIgnore
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	Set<Books> books;

	public Author() {
	}

	public Author(@NotBlank String name, @NotBlank String email) {
		this.name = name;
		this.email = email;
	}

	public Author(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String imageUrl) {
		this.name = firstName;
		this.email = lastName;
		this.imageUrl = imageUrl;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Books> getBooks() {
		return books;
	}

	public void setBooks(Set<Books> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "User {Author id=" + id + ", name='" + name + "', email='" + email + "'}";
	}

	public Integer getId() {
		return id;
	}
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
