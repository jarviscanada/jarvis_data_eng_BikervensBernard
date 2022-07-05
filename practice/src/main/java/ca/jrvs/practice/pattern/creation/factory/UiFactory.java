package ca.jrvs.practice.pattern.creation.factory;

/** Base factory class. <br/>
 * provides an interface for creating objects in a superclass,
 * but allows subclasses to alter the type of objects that will be created.*/
public abstract class UiFactory {

    public void render() {
        IButton okButton = createButton();
        System.out.println(okButton.render());
        okButton.onClick();
    }

    /** Subclasses will override this method in order to create specific buttonobjects.*/
    public abstract IButton createButton();
}