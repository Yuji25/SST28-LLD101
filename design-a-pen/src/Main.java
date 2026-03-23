public class Main {
    public static void main(String[] args) {
        Pen pen = PenFactory.getPen("ink-pen", "blue", "with-cap");

        pen.start();
        pen.write("I can't fix my past mistakes, but at least, I can guarantee that I won't repeat it AND THIS IS MY GUARANTEE !");
        pen.close();

        pen.refill("black");
        // pen.write("will fail"); 

        Pen pen2 = PenFactory.getPen("ball-pen", "black", "click");

        pen2 = new GripDecorator(pen2);

        pen2.start();
        pen2.write("Nah I'd Win");
    }
}
