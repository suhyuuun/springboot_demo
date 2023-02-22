package com.ugotfilm.data.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDTO {
	private int moviecode ;
	private String title ;
	private String overview ;
	private Date release_date ;
	private String vote_average ;
	private String genre_ids ;
	private String poster_url;
	
	private int count;
}
