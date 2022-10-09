package foo.maddo.appimpressao;

/**
 * Created by marcomaddo on 02/12/2018.
 */

public class ComandoEscPos {

    public static byte[] setFonteTypeA = {0x1b, 0x4d, 0}; // ESCMn n = 0 Fonte A (12 * 24)
    public static byte[] setFonteTypeB = {0x1b, 0x4d, 1}; // ESCMn n = 1 Fonte B (1 * 24)
    public static byte[] setFonteTypeC = {0x1b, 0x4d, 2}; // ESCMn n = 2 Fonte C (1 * 24)
    public static byte[] setLeftAligned = {0x1b, 0x61, 0}; // ESCan 0 - Left Aligned
    public static byte[] setCenterAligner = {0x1b, 0x61, 1}; // ESCan 1 - Center Aligned
    public static byte[] setRightAligned = {0x1b, 0x61, 2}; // ESCan 1 - Right Aligned

    public static final byte ESC = 27;
    public static final byte GS = 29;
    
    public static byte[] LineFeed = {0x0a}; // ASCII, HEX, DECIMAL
    public static final byte LF = 10;

    /**
     * Seta a altura do código de barras em dots
     * default dots = 162
     *
     * @param dots (altura do código de barras)
     * @return bytes
     */
    public static byte[] setBarcodeHeight(byte dots) {
        byte[] cmd = new byte[3];
        cmd[0] = GS;
        cmd[1] = 104;
        cmd[2] = dots;
        return cmd;
    }

    /**
     * Tabela com os códigos dos padrões para impressão de códigos de barras
     * Nem todos vão funcionar, pois depende da impressora
     * Esta projeto está imprimindo o padrão EAN13
     */
    public static class BarCode {
        public static final byte UPC_A = 0;    // Não Testado
        public static final byte UPC_E = 1;    // Não Testado
        public static final byte EAN13 = 2;    // Testado
        public static final byte EAN8 = 3;     // Não Testado
        public static final byte CODE39 = 4;   // Não Testado
        public static final byte ITF = 5;      // Não Testado
        public static final byte NW7 = 6;      // Não Testado
        public static final byte CODE93 = 72;  // Não Testado
        public static final byte CODE128 = 73; // Não Testado
    }

    /**
     * Imprime e envie um Line Feed
     * LF
     *
     * @return bytes
     */
    public static byte[] imprimirLF() {
        byte[] cmd = new byte[1];
        cmd[0] = LF;
        return cmd;
    }

    /**
     * Inicializa a impressora
     * Limpa todas as configurações da impressora, é o mesmo que ligar e desligar
     * ESC @
     *
     * @return bytes
     */
    public static byte[] initPrinter() {
        byte[] cmd = new byte[2];
        cmd[0] = ESC;
        cmd[1] = 64;

        return cmd;
    }

    /**
     * get Font A
     * ESC M n
     *
     * @return bytes
     */
    public static byte[] getFontA() {
        byte[] cmd = new byte[3];
        cmd[0] = ESC;
        cmd[1] = 77;
        cmd[2] = 0;

        return cmd;
    }

    /**
     * get Font B
     * ESC M n
     *
     * @return bytes
     */
    public static byte[] getFontB() {
        byte[] cmd = new byte[3];
        cmd[0] = ESC;
        cmd[1] = 77;
        cmd[2] = 1;

        return cmd;
    }

    /**
     * get Font C ( se disponível na impressora, verificar manual do usuário )
     * ESC M n
     *
     * @return bytes
     */
    public static byte[] getFontC() {
        byte[] cmd = new byte[3];
        cmd[0] = ESC;
        cmd[1] = 77;
        cmd[2] = 2;

        return cmd;
    }

    /**
     * setAlinhamentoLeft
     * ESC a n
     *
     * @return bytes
     */
    public static byte[] setAlinhamentoLeft() {
        byte[] cmd = new byte[3];
        cmd[0] = ESC;
        cmd[1] = 97;
        cmd[2] = 0;

        return cmd;
    }

    /**
     * setAlinhamentoCenter
     * ESC a n
     *
     * @return bytes
     */
    public static byte[] setAlinhamentoCenter() {
        byte[] cmd = new byte[3];
        cmd[0] = ESC;
        cmd[1] = 97;
        cmd[2] = 1;

        return cmd;
    }

    /**
     * setAlinhamentoRight
     * ESC a n
     *
     * @return bytes
     */
    public static byte[] setAlinhamentoRight() {
        byte[] cmd = new byte[3];
        cmd[0] = ESC;
        cmd[1] = 97;
        cmd[2] = 2;

        return cmd;
    }

    /**
     * Seta a posição para impressão da string do cóidgo de barras
     *
     * @param n Posição
     *          0, 48 Não imprimir
     *          1, 49 Acima do código de barras
     *          2, 50 Abaixo do código de barras
     *          3, 51 Acima e abaixo do código de barras
     * @return bytes
     */
    public static byte[] setStringPosition(byte n) {
        byte[] cmd = new byte[3];
        cmd[0] = GS;
        cmd[1] = 72;
        cmd[2] = n;

        return cmd;
    }

    /**
     * getBarCode imprime o código de barras
     *
     * @param tipoCodigoBarras
     * @param codigoToPrint
     * @return
     */
    public static byte[] getBarCode(byte tipoCodigoBarras, 
    String codigoToPrint) {
        byte[] barcodebytes = codigoToPrint.getBytes();
        byte[] cmd = new byte[3 + barcodebytes.length + 1];

        cmd[0] = GS;
        cmd[1] = 107;
        cmd[2] = tipoCodigoBarras;

        int indice = 3;

        for (int i = 0; i < barcodebytes.length; i++) {
            cmd[indice] = barcodebytes[i];
            indice++;
        }
        cmd[indice] = 0;

        return cmd;
    }

    public static String putZeros2BarCode(String ticket, String caixa) {

        String retorno = "000";

        retorno += String.format("%05d", Integer.parseInt(ticket)) + String.format("%05d", Integer.parseInt(caixa));

        return retorno;
    }


}
