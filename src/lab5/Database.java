/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.util.ArrayList;

/**
 *
 * @author 4E3-01
 */
public class Database {

    public static final ArrayList<Letra> caracteres;

    static {
        caracteres = new ArrayList<Letra>();
        char aux = 48;
        for (int i = 0; i < 62; i++) {
            caracteres.add(new Letra(String.valueOf(aux)));
            aux++;
            if (aux == 58) {
                aux = 65;
            }
            else if (aux == 91){
                aux = 97;
            }
        }
        caracteres.add(new Letra(" "));
    }

    public static Letra getLetra(String md5) {
        for (int i = 0; i < caracteres.size(); i++) {
            if (md5.equals(caracteres.get(i).getMd5Code())) {
                return caracteres.get(i);
            }
        }
        return null;
    }
    
    public static boolean EhNumero(char c){
        if(c>='0' && c<='9'){
            return true;
        }
        else{
            return false;
        }
    }

    public static Conta getConta(String[] chars) {
        int i = 0;
        String name = "", money = "";
        for (i = 0; !EhNumero(getLetra(chars[i+1]).getCaractere().charAt(0)) ; i++) {
            name += getLetra(chars[i]).getCaractere();
        }
        i++;
        for (int j = i; j < chars.length; j++) {
            money += getLetra(chars[j]).getCaractere();
        }
        Conta acc = new Conta(name, money);
        return acc;
    }

    public static void test5() {
        Conta c = new Conta("124", "333", "1234", "10", "john doe");
        ServerDatabase.insereConta(c);
        String chave = SecurityProvider.md5ToServer(c);
        Conta conta = ServerDatabase.getConta(chave);
        String chars[];
        chars = SecurityProvider.md5ToClient(conta);
        System.out.println(Database.getConta(chars));
    }

    public static void test6() {
        Letra l = new Letra("a");
        String md5 = l.getMd5Code();
        System.out.println(md5);
        Letra ll = Database.getLetra(md5);
        System.out.println(ll.getCaractere());
    }

    public static void main(String[] args) {
        Database.test5();
    }
}
