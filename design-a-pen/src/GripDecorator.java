public class GripDecorator extends PenDecorator {

    public GripDecorator(Pen pen) {
        super(pen);
    }

    @Override
    public void start() {
        pen.start();
    }

    @Override
    public void write(String text) {

        System.out.println("Writing with better grip...");

        pen.write(text);
    }
}