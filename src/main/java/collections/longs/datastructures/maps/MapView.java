package collections.longs.datastructures.maps;

public interface MapView extends MapEntryContainer {

    boolean hasNextEntry();

    void iterateNextEntry();
}
