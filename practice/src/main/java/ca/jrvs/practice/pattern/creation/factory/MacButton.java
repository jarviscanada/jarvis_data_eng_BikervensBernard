package ca.jrvs.practice.pattern.creation.factory;

public class MacButton implements IButton {

    @Override
    public void onClick() {
        System.out.println("opening app store app...");
    }

    @Override
    public String render() {
        return "Button('Sign In', action: signIn)";
    }
}