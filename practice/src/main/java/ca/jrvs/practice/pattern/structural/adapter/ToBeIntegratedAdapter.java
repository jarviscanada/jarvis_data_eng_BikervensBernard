package ca.jrvs.practice.pattern.structural.adapter;

/** allows objects with incompatible interfaces to collaborate.
 *  converts the interface of one object so that another object can understand it.<br/>
 *  i.e. my app use java.class for my model in mvc ex: tweet.status().<br/>
 *  but the model data is in json from a 3rd-party api.<br/> <br/>
 *
 * Object adapter (composition principle): <br/>
 * the adapter implements the interface of one object and wraps the other one.
 * It can be implemented in all popular programming languages.<br/> <br/>
 *
 * Class adapter (inheritance):
 * inherits interfaces from both objects at the same time.
 * only be implemented in programming languages that support multiple inheritance
 */
public class ToBeIntegratedAdapter extends RoundPeg{
    private ToBeIntegratedSquarePeg peg;
    public ToBeIntegratedAdapter(ToBeIntegratedSquarePeg peg) {this.peg = peg;}
    @Override
    public double getRadius() {
        // Calculate a minimum circle radius, which can fit this peg.
        return (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
    }

    //Client
    public static void main(String[] args) {
        // Round fits round, no surprise.
        if (new RoundHole(5).fits(new RoundPeg(5))) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        ToBeIntegratedSquarePeg smallSqPeg = new ToBeIntegratedSquarePeg(2);
        ToBeIntegratedSquarePeg largeSqPeg = new ToBeIntegratedSquarePeg(20);
        //new RoundHole(5).fits(smallSqPeg); // Won't compile this is why we needed to adapt

        // Adapter solves the problem.
        ToBeIntegratedAdapter smallSqPegAdapter = new ToBeIntegratedAdapter(smallSqPeg);
        ToBeIntegratedAdapter largeSqPegAdapter = new ToBeIntegratedAdapter(largeSqPeg);
        if (new RoundHole(5).fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!new RoundHole(5).fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
