import java.util.Random;


// Bu s�n�f� stackoverflow �zerindew buldum.
// Bir enum'� random �ekilde se�ebilmemiz i�in gerekli fonksiyonlar� i�eriyor.
public class RandomEnumGenerator<T extends Enum<T>> {
    private static final Random PRNG = new Random();
    private final T[] values;

    public RandomEnumGenerator(Class<T> e) {
        values = e.getEnumConstants();
    }

    public T randomEnum() {
        return values[PRNG.nextInt(values.length)];
    }
}