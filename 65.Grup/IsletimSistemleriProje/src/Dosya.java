import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// Bu sinifin amaci verilecek txt belgesinin okunmasý ve proseslerin dispatcher ve
// Scheduler aracýlýðýyla gerekli iþlemlerden geçmesini saðlamaktýr.
public class Dosya
{
    public static int zaman=0;
    public static int pId=0;
    public static boolean userBos;
    public static boolean realBos;
    public static String sonCalisan;
    public static Proses kesilenProses;
    public static boolean prosesSonlandiMi;


    public static void Oku()  // Bu fonksiyon herhangi bir parametra almadan kendi bilgisayarýmdan yapýlan okuma içindir.
    {
        Dispatcher dispatcher=new Dispatcher();
        Scheduler scheduler =new Scheduler(dispatcher);
        try {
            FileInputStream okunacak = new FileInputStream("C:\\Users\\baroo\\OneDrive\\Masaüstü\\deneme.txt");
            //Scanner okuyucu = new Scanner(okunacak);
            DataInputStream in = new DataInputStream(okunacak);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while((strLine = br.readLine()) != null) //Sonraki satýr null olmayana kadar çalýþacak bir döngü
            {
                String[] splitted = strLine.split(","); // Boþluk koymayý unutma

                splitted[0].split(","); // Split iþlemi için kullanýlacak regexi gösteriyoruz.

                Proses eklenecekProses = new Proses(pId,Integer.parseInt(splitted[0]),Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]));

                pId++; // proses eklendikten sonra pId deðeri arttýrýlarak sýrayla artan bir adlandýrma yapýlmaya çalýþýldý

                scheduler.dispatcheraYolla(eklenecekProses); // Prosesler ilk vardýðý zaman hazýr durumda oldugu için direkt olarak dispatchera iletiliyor
                dispatcher.DispatcherRun(); // Ýletim gerçekleþtikten sonra Dispatcher çalýþtýrýýlýyor.

            }

            in.close();
            while(!Dosya.userBos||!Dosya.realBos) // Okuma bittikten sonra Dispatcher çaðrýsýnýn devam etmesi için bir döngü
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

    public static void Oku(String dosyaYolu) // Bu fonksiyon ise parametre alýp girilen dosya yolundan okuma yapýlmasýný saðlar.
    {
        Dispatcher dispatcher=new Dispatcher();
        Scheduler scheduler =new Scheduler(dispatcher);
        try {
            FileInputStream okunacak = new FileInputStream(dosyaYolu);
            DataInputStream in = new DataInputStream(okunacak);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while((strLine = br.readLine()) != null) //Sonraki satýr null olmayana kadar çalýþacak bir döngü
            {
                String[] splitted = strLine.split(","); // Boþluk koymayý unutma

                splitted[0].split(" ,"); // Split iþlemi için kullanýlacak regexi gösteriyoruz.

                Proses eklenecekProses = new Proses(pId,Integer.parseInt(splitted[0]),Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]));

                pId++; // proses eklendikten sonra pId deðeri arttýrýlarak sýrayla artan bir adlandýrma yapýlmaya çalýþýldý

                scheduler.dispatcheraYolla(eklenecekProses); // Prosesler ilk vardýðý zaman hazýr durumda oldugu için direkt olarak dispatchera iletiliyor
                dispatcher.DispatcherRun(); // Ýletim gerçekleþtikten sonra Dispatcher çalýþtýrýýlýyor.

            }

            in.close();
            while(Dosya.userBos||Dosya.realBos) // Okuma bittikten sonra Dispatcher çaðrýsýnýn devam etmesi için bir döngü
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
