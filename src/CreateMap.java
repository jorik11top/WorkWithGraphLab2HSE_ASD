import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CreateMap {
    public static void main(String[] args) throws IOException {
        long sum1Ful = 0;
        long sum2Ful = 0;
        for (int f = 0; f < 10; f++) {
            File file = new File("C:\\Users\\георгий\\IdeaProjects\\workWithGraph\\src\\100.txt");
            Scanner in = new Scanner(file, StandardCharsets.UTF_8);
            int countRectangle = in.nextInt();
            long start = System.nanoTime();
            Rectangle[] mas = new Rectangle[countRectangle];
            for (int i = 0; i < countRectangle; i++){
                mas[i] = new Rectangle(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }
            ArrayList <Integer> masX = new ArrayList<>();
            ArrayList<Integer> masY = new ArrayList<>();
            for (int i = 0; i < countRectangle; i++){
                masX.add(mas[i].x1);
                masX.add(mas[i].x2);
                masY.add(mas[i].y1);
                masY.add(mas[i].y2);
                masY.add(mas[i].y2+1);
                masX.add(mas[i].x2+1);
            }
            // создания Set координат координат и их сортировка
            createCoordinateCompression(masX);
            createCoordinateCompression(masY);
            int[][] map = new int[masY.size()][masX.size()];
            for (int i = 0;i < countRectangle;i++){
                Point one = new Point(mas[i].x1,mas[i].y1);
                one = compressionPoint(masX,masY,one);
                Point two = new Point(mas[i].x2,mas[i].y2);
                two  = compressionPoint(masX,masY,two);
                mas[i].x1 = one.x;
                mas[i].y1 = one.y;
                mas[i].x2 = two.x;
                mas[i].y2 = two.y;
                for (int j = mas[i].x1; j <= mas[i].x2; j++) {
                    for (int k = mas[i].y1; k <= mas[i].y2; k++) {
                        if (k != mas[i].y2 & j != mas[i].x2)
                            map[k][j] +=1;
                    }
                }
            }
            long finish = System.nanoTime();
            long sumPrepare = finish - start;
            if(sum1Ful != 0){
                sum1Ful = (sumPrepare/1000 + sum1Ful)/2;
            }
            else{
                sum1Ful = sumPrepare/1000;
            }

            int countPoint = in.nextInt();
            long start2 = System.nanoTime();
            for (int i = 0;i < countPoint;i++){
                Point point = new Point(in.nextInt(),in.nextInt());
                point = compressionPoint(masX,masY,point);
                if(point != null) {
                    int a = map[point.y][point.x];
//                    System.out.print(map[point.y][point.x] + " ");
                }
                else{
//                    System.out.println(0+" ");
                }
            }
            long finish2 = System.nanoTime();
            long sumPrepare2 = finish2 - start2;
            if (sum2Ful != 0){
                sum2Ful = (sum2Ful+sumPrepare2/1000)/2;
            }
            else{
                sum2Ful = sumPrepare2/1000;
            }
            System.out.println(f+" 1t "+sumPrepare/1000+" 2t "+sumPrepare2/1000);


        }
        System.out.println(sum1Ful);
        System.out.println(sum2Ful);
    }


    public static void createCoordinateCompression(ArrayList <Integer> list){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        Collections.sort(list);
    }
    /***
     функция которая сжимает точку по массиву уникальных координат
     */
    public static Point compressionPoint(ArrayList<Integer> masX, ArrayList<Integer> masY, Point point){
        point.x = binarySearch(masX, point.x,0,masX.size()-1);
        point.y = binarySearch(masY, point.y,0,masY.size()-1);
        if(point.x != -1 & point.y != -1)
            return point;
        else{
            return null;
        }

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
    public static int binarySearch(ArrayList<Integer> sortedArray, int valueToFind, int low, int high) {
        int index = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedArray.get(mid) < valueToFind) {
                low = mid + 1;
            } else if (sortedArray.get(mid) > valueToFind) {
                high = mid - 1;
                index = mid - 1;
            } else if (sortedArray.get(mid) == valueToFind) {
                index = mid;
                break;
            }
        }

        return index;
    }
    public static class Rectangle {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Rectangle (int x1,int y1,int x2,int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

    }
}
