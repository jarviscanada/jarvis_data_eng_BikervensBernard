package ca.jrvs.practice.pattern.structural.adapter;

public class RoundHole{
    private double radius;

    public RoundHole(double radius) {this.radius = radius;}

    public double getRadius() {return radius;}

    public boolean fits(RoundPeg ball) {return this.getRadius() >= ball.getRadius();}
}
