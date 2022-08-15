package ca.jrvs.practice.pattern.creation.factory;

public class HTMLButton implements IButton {

    @Override
    public void onClick() {
        System.out.println("redirection to twitter.com ...");
    }

    @Override
    public String render() {
        return "<button> tweet! </button>";
    }
}