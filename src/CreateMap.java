import java.util.*;

public class CreateMap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int countRectangle = in.nextInt();
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
//        System.out.println(masX);
//        System.out.println(masY);
        int[][] map = new int[masY.size()][masX.size()];
        for (int i = 0;i < countRectangle;i++){
            mas[i].x1 = masX.indexOf(mas[i].x1);
            mas[i].y1 = masY.indexOf(mas[i].y1);
            mas[i].x2 = masX.indexOf(mas[i].x2);
            mas[i].y2 = masY.indexOf(mas[i].y2);
//            System.out.println(mas[i].x1 + " " + mas[i].y1+ " " +mas[i].x2+ " " +mas[i].y2);
            for (int j = mas[i].x1; j <= mas[i].x2; j++) {
                for (int k = mas[i].y1; k <= mas[i].y2; k++) {
                    if (k != mas[i].y2 & j != mas[i].x2)
                        map[k][j] +=1;
                }
            }
        }
//        for (int i = 0; i < masY.size(); i++) {
//            for (int j = 0; j < masX.size(); j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        int countPoint = in.nextInt();
        for (int i = 0;i < countPoint;i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int nx = masX.indexOf(x);
            int ny = masY.indexOf(y);
            if (nx != -1 && ny != -1){
                System.out.print(map[ny][nx]+" ");
            }
            else{

                if(x < masX.get(0) | y < masY.get(0)){
                    System.out.print(0+" ");
                    continue;
                }
                for (int j = 0; j < masX.size()+1; j++) {
                    if(j == masX.size()){
                        x = j-1;
                        break;
                    }
                    if(masX.get(j) == x){
                        x = j;
                        break;
                    }
                    if(masX.get(j) > x){
                        x = j-1;
                        break;
                    }
                }
                for (int j = 0; j < masY.size()+1; j++) {
                    if(j == masY.size()){
                        y = j-1;
                        break;
                    }
                    if(masY.get(j) == y){
                        y = j;
                        break;
                    }
                    if(masY.get(j) > y){
                        y = j-1;
                        break;
                    }
                }
                System.out.print(map[y][x]+" ");
            }



        }


    }
//    public static int ComCor()

    public static void createCoordinateCompression(ArrayList <Integer> list){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        Collections.sort(list);
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
