import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        File mFile;
        String fName;
        Scanner console = new Scanner(System.in);
        System.out.println("Enter path to the initial file");
        fName=console.nextLine();
        if (fName.length()==0){
            System.out.println("String is empty");
            System.exit(-1);
        }
        mFile=new File(fName);
        if (mFile.exists() && mFile.isFile()){
            HashMap<Character, Integer> enChar=new HashMap<>();
            try(FileReader read=new FileReader(fName)){
                int c;
                char a;
                while ((c=read.read())!=-1){
                    a=(char)c;
                    enChar.put(a, enChar.getOrDefault(a,0)+1);
                }
            }catch (IOException e){
                System.out.println("String is empty");
                System.exit(-1);
            }
            System.out.println("Enter path to the result file");
            fName=console.nextLine();
            if (fName.length()==0){
                System.out.println("String is empty");
                System.exit(-1);
            }
            File Res = new File(fName);
            if (!Res.exists()){
                try{
                    Res.createNewFile();
                }catch(IOException e){
                    if (!Res.isFile()){
                        System.out.println("It is not a file");
                        System.exit(-2);
                    }
                    else{
                        System.out.println("String is empty");
                        System.exit(-1);
                    }
                }
            }
            try(FileWriter write=new FileWriter(fName, false)){
                Set<Character> keys=enChar.keySet();
                for (char i:keys){
                    if (i==' ')
                        write.write("space="+enChar.get(i)+'\n');
                    else if (i=='\n')
                        write.write("newline="+enChar.get(i)+'\n');
                    else if (i=='\r')
                        write.write("return="+enChar.get(i)+'\n');
                    else if (i=='\t')
                        write.write("tab="+enChar.get(i)+'\n');
                    else
                        write.write(i +"="+ enChar.get(i)+'\n');
                }
            }catch (IOException e){
                if (!Res.isFile()){
                    System.out.println("It is not a file");
                    System.exit(-2);
                }
                else{
                    System.out.println("String is empty");
                    System.exit(-1);
                }
            }
        }
        else{
            System.out.println("File is not found");
        }
    }
}