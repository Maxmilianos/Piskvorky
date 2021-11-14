package the.max.piskvorky;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public Random r = new Random();

    public ArrayList<Field> fields = new ArrayList<Field>();

    public Integer rows = 15;

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
        while (!checkWin()) {
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
        for (int y = 1; y <= rows; y++) {
            for (int x = 1; x <= rows; x++) {
                fields.add(new Field(x, y));
            }
        }
    }

    public void draw() {
        for (Field f : fields) {
            System.out.print(f.type.toString());
            if (f.x % 15 == 0)
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

    public boolean checkWin() {
        HashMap<Type, ArrayList<Field>> map = new HashMap<Type, ArrayList<Field>>();

        boolean horizontal = false, vertical = false, a = false, b = true;

        for (Type t : Type.values())
            if (t != Type.NONE)
                map.put(t, new ArrayList<Field>());

        //horizontal
        if (horizontal) {
            for (int y = 1; y <= rows; y++) {
                for (int x = 1; x <= rows; x++) {
                    Field f = getField(x, y);
                    if (f != null) {
                        if (f.type == Type.NONE)
                            for (Type t : map.keySet())
                                map.get(t).clear();
                        else {
                            map.get(f.type).add(f);
                            for (Type t : map.keySet())
                                if (t != f.type)
                                    map.get(t).clear();
                            if (map.get(f.type).size() >= 5) {
                                System.out.println("WIN - HORIZONTAL - " + f.type + " - " + f.x + ", " + f.y);
                                return true;
                            }
                        }
                    }
                }
                for (Type t : map.keySet())
                    map.get(t).clear();
            }
        }

        for (Type t : map.keySet())
            map.get(t).clear();

        //vertikalni
        if (vertical) {
            for (int x = 1; x <= rows; x++) {
                for (int y = 1; y <= rows; y++) {
                    Field f = getField(x, y);
                    if (f != null) {
                        if (f.type == Type.NONE)
                            for (Type t : map.keySet())
                                map.get(t).clear();
                        else {
                            map.get(f.type).add(f);
                            for (Type t : map.keySet())
                                if (t != f.type)
                                    map.get(t).clear();
                            if (map.get(f.type).size() >= 5) {
                                System.out.println("WIN - VERTICAL - " + f.type + " - " + f.x + ", " + f.y);
                                return true;
                            }
                        }
                    }
                }
                for (Type t : map.keySet())
                    map.get(t).clear();
            }
        }

        for (Type t : map.keySet())
            map.get(t).clear();

        //a
        if (a) {
            for (int y = 1; y <= rows; y++) {
                for (int x = 1; x <= rows; x++) {
                    Field f = getField(x, y);
                    if (f != null) {
                        if (f.type != Type.NONE) {
                            boolean check = true;
                            for (int i = 1; i <= 4; i++) {
                                Field newField = getField(x + i, y + i);
                                if (newField != null) {
                                    if (newField.type != f.type) {
                                        check = false;
                                    } else if (i == 4 && check) {
                                        System.out.println("WIN - A - " + f.type + " - " + f.x + ", " + f.y);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //b
        if (b) {
            for (int y = 1; y <= rows; y++) {
                for (int x = 1; x <= rows; x++) {
                    Field f = getField(x, y);
                    if (f != null) {
                        if (f.type != Type.NONE) {
                            boolean check = true;
                            for (int i = 1; i <= 4; i++) {
                                Field newField = getField(x - i, y - i);
                                if (newField != null) {
                                    if (newField.type != f.type) {
                                        check = false;
                                    } else if (i == 4 && check) {
                                        System.out.println("WIN - B - " + f.type + " - " + f.x + ", " + f.y);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

}
