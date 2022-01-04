package com.kamged.kmovies.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tvseries")
public class TVSeries extends DomainBase{

    private String seasons;

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }
}
