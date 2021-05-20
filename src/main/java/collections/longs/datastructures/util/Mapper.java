package collections.longs.datastructures.util;

public class Mapper {

    public interface Function<C, T> {

        T map(C container);
    }

    public static  <C, T> GenericView<T> map(GenericView<C> view, Function<C, T> function) {
        return new MappedView<>(view, function);
    }

    private static class MappedView<C, T> implements GenericView<T> {

        private final GenericView<C> original;
        private final Function<C, T> function;

        private MappedView(GenericView<C> original, Function<C, T> function) {
            this.original = original;
            this.function = function;
        }

        @Override
        public boolean hasNext() {
            return original.hasNext();
        }

        @Override
        public void next() {
            original.next();
        }

        @Override
        public T current() {
            return function.map(original.current());
        }
    }
}
