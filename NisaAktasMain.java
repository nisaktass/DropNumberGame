/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsproje;

/**
 *
 * @author nisaaktas
 */
public class NisaAktasMain {

    public static void main(String[] args) {
        NisaAktasGame game = new NisaAktasGame();
        int[] sutunSenaryosu = {0, 3, 1, 2, 4, 1, 4, 0, 0, 1, 2, 2, 3, 1, 2, 0, 4, 2, 1, 3, 3, 3, 3, 1, 2, 2, 2, 2, 2, 1, 1};
        NisaAktasArayüz_start1 a = new NisaAktasArayüz_start1();
        a.setLocationRelativeTo(null);//ektanın tam ortasında acılsın dıye 
        a.setVisible(true);

        System.out.println("---GAME START---");
        game.printGrid();
        for (int i = 0; i < sutunSenaryosu.length; i++) {
            int col = sutunSenaryosu[i];
            System.out.println("\n-----------------------------------------");
            System.out.println(" STEP " + (i + 1) + " | SÜTUN: " + col + " | TAŞ: " + game.nextValue);
            System.out.println("-----------------------------------------");
            // 1. Taşı düşür
            game.dropTile(col);

            // 2. Tüm birleşmeleri arka arkaya yap
            while (game.merge(col));

            // 3. Değeri güncelle ve son durumu bas
            System.out.println("\nSTEP " + (i + 1) + " (Column: " + col + ")");
            System.out.println(" GELECEK DEGER: [" + game.nextValue + "]");
            game.printGrid();

            game.nextValue = game.generateNextValue();

            if (game.isGameOver()) {
                System.out.println("GAME OVER.");
                break;
            }
        }

    }
}
  