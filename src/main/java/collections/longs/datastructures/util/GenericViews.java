package collections.longs.datastructures.util;

import collections.longs.datastructures.bitsets.BitSetContainer;
import collections.longs.datastructures.bitsets.BitSetView;
import collections.longs.datastructures.iterators.LongElementContainer;
import collections.longs.datastructures.lists.ListEntryContainer;
import collections.longs.datastructures.lists.ListView;
import collections.longs.datastructures.maps.MapEntryContainer;
import collections.longs.datastructures.maps.MapView;
import collections.longs.datastructures.sets.SetView;

public class GenericViews {

    public static List list(ListView listView) {
        return new List(listView);
    }

    public static Set set(SetView setView) {
        return new Set(setView);
    }

    public static Map list(MapView mapView) {
        return new Map(mapView);
    }

    private static class List implements GenericView<ListEntryContainer> {

        private final ListView listView;

        private List(ListView listView) {
            this.listView = listView;
        }

        @Override
        public boolean hasNext() {
            return listView.hasNextIndex();
        }

        @Override
        public void next() {
            listView.iterateNextIndex();
        }

        @Override
        public ListEntryContainer current() {
            return listView;
        }
    }

    private static class Set implements GenericView<LongElementContainer> {

        private final SetView setView;

        private Set(SetView setView) {
            this.setView = setView;
        }

        @Override
        public boolean hasNext() {
            return setView.hasNextUnique();
        }

        @Override
        public void next() {
            setView.iterateNextUnique();
        }

        @Override
        public LongElementContainer current() {
            return setView;
        }
    }

    private static class Map implements GenericView<MapEntryContainer> {

        private final MapView mapView;

        private Map(MapView napVIew) {
            this.mapView = napVIew;
        }

        @Override
        public boolean hasNext() {
            return mapView.hasNextEntry();
        }

        @Override
        public void next() {
            mapView.iterateNextEntry();
        }

        @Override
        public MapEntryContainer current() {
            return mapView;
        }
    }
}
