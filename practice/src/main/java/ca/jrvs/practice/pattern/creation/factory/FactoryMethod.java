package ca.jrvs.practice.pattern.creation.factory;

public class FactoryMethod{

    //client
    public static void main(String[] args) {
        UiFactory factory;
        /**
         * The concrete factory is usually chosen depending on configuration or
         * environment options.
         */
        if (System.getProperty("os.name").contains("mac")) {
            factory = new MacUI();
        } else {
            factory = new HTMLUI();
        }
        factory.render();
    }
}
