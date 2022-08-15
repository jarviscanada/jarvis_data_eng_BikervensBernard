package ca.jrvs.practice.pattern.creation.factory;

public class HTMLUI extends UiFactory{
    @Override
    public IButton createButton() { return new HTMLButton();}
}