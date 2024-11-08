package com.github.alexpfx.udacity.beercollection.domain.model.collection;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CollectionItem {

    private List<CollectionItemVO> itemVOList;
    private Beer beer;
    private Object tag;

    public CollectionItem(Beer beer, List<CollectionItemVO> itemVOList) {
        this.beer = beer;
        this.itemVOList = itemVOList;
    }

    public CollectionItem() {
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "CollectionItem{" +
                "itemVOList=" + itemVOList +
                ", beer=" + beer +
                '}';
    }

    public void add(CollectionItemVO vo) {
        itemVOList.add(vo);
    }


    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public void merge(CollectionItem collectionItem) {
        beer = collectionItem.getBeer();
        if (itemVOList == null) {
            itemVOList = collectionItem.itemVOList;
        } else {
            itemVOList.addAll(collectionItem.itemVOList);
        }
    }


    public Date getLastDate() {
        if (itemVOList == null) {
            return null;
        }
        Collections.sort(itemVOList, CollectionItemVO::compareTo);
        return new Date(itemVOList.get(itemVOList.size() - 1).timestamp);
    }

    public int countBeers() {
        if (itemVOList == null) {
            return 0;
        }
        int count = 0;
        for (CollectionItemVO item : itemVOList) {
            count += item.getQuantity();
        }
        return count;
    }
}
