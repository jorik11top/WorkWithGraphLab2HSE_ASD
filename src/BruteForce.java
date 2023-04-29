import java.util.Scanner;

public class BruteForce {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int countRectamgle = in.nextInt();
        Rectangle[] mas = new Rectangle[countRectamgle];
        for (int i = 0; i < countRectamgle; i++){
            mas[i] = new Rectangle(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
        }
        int countPoint = in.nextInt();
        int masCountRec[] = new int[countPoint];
        for (int i = 0;i<countPoint;i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int count = 0;
            for (int j = 0; j < countRectamgle; j++){
                if (mas[j].check(x, y)){
                    count+=1;
                }
            }
            masCountRec[i] = count;
        }
        for(int i = 0;i < countPoint;i++){
            System.out.print(masCountRec[i]+" ");
        }


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
