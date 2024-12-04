package helpers;

public class RandomNumberGenerator {

    public static Integer getRandomNumber(Integer maxNumber) {
        return (int) (Math.random() * maxNumber);
    }
}
