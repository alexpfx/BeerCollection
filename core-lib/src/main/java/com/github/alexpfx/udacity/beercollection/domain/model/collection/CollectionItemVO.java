package com.github.alexpfx.udacity.beercollection.domain.model.collection;

/**
 * Created by alexandre on 09/11/17.
 */

public class CollectionItemVO implements Comparable<CollectionItemVO> {
    String beerId;
    long timestamp;
    int quantity;

    public CollectionItemVO() {
    }


    public CollectionItemVO(String beerId, long timestamp, int quantity) {
        this.beerId = beerId;
        this.timestamp = timestamp;
        this.quantity = quantity;
    }

    public String getBeerId() {
        return beerId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CollectionItemVO{" +
                "beerId='" + beerId + '\'' +
                ", timestamp=" + timestamp +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(CollectionItemVO collectionItemVO) {
        return timestamp >= collectionItemVO.timestamp ? 1 : -1;

    }
}
