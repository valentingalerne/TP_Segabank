package testBo;

import bo.CompteSimple;

public class TestLog {

    public static void main(String[] args) {

        CompteSimple compteSimple;

        compteSimple = new CompteSimple(1250.47f, 400f, 1);
        compteSimple.versement(600f);
        compteSimple.retrait(480.47f);
    }
}
