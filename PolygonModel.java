import polygon.IDrawablePolygon;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Niklas on 2016-02-21.
 */
public class PolygonModel extends JComponent {
    private ArrayList<IDrawablePolygon> polygons = new ArrayList<>();

    private boolean direction = true;
    private int ticker = 0;

    public void addPolygon(IDrawablePolygon p){
        polygons.add(p);
    }

    public void paint(Graphics g) {
        for (IDrawablePolygon polygon : polygons) {
            polygon.paint(g);
        }
    }

    private void translate(int x, int y){
        for (IDrawablePolygon p: polygons){
            p.translate(x,y);
        }

    }
    private void update(){
        ticker++;
        int value = direction ? 10 : -10;
        translate(value, value);
        notifyListeners();
        if (ticker > 10) {
            direction = !direction;
            ticker = 0;
        }
    }

    private void notifyListeners(){
        for (AnimateListener l : listeners)
            l.actOnUpdate();
    }

    public void animate(){
        try {
            while (true) {
                Thread.sleep(500);
                update();
            }
        } catch (InterruptedException e) {}
    }

    private List<AnimateListener> listeners = new ArrayList<>();
    public void addListener(AnimateListener l){
        listeners.add(l);
    }

}

