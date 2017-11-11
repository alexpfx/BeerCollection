package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SearchResponse extends ServerResponse{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("numberOfPages")
	private int numberOfPages;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("currentPage")
	private int currentPage;

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setNumberOfPages(int numberOfPages){
		this.numberOfPages = numberOfPages;
	}

	public int getNumberOfPages(){
		return numberOfPages;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}


	@Override
	public boolean validate() {
		return data != null && !data.isEmpty();
	}


}