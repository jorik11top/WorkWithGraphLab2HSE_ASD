import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PersistentTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int countRectangle = in.nextInt();
        Rectangle[] mas = new Rectangle[countRectangle];
        for (int i = 0; i < countRectangle; i++){
            mas[i] = new Rectangle(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
        }
        ArrayList<Integer> masX = new ArrayList<>();
        ArrayList<Integer> masY = new ArrayList<>();
        for (int i = 0; i < countRectangle; i++){
            masX.add(mas[i].pointDown.x);
            masY.add(mas[i].pointDown.y);
            masX.add(mas[i].pointHigh.x);
            masY.add(mas[i].pointHigh.y);
            masY.add(mas[i].pointHigh.y+1);
            masX.add(mas[i].pointHigh.x+1);
        }


        // создания Set координат координат и их сортировка
        createCoordinateCompression(masX);
        createCoordinateCompression(masY);
        System.out.println(masX);
        System.out.println(masY);
        for (int i = 0; i < countRectangle; i++) {
            mas[i].pointHigh = compressionPoint(masX,masY,mas[i].pointHigh);
            mas[i].pointDown = compressionPoint(masX,masY,mas[i].pointDown);
        }
        for (int i = 0; i < countRectangle; i++) {
            if (mas[i].pointHigh != null & mas[i].pointDown != null){
                System.out.println(mas[i].pointDown.x + " " + mas[i].pointDown.y + " " + mas[i].pointHigh.x + " " + mas[i].pointHigh.y);
            }
            else{
                System.out.println("гыг");
            }
        }
        ArrayList <Rectangle> operation = new ArrayList<>();
        for (int i = 0; i < countRectangle; i++) {
            operation.add(mas[i]);
        }


        int countPoint =in.nextInt();
        for (int i = 0; i < countPoint; i++) {
            Point point = new Point(in.nextInt(),in.nextInt());
            point = compressionPoint(masX,masY,point);
            if (point != null){
                System.out.println(point.x+" "+point.y);
            }
            else{
                System.out.println(point);
            }
        }
    }
    public static class Tree{
        Tree left;
        Tree right;
        int sum;
        public Tree(){
            left = null;
            right = null;
            sum = 0;
        }
    }
    public static Point compressionPoint(ArrayList<Integer> masX,ArrayList<Integer> masY,Point point){
        int nx = masX.indexOf(point.x);
        int ny = masY.indexOf(point.y);
        if (nx != -1 && ny != -1){
            point.x = nx;
            point.y = ny;
            return point;
        }
        else{
            if(point.x < masX.get(0) | point.y < masY.get(0)){
                return null;
            }
            for (int i = 0; i < masX.size()+1; i++) {
                if(i == masX.size()){
                    point.x = i-1;
                    break;
                }
                if(masX.get(i) == point.x){
                    point.x = i;
                    break;
                }
                if(masX.get(i) > point.x){
                    point.x = i-1;
                    break;
                }
            }
            for (int i = 0; i < masY.size()+1; i++) {
                if(i == masY.size()){
                    point.y = i-1;
                    break;
                }
                if(masY.get(i) == point.y){
                    point.y = i;
                    break;
                }
                if(masY.get(i) > point.y){
                    point.y = i-1;
                    break;
                }
            }
            return point;
        }

    }
    public static void createCoordinateCompression(ArrayList <Integer> list){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        Collections.sort(list);
    }
    public static class Point{
        int x;
        int y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static class Rectangle {
        Point pointDown;
        Point pointHigh;

        public Rectangle (int x1,int y1,int x2,int y2){
            pointDown = new Point(x1,y1);
            pointHigh = new Point(x2,y2);
        }

    }
}
