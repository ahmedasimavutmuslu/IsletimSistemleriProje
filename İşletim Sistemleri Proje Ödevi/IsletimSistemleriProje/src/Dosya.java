import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// Bu sinifin amaci verilecek txt belgesinin okunmas� ve proseslerin dispatcher ve
// Scheduler arac�l���yla gerekli i�lemlerden ge�mesini sa�lamakt�r.
public class Dosya
{
    public static int zaman=0;
    public static int pId=0;
    public static boolean userBos;
    public static boolean realBos;
    public static String sonCalisan;
    public static Proses kesilenProses;
    public static boolean prosesSonlandiMi;


    public static void Oku()  // Bu fonksiyon herhangi bir parametra almadan kendi bilgisayar�mdan yap�lan okuma i�indir.
    {
        Dispatcher dispatcher=new Dispatcher();
        Scheduler scheduler =new Scheduler(dispatcher);
        try {
            FileInputStream okunacak = new FileInputStream("C:\\Users\\baroo\\OneDrive\\Masa�st�\\deneme.txt");
            //Scanner okuyucu = new Scanner(okunacak);
            DataInputStream in = new DataInputStream(okunacak);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while((strLine = br.readLine()) != null) //Sonraki sat�r null olmayana kadar �al��acak bir d�ng�
            {
                String[] splitted = strLine.split(","); // Bo�luk koymay� unutma

                splitted[0].split(","); // Split i�lemi i�in kullan�lacak regexi g�steriyoruz.

                Proses eklenecekProses = new Proses(pId,Integer.parseInt(splitted[0]),Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]));

                pId++; // proses eklendikten sonra pId de�eri artt�r�larak s�rayla artan bir adland�rma yap�lmaya �al���ld�

                scheduler.dispatcheraYolla(eklenecekProses); // Prosesler ilk vard��� zaman haz�r durumda oldugu i�in direkt olarak dispatchera iletiliyor
                dispatcher.DispatcherRun(); // �letim ger�ekle�tikten sonra Dispatcher �al��t�r��l�yor.

            }

            in.close();
            while(!Dosya.userBos||!Dosya.realBos) // Okuma bittikten sonra Dispatcher �a�r�s�n�n devam etmesi i�in bir d�ng�
            {
                dispatcher.DispatcherRun();
            }
        }
        catch (Exception e)
        {
            System.err.println("Error:"+e.getMessage());
            e.printStackTrace();
        }

    }

    public static void Oku(String dosyaYolu) // Bu fonksiyon ise parametre al�p girilen dosya yolundan okuma yap�lmas�n� sa�lar.
    {
        Dispatcher dispatcher=new Dispatcher();
        Scheduler scheduler =new Scheduler(dispatcher);
        try {
            FileInputStream okunacak = new FileInputStream(dosyaYolu);
            DataInputStream in = new DataInputStream(okunacak);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while((strLine = br.readLine()) != null) //Sonraki sat�r null olmayana kadar �al��acak bir d�ng�
            {
                String[] splitted = strLine.split(","); // Bo�luk koymay� unutma

                splitted[0].split(" ,"); // Split i�lemi i�in kullan�lacak regexi g�steriyoruz.

                Proses eklenecekProses = new Proses(pId,Integer.parseInt(splitted[0]),Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]));

                pId++; // proses eklendikten sonra pId de�eri artt�r�larak s�rayla artan bir adland�rma yap�lmaya �al���ld�

                scheduler.dispatcheraYolla(eklenecekProses); // Prosesler ilk vard��� zaman haz�r durumda oldugu i�in direkt olarak dispatchera iletiliyor
                dispatcher.DispatcherRun(); // �letim ger�ekle�tikten sonra Dispatcher �al��t�r��l�yor.

            }

            in.close();
            while(Dosya.userBos||Dosya.realBos) // Okuma bittikten sonra Dispatcher �a�r�s�n�n devam etmesi i�in bir d�ng�
            {
                dispatcher.DispatcherRun();
            }
        }
        catch (Exception e)
        {
            System.err.println("Error:"+e.getMessage());
            e.printStackTrace();
        }
    }

}
