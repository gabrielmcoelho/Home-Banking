/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author PC09
 */
public class ServerDatabase{

    public static final ArrayList<ArrayList<Conta>> contas;
    public static final int N = 100;

    static {
        contas = new ArrayList<ArrayList<Conta>>();
        for (int i = 0; i < N; i++) {
            contas.add(new ArrayList<Conta>());
        }
    }

    public static int hashCode(String md5) {
        BigInteger bi = new BigInteger(md5, 16);
        BigInteger m = new BigInteger(Integer.toString(N), 10);
        int pos;
        pos = bi.mod(m).intValue();
        return pos;
    }

    public static void insereConta(Conta conta) {
        int pos = hashCode(conta.getMd5());

        for (int i = 0; i < contas.get(pos).size(); i++) {
            if (conta.getMd5() == contas.get(pos).get(i).getMd5()) {
                return;
            }
        }
        contas.get(pos).add(conta);
    }

    public static Conta getConta(String md5) {
        int pos = hashCode(md5);

        for (int i = 0; i < contas.get(pos).size(); i++) {
            if (contas.get(pos).get(i).getMd5().equals(md5)) {
                return contas.get(pos).get(i);
            }
        }
        return null;
    }

    public static void test3() {
        Conta c = new Conta("1234", "2222", "1245");
        ServerDatabase.insereConta(c);
        String chave = SecurityProvider.md5ToServer(c);
        System.out.println(chave);
        Conta conta = ServerDatabase.getConta(chave);
        System.out.println(conta);
    }
    
    public static void main(String[] args) {
        ServerDatabase.test3();
    }
}
