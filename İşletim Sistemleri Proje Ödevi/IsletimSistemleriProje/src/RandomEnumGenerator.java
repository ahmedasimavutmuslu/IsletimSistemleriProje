import java.util.Random;


// Bu sýnýfý stackoverflow üzerindew buldum.
// Bir enum'ý random þekilde seçebilmemiz için gerekli fonksiyonlarý içeriyor.
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