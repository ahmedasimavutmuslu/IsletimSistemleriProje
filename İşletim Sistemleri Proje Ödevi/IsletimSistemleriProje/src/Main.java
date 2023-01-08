import java.util.Scanner;

public class Main
{
    public static void main(String[] Args)
    {
        // Main fonksiyonuýnu oldugunca basit tutmaya çalýþtým.
        // Ýki adet seçenek sunup buna göre programýn çalýþmasýnýn devamý saðlandý.
        Scanner scanner=new Scanner(System.in);
        String cevap;
        System.out.println("//////////////////////////");
        System.out.println("//  Giriþ.txt Dosyanýz  //");
        System.out.println("//     Bulunuyor Mu?    //");
        System.out.println("//         E/H          //");
        System.out.println("//////////////////////////");
        cevap=scanner.nextLine();
        if(cevap.equals("e")||cevap.equals("E"))
        {
            System.out.println("//////////////////////////");
            System.out.println("// Giriþ.txt Dosyanýzýn //");
            System.out.println("//    Dizinini Giriniz  //");
            System.out.println("//////////////////////////");
            cevap=scanner.nextLine();

            System.out.println("//////////////////////////");
            System.out.println("//     Girilen Dizin    //");
            System.out.println("//     ile Program      //");
            System.out.println("//     Çalýþtýrýlacak   //");
            System.out.println("//////////////////////////");
            Dosya.Oku(cevap);


        }
        else
        {
            System.out.println("//////////////////////////");
            System.out.println("//  Varsayýlan Ayarlar  //");
            System.out.println("//     ile Program      //");
            System.out.println("//     Çalýþtýrýlacak   //");
            System.out.println("//////////////////////////");
            Dosya.Oku();

        }

    }
}
