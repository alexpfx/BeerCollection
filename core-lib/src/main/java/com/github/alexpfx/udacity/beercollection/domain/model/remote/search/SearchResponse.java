package com.github.alexpfx.udacity.beercollection.domain.model.remote.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SearchResponse {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("numberOfPages")
    private int numberOfPages;

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("currentPage")
    private int currentPage;

    @SerializedName("status")
    private String status;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "SearchResponse{" +
                        "totalResults = '" + totalResults + '\'' +
                        ",numberOfPages = '" + numberOfPages + '\'' +
                        ",data = '" + data + '\'' +
                        ",currentPage = '" + currentPage + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}