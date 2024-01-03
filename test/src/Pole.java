import java.util.Scanner;

public class Pole {
    Scanner scan=new Scanner(System.in);
    private char[] array;
    private int lastXCoordinate;
    private int lastYCoordinate;

    public void reset(){
        for(int i=0; i<25; ++i){
            array[i]=' ';
        }
    }

    Pole(){
        array=new char[25];
        reset();
    }
    public void display(){
        for (int i=0; i<25; i++){
            if((i+1)%5==0){
                System.out.println(array[i]);
                continue;
            }
            System.out.print(array[i]+"|");
        }
    }

    public boolean isEmpty(int x, int y){
        if(array[5*(5-y)+x-1]==' ')
            return true;
        else
            return false;
    }

    public void setNolik(int x, int y){
        array[5*(5-y)+x-1]='0';
        lastXCoordinate=x;
        lastYCoordinate=y;
    }

    public void setKrestik(int x, int y){
        array[5*(5-y)+x-1]='X';
    }

    public boolean isKrestikiWin(){
        int temp=0;
        for(int i=1; i<6; i++){
            for(int j=0; j<5; j++){
                if(array[5*(5-i)+j]=='X')
                    temp++;
            }
            if(temp==5)
                return true;
            temp=0;
        }
        for(int i=1; i<6; i++){
            for(int j=0; j<5; j++){
                if(array[5-i+j*5]=='X')
                    temp++;
            }
            if(temp==5)
                return true;
            temp=0;
        }
        for(int j=0; j<5; j++){
            if(array[j*6]=='X')
                temp++;
        }
        if(temp==5)
            return true;
        temp=0;
        for(int j=1; j<6; j++){
            if(array[j*4]=='X')
                temp++;
        }
        if(temp==5)
            return true;
        temp=0;
        return false;
    }

    public boolean isNolikikiWin(){
        int temp=0;
        for(int i=1; i<6; i++){
            for(int j=0; j<5; j++){
                if(array[5*(5-i)+j]=='0')
                    temp++;
            }
            if(temp==5)
                return true;
            temp=0;
        }
        for(int i=1; i<6; i++){
            for(int j=0; j<5; j++){
                if(array[5-i+j*5]=='0')
                    temp++;
            }
            if(temp==5)
                return true;
            temp=0;
        }
        for(int j=0; j<5; j++){
            if(array[j*6]=='0')
                temp++;
        }
        if(temp==5)
            return true;
        temp=0;
        for(int j=1; j<6; j++){
            if(array[j*4]=='0')
                temp++;
        }
        if(temp==5)
            return true;
        temp=0;
        return false;
    }

    public void move(){
        System.out.println("Enter the Coordinates");
        int x=scan.nextInt();
        int y=scan.nextInt();
        while(1>x || x>6 || 1>y || y>6){
            System.out.println("Coordinates are incorrect, try again");
            x=scan.nextInt();
            y=scan.nextInt();
        }
        while(isEmpty(x,y)==false){
            System.out.println("Choose another coordinates");
            x=scan.nextInt();
            y=scan.nextInt();
            while(1>x || x>6 || 1>y || y>6){
                System.out.println("Coordinates are incorrect, try again");
                x=scan.nextInt();
                y=scan.nextInt();
            }
        }
        setNolik(x,y);
    }

    public int getLastX(){
        return lastXCoordinate;
    }

    public int getLastY(){
        return lastYCoordinate;
    }

    public boolean isFull(){
        int k=0;
        for(int i=0; i<25; ++i)
            if(array[i]!=' ')
                ++k;
        if(k==25)
            return true;
        else
            return false;
    }
}
