package com.orgamisoftware.puzzles.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Track {

    private String name;
    private int length;

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}

class Album {
    private List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }
}

class Catalog {

    public static Set<String> getLongTrackNames(List<Album> albums) {
        return null;
      /*return albums.stream().
               map(Album::getTrackList).
               flatMap(tracks -> tracks.stream()).
               filter( track -> track.getLength() > 60).
               collect(Track::getName);
    */
    }
}