import java.io.*;

public class MyScanner {
    private Reader input;
    private char[] buffer = new char[1 << 10];
    private int endB;
    private int currB = 0;
    private String number;


    private String next = "";
    public MyScanner(InputStream input) throws IOException {
        this.input = new InputStreamReader(input, "UTF-8");
        updateBuffer();
    }

    public MyScanner(String inputData, String encoding) throws IOException {
        this.input = new InputStreamReader(new FileInputStream(inputData), encoding);
        updateBuffer();
    }

    public MyScanner(String string) throws IOException {
        this.input= new StringReader(string);
        updateBuffer();
    }

    private boolean isStringSymbol(char symbol) {
        return Character.getType(symbol) == Character.DASH_PUNCTUATION || Character.isLetter(symbol) || symbol == '\'';
    }

    private boolean isPref(String word) {
        if (word.charAt(0) == '-') {
            if (word.length() >= 4) {
                return word.charAt(1) == '0' && (word.charAt(2) == 'x' || word.charAt(2) == 'X');
            }
        } else {
            if (word.length() >= 3) {
                return word.charAt(0) == '0' && (word.charAt(1) == 'x' || word.charAt(1) == 'X');
            }
        }
        return false;
    }

    private void updateBuffer() throws IOException {
        this.endB = input.read(this.buffer);
        this.currB = 0;
    }

    public void close() throws IOException {
        input.close();
    }

    private void checkBuffer() throws IOException {
        if (currB >= endB) {
            updateBuffer();
        }
    }

    public boolean hasNextLine() {
        return this.endB != -1;
    }

    public String nextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        checkBuffer();
        while (hasNextLine()) {
            if (System.lineSeparator().contains(Character.toString(buffer[currB]))) {
                StringBuilder separator = new StringBuilder();
                separator.append((buffer[currB]));
                currB++;
                checkBuffer();
                while (System.lineSeparator().contains(separator.toString()) && hasNextLine()) {
                    currB++;
                    checkBuffer();
                    separator.append(buffer[currB]);
                }
                break;
            } else {
                sb.append(buffer[currB]);
                currB++;
                checkBuffer();
            }
        }
        return sb.toString();
    }
    public String returnnext() throws IOException {
        StringBuilder s = new StringBuilder();
        checkBuffer();
        while (hasNextLine()) {
            if ((Character.getType(buffer[currB]) == Character.SPACE_SEPARATOR) && s.isEmpty()) {
                currB++;
            } else {
                if (!(Character.getType(buffer[currB]) == Character.SPACE_SEPARATOR)) {
                    s.append(buffer[currB]);
                    currB++;
                } else {
                    break;
                }
            }
            checkBuffer();
        }
        currB++;
        checkBuffer();
        this.next = s.toString();
        return next;
    }
    public boolean hasNextfirst() throws IOException {
        StringBuilder s = new StringBuilder();
        checkBuffer();
        while (hasNextLine()) {
            if ((Character.getType(buffer[currB]) == Character.SPACE_SEPARATOR) && s.isEmpty()) {
                currB++;
            } else {
                if (!(Character.getType(buffer[currB]) == Character.SPACE_SEPARATOR)) {
                    s.append(buffer[currB]);
                    currB++;
                } else {
                    break;
                }
            }
            checkBuffer();
        }
        currB++;
        checkBuffer();
        if (s.isEmpty()) {
            return false;
        }
        this.next = s.toString();
        return true;
    }
    public boolean hasNextsecond() throws IOException {
        StringBuilder s = new StringBuilder();
        checkBuffer();
        while (hasNextLine()) {
            if (!(isStringSymbol(buffer[currB])) && s.isEmpty()) {
                currB++;
            } else {
                if ((isStringSymbol(buffer[currB]))) {
                    s.append(buffer[currB]);
                    currB++;
                } else {
                    break;
                }
            }
            checkBuffer();
        }
        currB++;
        checkBuffer();
        if (s.isEmpty()) {
            return false;
        }
        this.next = s.toString();
        return true;
    }
    private boolean flag = true;
    public boolean hasNextthird() throws IOException {
        StringBuilder s = new StringBuilder();
        while (hasNextLine()) {
            if (System.lineSeparator().contains(Character.toString(buffer[currB]))) {
                currB++;
                checkBuffer();
                while(hasNextLine() && System.lineSeparator().contains(Character.toString(buffer[currB]))) {
                    currB++;
                    checkBuffer();
                }
                flag = false;
                currB--;
                break;
            }
            if (!(isStringSymbol(buffer[currB])) && s.isEmpty()) {
                currB++;
                checkBuffer();
            } else {
                if ((isStringSymbol(buffer[currB]))) {
                    s.append(buffer[currB]);
                    currB++;
                    checkBuffer();
                } else {
                    break;
                }
            }
            checkBuffer();
        }
        currB++;
        checkBuffer();
        if (s.isEmpty()) {
            return false;
        }
        this.next = s.toString();

        return true;
    }
    public String next() {
        return this.next;
    }

    public boolean change() {
        if (flag == false){
            flag = true;
            return true;
        }
        return false;
    }

    private boolean isHexSymbol(char symbol) {
        return (('0' <= symbol && symbol <= '9') || ('a' <= symbol && symbol <= 'f') || symbol == '-');
    }


    private boolean isIntSymbol(char symbol) {
        return (('0' <= symbol && symbol <= '9') || symbol == '-');
    }

    public boolean hasNextInt() throws IOException {
        String word = "";
        boolean flag = false;
        int index = 0;
        int end = 0;
        if (!this.next.isEmpty()) {
            word = this.next;
            end = word.length();
            while ((index < word.length()) && !isIntSymbol(word.charAt(index))) {
                index++;
            }
            end = index;
            while ((end < word.length()) && isIntSymbol(word.charAt(end))) {
                end--;
            }
        }
        if (end > index) {
            flag = true;
            this.number = word.substring(index, end);
            this.next = word.substring(end);
            return flag;
        }
        while (hasNextfirst()) {
            word = next();
            index = 0;
            while ((index < word.length()) && !isIntSymbol(word.charAt(index))){
                index++;
            }
            end = index;
            while ((end < word.length()) && isIntSymbol(word.charAt(end))){
                end++;
            }
            if (end > index) {
                flag = true;
                break;
            }
        }
        this.number = word.substring(index, end);
        this.next = word.substring(end);
        return flag;
    }

    public int nextIntNumber() {
        return Integer.parseInt(this.number);
    }

    private boolean isIntStringAbc(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!(('a'<=word.charAt(i) && 'j'>=word.charAt(i)) || word.charAt(i)=='-')) {
                return false;
            }
        }
        return true;
    }

    private int nextIntNumberAbc() {
        int x = 0;
        for (int i = 0; i < number.length(); i++) {
            x *= 10;
            if (number.charAt(i) == 'a') {
                x += 0;
            } else if (number.charAt(i) == 'b') {
                x += 1;
            } else if (number.charAt(i) == 'c') {
                x += 2;
            } else if (number.charAt(i) == 'd') {
                x += 3;
            } else if (number.charAt(i) == 'e') {
                x += 4;
            } else if (number.charAt(i) == 'f') {
                x += 5;
            } else if (number.charAt(i) == 'g') {
                x += 6;
            } else if (number.charAt(i) == 'h') {
                x += 7;
            } else if (number.charAt(i) == 'i') {
                x += 8;
            } else if (number.charAt(i) == 'j') {
                x += 9;
            }
        }
        if (number.charAt(0) == '-') {
            x *= -1;
        }
        return x;
    }

    private int nextHexPref() {
        if (number.charAt(0) == '-') {
            number = '-' + number.substring(3);
        } else {
            number = number.substring(2);
        }
        return Integer.parseUnsignedInt(number, 16);
    }

    private boolean isHexStringPref(String word) {
        if (isPref(word)) {
            if (word.charAt(0) == '-') {
                for (int i = 3;i<word.length();i++){
                    if (!isHexSymbol(word.charAt(i))) {
                        return false;
                    }
                }
            }
            else{
                for(int i = 2;i<word.length();i++){
                    if (!isHexSymbol(word.charAt(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean hasNextHexOrInt() throws IOException {
        String word = "";
        boolean flag = false;
        while (hasNextfirst()) {
            word = next();
            if (isHexStringPref(word) || isIntStringAbc(word)) {
                flag = true;
                break;
            }
        }
        this.number = word;
        return flag;
    }


    public int nextHexOrInt() {
        if (isHexStringPref(number)) {
            return nextHexPref();
        }
        if (isIntStringAbc(number)) {
            return nextIntNumberAbc();
        }
        throw new IllegalArgumentException("er");
    }
}