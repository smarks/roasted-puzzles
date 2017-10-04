/*
 *  Copyright  (c)  2017.  Spencer Marks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.orgamisoftware.puzzles.streams;

import java.util.List;
import java.util.Set;

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