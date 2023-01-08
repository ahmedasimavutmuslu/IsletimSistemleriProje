import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


// Bu projenin en temel yapý taþý proses sýnýfý.
public class Proses
{
    private int prosesId;
    private int varisZamani;
    private int oncelik;
    private int prosesZamani;
    private String yaziRengi;
    private String durum;
    Random random = new Random();


    // Kurucu fonksiyonumuz 4 adet parametre alýyor.

    public Proses(int prosesId,int varisZamani, int oncelik,int prosesZamani)
    {
        setVarisZamani(varisZamani);
        setOncelik(oncelik);
        setProsesZamani(prosesZamani);
        setDurum("yürütülüyor");
        setProsesId(prosesId);
        yaziRengi=GiveRandomColor();

    }

    public int getProsesId() {
        return prosesId;
    }

    public void setProsesId(int prosesId) {
        this.prosesId = prosesId;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getDurum() {
        return durum;
    }

    public String GiveRandomColor()  // Bu fonksiyon konsola yazdýracaðýmýz rengi belirliyor ve prosese atanmasýný saðlýyor.
    {
        RandomEnumGenerator reg = new RandomEnumGenerator(Color.class);
        Color color = (Color) reg.randomEnum();
        return color.toString();
    }

    public void setYaziRengi(String yaziRengi) {
        this.yaziRengi = yaziRengi;
    }

    public String getYaziRengi() {
        return yaziRengi;
    }

    public void setVarisZamani(int varisZamani) {
        this.varisZamani = varisZamani;
    }

    public int getVarisZamani() {
        return varisZamani;
    }

    public void setProsesZamani(int prosesZamani) {
        this.prosesZamani = prosesZamani;
    }

    public int getProsesZamani() {
        return prosesZamani;
    }

    public void setOncelik(int oncelik) {
        this.oncelik = oncelik;
    }

    public int getOncelik() {
        return oncelik;
    }

    public String prosesBilgileri() // Bu fonksiyonumuz da bilgileri toplu bir þekilde kolayca yazdýrabilmemiz için.
    {
        return "Proses Durumu:"+getDurum()+" Proses Idsi:"+getProsesId()+ " Proses Onceligi:"+ getOncelik() + " Kalan zaman:" + getProsesZamani();
    }


}
