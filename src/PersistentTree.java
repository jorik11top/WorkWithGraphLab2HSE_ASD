
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PersistentTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int countRectangle = in.nextInt();
        if(countRectangle != 0) {
            Rectangle[] mas = new Rectangle[countRectangle];
            for (int i = 0; i < countRectangle; i++) {
                mas[i] = new Rectangle(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }
            ArrayList<Integer> masX = new ArrayList<>();
            ArrayList<Integer> masY = new ArrayList<>();
            for (int i = 0; i < countRectangle; i++) {
                masX.add(mas[i].pointDown.x);
                masY.add(mas[i].pointDown.y);
                masX.add(mas[i].pointHigh.x);
                masY.add(mas[i].pointHigh.y);
            }


            // создания Set координат координат и их сортировка
            createCoordinateCompression(masX);
            createCoordinateCompression(masY);

            ArrayList<Operation> masOper = new ArrayList<>();
            // сжимаем коордианты прямоугольников и добовляем в массив операций
            for (int i = 0; i < countRectangle; i++) {
                mas[i].pointHigh = compressionPoint(masX, masY, mas[i].pointHigh);
                mas[i].pointDown = compressionPoint(masX, masY, mas[i].pointDown);
                masOper.add(new Operation(mas[i], 1));
                masOper.add(new Operation(mas[i], -1));
            }
            // сортируем массив операций
            BottleSort(masOper);
            // создания списка операция на каждое дерево
            ArrayList<ArrayList<Operation>> masTr = new ArrayList<>();
            for (int i = 0; i < masX.size(); i++) {
                masTr.add(new ArrayList<Operation>());
                for (int j = 0; j < masOper.size(); j++) {
                    if (masOper.get(j).mod == 1) {
                        if (masOper.get(j).rectangle.pointDown.x == i) {
                            masTr.get(i).add(masOper.get(j));
                        }
                    } else {
                        if (masOper.get(j).rectangle.pointHigh.x == i) {
                            masTr.get(i).add(masOper.get(j));
                        }
                    }
                }
            }

            int countNode = masOper.size() - 2;
            Tree start = new Tree(0, countNode);
            Tree[] masTree = new Tree[masTr.size()];
            for (int i = 0; i < masTree.length; i++) {
                if (i == 0) {
                    masTree[i] = new Tree();
                    masTree[i].copy(start);
                    for (int j = 0; j < masTr.get(i).size(); j++) {
                        masTree[i].modificate(masTr.get(i).get(j).mod, masTr.get(i).get(j).rectangle.pointDown.y, masTr.get(i).get(j).rectangle.pointHigh.y - 1);
                    }
                } else {
                    masTree[i] = new Tree();
                    masTree[i].copy(masTree[i - 1]);
                    for (int j = 0; j < masTr.get(i).size(); j++) {
                        masTree[i].modificate(masTr.get(i).get(j).mod, masTr.get(i).get(j).rectangle.pointDown.y, masTr.get(i).get(j).rectangle.pointHigh.y - 1);
                    }
                }
            }
            // обработка каждой точки
            int countPoint = in.nextInt();
            if (countPoint != 0 ){
                for (int i = 0; i < countPoint; i++) {
                    Point point = new Point(in.nextInt(), in.nextInt());
                    point = compressionPoint(masX, masY, point);
                    int sum = 0;
                    if (point != null) {
                        sum = masTree[point.x].sumMod(point.y,sum);
                        System.out.print(sum+" ");
                        sum = 0;
                    } else {
                        System.out.print(0+ " ");
                    }
                }
            }
        }
    }

    /***
        Класс дерева
     */
    public static class Tree {
        Tree left;
        Tree right;
        int sum;
        int lg;
        int rg;

        public Tree() {
            left = null;
            right = null;
            sum = 0;
        }
        /***
          создания листа
         */
        public Tree(int one) {
            lg = one;
            rg = one;
            left = null;
            right = null;
            sum = 0;
        }
        /***
          создания новой вершины
         */
        public Tree(int start, int end) {
            lg = start;
            rg = end;
            sum = 0;
            if (start == end - 1) {
                left = new Tree(start);
                right = new Tree(end);
            } else if (start == end) {
                left = null;
                right = null;
            } else {
                int newEnd = (start + end) / 2;
                left = new Tree(start, newEnd);
                right = new Tree(newEnd + 1, end);
            }
        }
        /***
         копирования дерева
         */
        public void copy(Tree tree) {
            lg = tree.lg;
            rg = tree.rg;
            sum = tree.sum;
            if (tree.left != null) {
                left = new Tree();
                left.copy(tree.left);
            }
            if (tree.right != null) {
                right = new Tree();
                right.copy(tree.right);
            }
        }

        /***
         функция которая ставит мод на вершины с полным покрытием
         */
        public void modificate(int mod, int l, int r) {
            if (l <= lg & r  >= rg) {
                this.sum += mod;
            } else {
                if (left != null) {
                    left.modificate(mod, l, r);
                }
                if (right != null) {
                    right.modificate(mod, l, r);
                }
            }
        }
        /***
          функция которая находит сумму по координате y
         */
        public int sumMod(int num, int mod) {

            if (num == this.lg & num == this.rg) {
//                mod += sum;
                return mod+sum;
            }
            else if(this.left == null & this.right == null){
                    return 0;
                }
            else if (this.left == null & this.right != null) {
                return right.sumMod(num,mod+this.sum);}
            else if (this.left != null & this.right == null) {
                return left.sumMod(num,mod+this.sum);}
            else {
                int nsum = sum+mod;
                int sum1 = left.sumMod(num, nsum);
                int sum2 = right.sumMod(num,nsum);
                return Math.max(sum1,sum2);
            }
        }
    }

    /***
     Пузырьковая сортировка для операций, по координатам x
     */
    public static void BottleSort(ArrayList<Operation> masOper){
        for (int i = 0; i < masOper.size(); i++) {
            for (int j = i+1; j < masOper.size(); j++) {
                if(masOper.get(i).mod == 1){
                    if(masOper.get(j).mod == 1){
                        if(masOper.get(j).rectangle.pointDown.x < masOper.get(i).rectangle.pointDown.x ){
                            Collections.swap(masOper,i,j);
                        }
                    }
                    else{
                        if(masOper.get(j).rectangle.pointHigh.x < masOper.get(i).rectangle.pointDown.x ){
                            Collections.swap(masOper,i,j);
                        }
                    }
                }
                else{
                    if(masOper.get(j).mod == 1){
                        if(masOper.get(j).rectangle.pointDown.x <= masOper.get(i).rectangle.pointHigh.x ){
                            Collections.swap(masOper,i,j);
                        }
                    }
                    else{
                        if(masOper.get(j).rectangle.pointHigh.x < masOper.get(i).rectangle.pointHigh.x ){
                            Collections.swap(masOper,i,j);
                        }
                    }
                }
            }
        }
    }
    /***
     Класс операция, содержащия треугольник и его модификатор
     */
    public static class Operation{
        Rectangle rectangle;
        int mod;
        public Operation(Rectangle rectangle,int mod){
            this.rectangle = rectangle;
            this.mod = mod;
        }
    }
    /***
     функция которая сжимает точку по массиву уникальных координат
     */
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
    /***
      создаёт массив уникальных координат
     */
    public static void createCoordinateCompression(ArrayList <Integer> list){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        Collections.sort(list);
    }
    /***
     Класс точки
     */
    public static class Point{
        int x;
        int y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    /***
     Класс прямоугольника
     */
    public static class Rectangle {
        Point pointDown;
        Point pointHigh;

        public Rectangle (int x1,int y1,int x2,int y2){
            pointDown = new Point(x1,y1);
            pointHigh = new Point(x2,y2);
        }

    }
}
