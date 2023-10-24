package utils;

import java.util.Random;

public class RandomUtils {
    public static int getRandomNumber(int num) {
        return new Random().nextInt(num);
    }
}
