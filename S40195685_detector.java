import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class S40195685_detector {

    public static void main(String[] args) throws FileNotFoundException {
        String file1 = args[0];
        String file2 = args[1];
        List<String> listOfStrings1 = readContents(file1);
        String wordArray1 [] = modifyContents(listOfStrings1);
        List<String> listOfStrings2 = readContents(file2);
        String wordArray2 [] = modifyContents(listOfStrings2);
        int lcs_length = modifiedLcs(wordArray1,wordArray2, wordArray1.length, wordArray2.length);
        double threshold =(double)lcs_length/min(wordArray1.length, wordArray2.length);
        int comparison = Double.compare(threshold, 0.63);
        if(comparison >= 0){
            System.out.println("1");
        }
        else{
            System.out.println("0");
        }
    }
    public static List<String> readContents(String fileName) throws FileNotFoundException {
        List<String> listOfStrings = null;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            listOfStrings = new ArrayList<>();
            String contentLine = br.readLine();
            while (contentLine != null) {
                String[] words = contentLine.replaceAll("[^0-9a-zA-Z ]", "").toLowerCase().split("\\s+");
                for(int i=0; i<words.length;i++) {
                    listOfStrings.add(words[i]);
                    listOfStrings.removeIf(String::isEmpty);
                }
                contentLine = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            }
            catch (IOException ioe)
            {
                System.out.println("Error in closing the BufferedReader");
            }
        }
        return listOfStrings;
    }

    public static String [] modifyContents(List<String> words){
        String [] stopWords ={"a","about","above","after","against","again","all","am","an","and","any","are","arent","as","at","be","because","been","before","being","below","between","both","but","by","cant","cannot","could","couldnt","did","didnt","do","does","doesnt","doing","dont","down","during","each","few","for","from","further","had","hadnt","has","hasnt","have","havent","having","he","hed","hell","hes","her","here","heres","hers","herself","him","himself","his","how","hows","i","id","ill","im","ive","if","in","into","is","isnt","it","its","its","itself","lets","me","more","most","mustnt","my","myself","no","nor","not","of","off","on","once","only","or","other","ought","our","ours	ourselves","out","over","own","same","shant","she","shed","shell","shes","should","shouldnt","so","some","such","than","that","thats","the","their","theirs","them","themselves","then","there","theres","these","they","theyd","theyll","theyre","theyve","this","those","through","to","too","under","until","up","very","was","wasnt","we","we'd","well","were","weve","were","werent","what","whats","when","whens","where","wheres","which","while","who","whos","whom","why","whys","with","wont","would","wouldnt","you","youd","youll","youre","youve","your","yours","yourself","yourselves"};
        ArrayList<String> stopWords1 = new ArrayList<>();
        for(int i=0; i<stopWords.length;i++){
            stopWords1.add(stopWords[i]);
        }
        for(int i =0;i<words.size();i++){
            if(stopWords1.contains(words.get(i)))
            {
                words.remove(i);
            }
        }
        String wordArr1 [] = words.toArray(new String[0]);
        wordArr1 = Stemming(wordArr1);
        Set<String> hs = new HashSet<>();
        for(int i =0; i< wordArr1.length;i++){
            hs.add(wordArr1[i]);
        }
        List<String> sortedList = new ArrayList<String>(hs);
        Collections.sort(sortedList);
        String wordArray [] = sortedList.toArray(new String[0]);
        return wordArray;
    }

    public static int modifiedLcs( String wordArr1[], String wordArr2[], int m, int n)
    {
        int lcs[][] = new int[2][n+1];
        int x=0;
        for (int i = 0; i <= m; i++)
        {
            x = i & 1;
            for (int j = 0; j <= n; j++)
            {
                if (i == 0 || j == 0)
                    lcs[x][j] = 0;

                else if ((wordArr1[i - 1]).equals(wordArr2[j - 1]))
                    lcs[x][j] = lcs[1 - x][j - 1] + 1;
                else
                    lcs[x][j] = max(lcs[1 - x][j],
                            lcs[x][j - 1]);
            }
        }
        return lcs[x][n];
    }

    static int max(int a, int b){
        return (a > b)? a : b;
    }

    static int min(int a, int b){
        return (a < b)? a : b;
    }

    public static String[] Stemming(String[] wordArr) {
        for(int i=0; i<wordArr.length; i++)
        {
            wordArr[i] = wordArr[i].replaceAll("s$", "")
                    .replaceAll("ess$", "")
                    .replaceAll("ic$","")
                    .replaceAll("ler?$","")
                    .replaceAll("ing$","")
                    .replaceAll("ism$", "")
                    .replaceAll("ly$","");
        }
        return wordArr;
    }
}
