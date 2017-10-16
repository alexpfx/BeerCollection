
package com.github.alexpfx.udacity.beercollection.domain.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SearchBeerResponse {

    @SerializedName("beer")
    private List<Beer> mBeer;
    @SerializedName("currentPage")
    private Long mCurrentPage;
    @SerializedName("numberOfPages")
    private Long mNumberOfPages;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("totalResults")
    private Long mTotalResults;

    public List<Beer> getBeer() {
        return mBeer;
    }

    public void setBeer(List<Beer> beer) {
        mBeer = beer;
    }

    public Long getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(Long currentPage) {
        mCurrentPage = currentPage;
    }

    public Long getNumberOfPages() {
        return mNumberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        mNumberOfPages = numberOfPages;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
