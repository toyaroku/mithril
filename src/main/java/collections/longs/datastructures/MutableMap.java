package collections.longs.datastructures;

public interface MutableMap extends Map {

    void define(long domainElement, long codomainElement);

    void remove(long domainElement);

    void update(long domainElement, long newCodomainElement, Update update);

    @Override
    MutableMap copy();
}
