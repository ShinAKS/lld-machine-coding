import java.util.Random;

public class Utility {

    public static int getRandomValueInLimit(int limit){
        Random ran = new Random();

        int nxt = ran.nextInt(limit);

        return nxt;
    }
}
