package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

import lombok.Data;
import lombok.ToString;

@Generated("com.robohorse.robopojogenerator")
@Data
@ToString
public class SearchResponse extends ServerResponse{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("numberOfPages")
	private int numberOfPages;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("currentPage")
	private int currentPage;


	@Override
	public boolean validate() {
		return data != null && !data.isEmpty();
	}


}