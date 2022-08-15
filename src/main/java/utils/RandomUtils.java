package utils;

import java.util.Random;

public class RandomUtils {
    /**
     * случайное число для расположения мин
     **/
    public static int getRandomNumber(int num) {
        return new Random().nextInt(num);
    }
}
