package classlib;

public interface Child extends Parent {

    @Override
    public default void welcome() {
        message("Child: Hi!");
    }

}