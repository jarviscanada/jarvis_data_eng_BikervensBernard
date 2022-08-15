package ca.jrvs.practice.pattern.structural.adapter;

public class ToBeIntegratedSquarePeg{
    private double width;
    public ToBeIntegratedSquarePeg(double width) {this.width = width;}
    public double getWidth() {return width;}
    public double getSquare() {return Math.pow(this.width, 2);}
}
