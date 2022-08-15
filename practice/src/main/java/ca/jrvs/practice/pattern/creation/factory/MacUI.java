package ca.jrvs.practice.pattern.creation.factory;

public class MacUI extends UiFactory{
    @Override
    public IButton createButton() { return new MacButton();}
}
