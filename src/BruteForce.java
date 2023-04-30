import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BruteForce {
    public static void main(String[] args) throws IOException {
        long sum1Ful = 0;
        long sum2Ful = 0;
        for (int f = 0; f < 10; f++) {
            File file = new File("C:\\Users\\георгий\\IdeaProjects\\workWithGraph\\src\\10000.txt");
            Scanner in = new Scanner(file, StandardCharsets.UTF_8);
            int countRectamgle = in.nextInt();
            long start = System.nanoTime();
            Rectangle[] mas = new Rectangle[countRectamgle];
            for (int i = 0; i < countRectamgle; i++) {
                mas[i] = new Rectangle(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
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
            int masCountRec[] = new int[countPoint];
            for (int i = 0; i < countPoint; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int count = 0;
                for (int j = 0; j < countRectamgle; j++) {
                    if (mas[j].check(x, y)) {
                        count += 1;
                    }
                }
                masCountRec[i] = count;
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
            for (int i = 0; i < countPoint; i++) {
                int count = masCountRec[i];
//            System.out.print(masCountRec[i]+" ");
            }

        }
        System.out.println(sum1Ful);
        System.out.println(sum2Ful);
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

        public boolean check(int x,int y){
            return ((x >= x1) & (x < x2)) & ((y >= y1) & (y < y2));
        }
    }
}
