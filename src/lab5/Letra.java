/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author 4E3-01
 */
public class Letra {
    
    private String caractere, md5Code;

    Letra(String caractere){
        this.caractere = caractere;
        this.md5Code = SecurityProvider.md5(caractere);
    }
    
    public String getCaractere() {
        return caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    @Override
    public String toString() {
        return caractere + ":" + md5Code+"\n";
    }
    
    
    
}
