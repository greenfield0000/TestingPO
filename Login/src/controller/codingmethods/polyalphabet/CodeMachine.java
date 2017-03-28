package controller.codingmethods.polyalphabet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Шифровальная машина, инкапсулирует в себе все действия 
 * @author roman
 */
public class CodeMachine {

    private int bias;
    private int keys[];
    private final char alph[] = {'_', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м',
        'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'ч', 'ц', 'ш', 'щ', 'ъ',
        'ы', 'ь', 'э', 'ю', 'я'};
    private StringBuffer word = null;
    
    public CodeMachine() {
        bias = 0;
        word = new StringBuffer();
        keys = new int[1000];
    }

    public StringBuffer getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = null;
        this.word = new StringBuffer();
        this.word.append(word);
    }

    public int[] getKeys() {
        return keys;
    }

    public void setKeys(String strKeys) {
        
        ArrayList<String> arrayList = new ArrayList<>();
        Arrays.stream(strKeys.split(",")).forEach(arrayList::add);
        
        this.bias = arrayList.size();
        this.keys = arrayList.stream().mapToInt(s -> Integer.parseInt(s)).toArray();
        
    }
    
    
    /**
     * 2 - закодировать, 1 - декодировать
     * @param option - опция (1,2)
     */
    public void coding(int option){
        char newSymbol;
        int newPosition;
        int j = 0;
        
        for (int i = 0; i < word.length(); i++) {
            if (j >= bias) j = 0;
            int x = getCharPosition(word.charAt(i));
            newPosition = (int) Math.floorMod((int) ((x + Math.pow(-1, option) * keys[j])), 31);
            newSymbol = getCharFromPosition(newPosition);
            setNewSymbol(i, newSymbol);
            j++;
        }
    }
    
    /**
     * Вставляем новый символ
     * @param position
     * @param newSybol 
     */
    public void setNewSymbol(int position, char newSybol){
        word.setCharAt(position, newSybol);
    }

    /**
     * Получает позицию текущего символа
     * @param curChar
     * @return 
     */
    public int getCharPosition(char curChar){
        int position = 0;
        int i = 0;
        boolean flag = true;
        
        String s = String.valueOf(alph);
        while(i < s.length() && (flag)){
            
            if (s.charAt(i) == curChar){
                position = i;
                flag = false;
            }
            i++;
        }
        return position;
    }
    
    public char getCharFromPosition(int position){
        return alph[position];
    }
}
