package the.max.piskvorky;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Main {

    public Random r = new Random();

    public ArrayList<Field> fields = new ArrayList<Field>();

    public static void main(String[] args) {
        Main m = new Main();
        m.start();
    }

    public void start() {
        createFields();
        draw();
        simulate();
    }

    public void simulate() {
        ArrayList<Field> simulateFields = (ArrayList<Field>) fields.clone();
        Type typeX = Type.X, typeO = Type.O;
        while (simulateFields.size() != 0) {
            Field f = simulateFields.get(r.nextInt(simulateFields.size()));
            Type actual = Type.O;
            if (simulateFields.size() % 2 == 0)
                actual = Type.X;
            f.type = actual;
            simulateFields.remove(f);
        }
        draw();
    }

    public void createFields() {
        fields.clear();
        for (int x = 1; x <= 15; x++) {
            for (int y = 1; y <= 15; y++) {
                fields.add(new Field(x, y));
            }
        }
    }

    public void draw() {
        for (Field f : fields) {
            System.out.print(f.type.toString());
            if (f.y % 15 == 0)
                System.out.println("");
            else
                System.out.print(" ");
        }
        System.out.println("");
    }

    public boolean setField(int x, int y, Type type) {
        Field field = getField(x, y);
        if (field != null) {
            // TODO: ADD
        }
        return false;
    }

    public Field getField(int x, int y) {
        for (Field f : fields) {
            if (f.x == x && f.y == y) {
                return f;
            }
        }
        return null;
    }

}
