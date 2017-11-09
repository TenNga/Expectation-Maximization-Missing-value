/**
 * Created by 10ngawang on 2017-11-08.
 */
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class TableRead {
    public static void main(String[] args)throws IOException{
        int[][] data = new int[20][3];
        int p=10;
        String file1 = "hw2dataset_10.txt"; //read file using 'p' as a integer
        Scanner scanner = new Scanner(new File(file1));
        for(int i=0;i<3;i++){
            scanner.next();
        }
        while (scanner.hasNext()) {
        for(int i=0;i<20;i++)
            for(int j=0;j<3;j++) {

                    // int input2 = scanner.nextInt();
                    String input = scanner.next();
                    if (input.equals("0")) {
                    //    System.out.println("Zero ");
                        data[i][j] = Integer.parseInt(input);
                    } else if (input.equals("1")) {
                    //    System.out.println("One");
                        data[i][j] = Integer.parseInt(input);
                    } else if (input.equals("-")) {
                     //   System.out.println("Empty");
                        data[i][j] = 2;
                    } else {
                        System.out.println(" no NEED!! ");
                    }
                   // System.out.println(data[i][j]+" "+i+""+j);

                }   //scanner while
            }
           for (int x = 0; x < 20; x++) {
               for (int a = 0; a < 3; a++)
                   System.out.print(data[x][a] + " ");
               System.out.println();
           }

    }
}
